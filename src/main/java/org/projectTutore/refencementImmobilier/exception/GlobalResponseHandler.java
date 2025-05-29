package org.projectTutore.refencementImmobilier.exception;

import org.projectTutore.refencementImmobilier.Configuration.CustomApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalResponseHandler {

    // 🔹 Ressource non trouvée
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomApiResponse<Object>> handleNotFound(ResourceNotFoundException ex) {
        return buildResponse(false, ex.getMessage(), null, HttpStatus.NOT_FOUND);
    }

    // 🔹 Violation d'intégrité (clé unique, etc.)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomApiResponse<Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = "Violation d'intégrité des données.";

        Throwable cause = ex.getCause();
        if (cause instanceof org.hibernate.exception.ConstraintViolationException hibernateEx) {
            String constraint = hibernateEx.getConstraintName();
            message = switch (constraint) {
                case "uk_user_email" -> "L'adresse e-mail est déjà utilisée.";
                default -> "Contrainte violée : " + constraint;
            };
        } else if (cause instanceof SQLIntegrityConstraintViolationException sqlEx) {
            message = "Contrainte SQL violée : " + sqlEx.getMessage();
        }

        return buildResponse(false, message, null, HttpStatus.CONFLICT);
    }

    // 🔹 Erreurs de validation (javax ou jakarta.validation)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomApiResponse<Object>> handleConstraintViolation(ConstraintViolationException ex) {
        StringBuilder message = new StringBuilder("Erreur de validation : ");
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            message.append(violation.getPropertyPath())
                    .append(" ")
                    .append(violation.getMessage())
                    .append("; ");
        }

        return buildResponse(false, message.toString(), null, HttpStatus.BAD_REQUEST);
    }

    // 🔹 Mauvais arguments
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomApiResponse<Object>> handleIllegalArgument(IllegalArgumentException ex) {
        return buildResponse(false, ex.getMessage(), null, HttpStatus.BAD_REQUEST);
    }

    // 🔹 Toutes les autres erreurs
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomApiResponse<Object>> handleAll(Exception ex) {
        // Optional: log exception stack trace
        // ex.printStackTrace();
        return buildResponse(false, "Erreur interne : " + ex.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 🔧 Méthode utilitaire pour créer une réponse
    private ResponseEntity<CustomApiResponse<Object>> buildResponse(boolean success, String message, Object data, HttpStatus status) {
        CustomApiResponse<Object> response = new CustomApiResponse<>(success, message, data, status);
        return new ResponseEntity<>(response, status);
    }
}
