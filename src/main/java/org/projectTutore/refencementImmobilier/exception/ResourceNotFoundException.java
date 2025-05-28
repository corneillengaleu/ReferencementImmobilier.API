package org.projectTutore.refencementImmobilier.Configuration;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}