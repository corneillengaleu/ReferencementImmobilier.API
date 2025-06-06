package org.projectTutore.refencementImmobilier.Configuration;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class CustomApiResponse<T> {
    // Getters and setters
    private boolean success;
    private String message;
    private T data;
    private int status;

    public CustomApiResponse() {}

    public CustomApiResponse(boolean success, String message, T data, HttpStatus status) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.status = status.value();
    }

}
