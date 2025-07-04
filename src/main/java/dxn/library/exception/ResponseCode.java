package dxn.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    INVALID_INPUT(300, "Invalid input."),
    INVALID_USERNAME(301, "Username must be between 3 and 20 characters."),
    MISSING_USERNAME(302, "Username is required."),
    INVALID_PASSWORD(303, "Password must be at least 6 characters."),
    MISSING_PASSWORD(304, "Password is required."),
    INVALID_EMAIL(305, "Email is invalid."),

    USER_EXISTED(400, "User already existed."),
    BOOK_EXISTED(400, "Book already existed."),
    AUTHOR_EXISTED(400, "Author already existed."),
    CATEGORY_EXISTED(400, "Category already existed."),
    UNKNOWN_USER(401, "User does not exist."),
    UNKNOWN_BOOK(401, "Book does not exist."),
    UNKNOWN_AUTHOR(401, "Author does not exist."),
    UNKNOWN_CATEGORY(401, "Category does not exist."),
    UNKNOWN_BOOK_ORDER(401, "Book order does not exist."),

    AUTHENTICATION_ERROR(500, "Password is incorrect."),
    AUTHORIZATION_ERROR(501, "Authorization error."),
    AUTHENTICATION_SUCCESSFUL(502, "Login successful."),
    AUTHORIZATION_SUCCESSFUL(503, "Authorization successful."),

    UNKNOWN_EXCEPTION(666, "An unknown error occurred.");

    private final int code;
    private final String message;
}

