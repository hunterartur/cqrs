package io.ai.comandside.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@Getter
public class ApplicationException extends RuntimeException {
    private HttpStatusCode statusCode;
    private String message;
}
