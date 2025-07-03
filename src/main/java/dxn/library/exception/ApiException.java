package dxn.library.exception;

public class ApiException extends RuntimeException {
    private ResponseCode responseCode;

    public ApiException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public ResponseCode getErrorCode() {
        return responseCode;
    }

    public void setErrorCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}

