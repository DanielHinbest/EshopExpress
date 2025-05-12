package ca.eshopexpress.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception thrown when a requested resource is not found.
 * Typically used in service or controller layers to handle missing entities.
 *
 * Responds with HTTP status 404 (NOT FOUND).
 *
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-12
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with a specific message.
     *
     * @param message the detail message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
