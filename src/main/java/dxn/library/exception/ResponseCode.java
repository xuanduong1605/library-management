package dxn.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    PLAYER_EXISTED(400, "Player already existed."),
    UNKNOWN_PLAYER(401, "Player does not exist."),
    USERNAME_NOT_EXISTS(401, "Username does not exist"),
    AUTHENTICATION_ERROR(402, "Password is incorrect"),
    AUTHORIZATION_ERROR(403, "Authorization error"),

    AUTHENTICATION_SUCCESSFUL(102, "Login successful"),
    AUTHORIZATION_SUCCESSFUL(103, "Authorization successful"),

    UNKNOWN_ERROR(666, "An unknown error occurred");

    private final int code;
    private final String message;
}

