package dxn.library.exception.handler;

import dxn.library.dto.response.ApiResponse;
import dxn.library.exception.ApiException;
import dxn.library.exception.ResponseCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ResponseCode.UNKNOWN_EXCEPTION.getCode());
        apiResponse.setMessage(ResponseCode.UNKNOWN_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = ApiException.class)
    ResponseEntity<ApiResponse> handlingApiException (ApiException apiException) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(apiException.getErrorCode().getCode());
        apiResponse.setMessage(apiException.getErrorCode().getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception){
        List<String> logs = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ApiResponse<List<String>> apiResponse = new ApiResponse<>();

        apiResponse.setCode(ResponseCode.INVALID_INPUT.getCode());
        apiResponse.setMessage(ResponseCode.INVALID_INPUT.getMessage());
        apiResponse.setResult(logs);

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
