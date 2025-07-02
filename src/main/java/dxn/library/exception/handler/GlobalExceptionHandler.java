package dxn.library.exception.handler;

import dxn.library.dto.response.ApiResponse;
import dxn.library.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ApiException.class)
    ResponseEntity<ApiResponse> handlingApiException (ApiException apiException) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(apiException.getErrorCode().getCode());
        apiResponse.setMessage(apiException.getErrorCode().getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
