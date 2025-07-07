package dxn.library.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import dxn.library.dto.request.AuthenticationRequest;
import dxn.library.dto.request.IntrospectRequest;
import dxn.library.dto.request.LogoutRequest;
import dxn.library.dto.request.RefreshRequest;
import dxn.library.dto.response.AuthenticationResponse;
import dxn.library.dto.response.IntrospectResponse;
import dxn.library.exception.ApiException;
import dxn.library.exception.ResponseCode;
import dxn.library.model.ExpiredToken;
import dxn.library.model.User;
import dxn.library.repository.ExpiredTokenRepository;
import dxn.library.repository.UserRepository;
import dxn.library.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.ParseException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final ExpiredTokenRepository expiredTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret-key}")
    protected String secretKey;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository,
                                     ExpiredTokenRepository expiredTokenRepository,
                                     PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.expiredTokenRepository = expiredTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthenticationResponse authenticateUser(AuthenticationRequest request) throws JOSEException {
        var user = userRepository.getUserByUsername(request.getUsername())
                .orElseThrow(() -> new ApiException(ResponseCode.UNKNOWN_USER));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new ApiException(ResponseCode.AUTHENTICATION_ERROR);
        }

        return AuthenticationResponse.builder()
                .username(user.getUsername())
                .token(generateToken(user.getUsername()))
                .authenticated(true)
                .build();
    }

    public IntrospectResponse introspectToken(IntrospectRequest request) throws JOSEException, ParseException {
        var token = verifyToken(request.getToken());

        return IntrospectResponse.builder()
                .active(true)
                .build();
    }

    public void logoutUser(LogoutRequest request) throws JOSEException, ParseException {
        var token = verifyToken(request.getToken());

        String jti = token.getJWTClaimsSet().getJWTID();
        Date expiredDate = token.getJWTClaimsSet().getExpirationTime();

        ExpiredToken expiredToken = new ExpiredToken(jti, expiredDate);

        expiredTokenRepository.save(expiredToken);
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var token = verifyToken(request.getToken());

        String jti = token.getJWTClaimsSet().getJWTID();
        Date expiredDate = token.getJWTClaimsSet().getExpirationTime();

        ExpiredToken expiredToken = new ExpiredToken(jti, expiredDate);

        expiredTokenRepository.save(expiredToken);

        var username = token.getJWTClaimsSet().getSubject();

        return AuthenticationResponse.builder()
                .token(generateToken(username))
                .authenticated(true)
                .build();
    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier jwsVerifier = new MACVerifier(secretKey.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean authenticated = signedJWT.verify(jwsVerifier) && expirationDate.after(new Date(Instant.now().toEpochMilli()));

        boolean expired = expiredTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID());

        if (!authenticated || expired) {
            throw new ApiException(ResponseCode.AUTHENTICATION_ERROR);
        }

        return signedJWT;
    }

    private String generateToken(String username) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        Optional<User> user = userRepository.getUserByUsername(username);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("dxn.library")
                .issueTime(new Date(Instant.now().toEpochMilli()))
                .expirationTime(new Date(Instant.now().plusSeconds(60 * 60 * 24).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", user.get().getRole())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        jwsObject.sign(new MACSigner(secretKey.getBytes()));

        return jwsObject.serialize();
    }
}
