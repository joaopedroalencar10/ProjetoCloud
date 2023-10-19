package api_imotors.api_imotors.ErrorHandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

public class ValidationErrorResponse {
    public String message = "Existem erros na sua requisição";
    
    public List<Validation> validationErrors = new ArrayList<Validation>();
    
    public List<BusinessError> businessErrors = new ArrayList<BusinessError>();

    public List<Validation> getErrors() {
        return validationErrors;
    }

    public void setErrors(List<Validation> errors) {
        this.validationErrors = errors;
    }

    public String getMessage() {
        return message;
    }

    public List<BusinessError> getBusinessErrors() {
        return businessErrors;
    }

    public void setBusinessErrors(List<BusinessError> businessErrors) {
        this.businessErrors = businessErrors;
    }

}