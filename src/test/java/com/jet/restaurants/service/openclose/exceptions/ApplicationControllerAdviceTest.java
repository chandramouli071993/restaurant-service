package com.jet.restaurants.service.openclose.exceptions;

import com.jet.restaurants.service.restaurants.exceptions.ApplicationControllerAdvice;
import com.jet.restaurants.service.restaurants.exceptions.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class ApplicationControllerAdviceTest {

    @InjectMocks
    ApplicationControllerAdvice applicationControllerAdvice;

    @Test
    void shouldHandleNotFoundException() {
        NoSuchElementException exception = new NoSuchElementException("not found");
        ResponseEntity<ErrorResponse> errorResponse = applicationControllerAdvice.return404(exception);
        ErrorResponse responseBody = errorResponse.getBody();
        assertNotNull(responseBody);
        assertThat(responseBody.getCode()).isEqualTo("404");
        assertThat(responseBody.getDescription()).isEqualTo("not found");
    }

    @Test
    void shouldHandledGenericException() {
        Throwable exception = new Throwable("generic exception");
        ResponseEntity<ErrorResponse> errorResponse = applicationControllerAdvice.handleException(exception);
        ErrorResponse responseBody = errorResponse.getBody();
        assertNotNull(responseBody);
        assertThat(responseBody.getCode()).isEqualTo("500");
        assertThat(responseBody.getDescription()).isEqualTo("generic exception");
    }

}
