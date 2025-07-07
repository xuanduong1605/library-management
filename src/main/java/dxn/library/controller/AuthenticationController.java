package dxn.library.controller;

import com.nimbusds.jose.JOSEException;
import dxn.library.dto.request.AuthenticationRequest;
import dxn.library.dto.request.IntrospectRequest;
import dxn.library.dto.request.LogoutRequest;
import dxn.library.dto.request.RefreshRequest;
import dxn.library.dto.response.ApiResponse;
import dxn.library.dto.response.AuthenticationResponse;
import dxn.library.dto.response.IntrospectResponse;
import dxn.library.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("api/v0/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.authenticateUser(request))
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws JOSEException, ParseException {
        return ApiResponse.<IntrospectResponse>builder()
                .result(authenticationService.introspectToken(request))
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws JOSEException, ParseException {
        authenticationService.logoutUser(request);
        return ApiResponse.<Void>builder()
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request) throws JOSEException, ParseException {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.refreshToken(request))
                .build();
    }
}
