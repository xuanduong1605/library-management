package dxn.library.service;

import com.nimbusds.jose.JOSEException;
import dxn.library.dto.request.AuthenticationRequest;
import dxn.library.dto.request.IntrospectRequest;
import dxn.library.dto.request.LogoutRequest;
import dxn.library.dto.request.RefreshRequest;
import dxn.library.dto.response.AuthenticationResponse;
import dxn.library.dto.response.IntrospectResponse;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticateUser(AuthenticationRequest request) throws JOSEException;
    IntrospectResponse introspectToken(IntrospectRequest request) throws JOSEException, ParseException;
    void logoutUser(LogoutRequest request) throws JOSEException, ParseException;
    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
