/**
 * ResourceNotFoundException.java
 */
package by.bnicky.computergames.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nick Korp
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="No such resource.")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
