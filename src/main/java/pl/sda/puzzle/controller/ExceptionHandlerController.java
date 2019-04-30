package pl.sda.puzzle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionHandlerController {

    @ExceptionHandler(ConstraintViolationException.class)
    public Collection<PropertyValidationError> handleConstraintViolationException(ConstraintViolationException e) {
        return e.getConstraintViolations()
                .stream()
                .map(constraintViolation -> new PropertyValidationError(constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(ValidationException.class)
    public Collection<PropertyValidationError> handleIllegalArgumentException(ValidationException e) {
        Collection<PropertyValidationError> collection = Arrays.asList(new PropertyValidationError("email", "zajety adres emial"));
        return collection;
    }

    // ConstraintViolationException extends ValidationException extends RuntimeException
}
// ConstraintViolationException a = (ConstraintViolationException) e;
//
//        return a.getConstraintViolations()
//                .stream()
//                .map(constraintViolation -> new PropertyValidationError(constraintViolation.getPropertyPath().toString(),
//                        constraintViolation.getMessage()))
//                .collect(Collectors.toList());