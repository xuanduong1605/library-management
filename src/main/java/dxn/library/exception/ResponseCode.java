package dxn.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    INVALID_INPUT(300, "Invalid input"),
    INVALID_USERNAME(301, "Username must be between 3 and 20 characters"),
    MISSING_USERNAME(302, "Username is required"),
    INVALID_PASSWORD(303, "Password must be at least 6 characters"),
    MISSING_PASSWORD(304, "Password is required"),
    INVALID_EMAIL(305, "Email is invalid"),

    USER_EXISTED(400, "Player already existed."),
    UNKNOWN_USER(401, "Player does not exist."),

    AUTHENTICATION_ERROR(402, "Password is incorrect"),
    AUTHORIZATION_ERROR(403, "Authorization error"),
    AUTHENTICATION_SUCCESSFUL(102, "Login successful"),
    AUTHORIZATION_SUCCESSFUL(103, "Authorization successful"),

    UNKNOWN_EXCEPTION(666, "An unknown error occurred");

    private final int code;
    private final String message;
}

