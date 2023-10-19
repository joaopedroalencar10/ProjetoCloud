package api_imotors.api_imotors.ErrorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import api_imotors.api_imotors.exception.BusinessException;

@ControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse validationHandler(MethodArgumentNotValidException e) {
        ValidationErrorResponse errors = new ValidationErrorResponse();
        for (FieldError item : e.getBindingResult().getFieldErrors()) {
            errors.getErrors().add(new Validation(item.getField(), item.getDefaultMessage()));
        }

        return errors;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse businessExceptionHandler(BusinessException e) {
        ValidationErrorResponse errors = new ValidationErrorResponse();
        errors.getBusinessErrors().add(new BusinessError(e.getClass().getSimpleName(), e.getMessage()));
        return errors;
    }

}