package api_imotors.api_imotors.ErrorHandler;

public class BusinessError {

    private String typeError = "BusinessException";

    public String message;

    public BusinessError(String typeError, String message) {
        this.typeError = typeError;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTypeError() {
        return typeError;
    }

}