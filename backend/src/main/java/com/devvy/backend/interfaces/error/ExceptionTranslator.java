package com.devvy.backend.interfaces.error;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@ControllerAdvice
public class ExceptionTranslator implements ProblemHandling {

    @ExceptionHandler
    public ResponseEntity<Problem> handleDataIntegrityViolationException(
        DataIntegrityViolationException exception,
        NativeWebRequest request
    ) {
        var problem = Problem.builder()
            .withTitle("DataIntegrityViolationException")
            .withStatus(Status.BAD_REQUEST)
            .withDetail(exception.getMessage())
            .build();

        return create(exception, problem, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleException(
        Exception exception,
        NativeWebRequest request
    ) {
        var problem = Problem.builder()
                             .withTitle("Unhandled exception")
                             .withStatus(Status.BAD_REQUEST)
                             .withDetail(exception.getMessage())
                             .build();
        return create(exception, problem, request);
    }
}
