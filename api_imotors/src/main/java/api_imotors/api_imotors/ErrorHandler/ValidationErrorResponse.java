package api_imotors.api_imotors.ErrorHandler;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {
    public String message = "Existem erros na sua requisição";
    public List<Validation> errors = new ArrayList<Validation>();

    public List<Validation> getErrors() {
        return errors;
    }

    public void setErrors(List<Validation> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }
}
