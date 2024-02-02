package io.ai.comandside.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private HttpStatusCode statusCode;
    private String message;
}
