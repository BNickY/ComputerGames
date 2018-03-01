/**
 * ResourceNoContentException.java
 */
package by.bnicky.computergames.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nick Korp
 */
@ResponseStatus(value = HttpStatus.NO_CONTENT, reason="There is no data.")
public class ResourceNoContentException extends RuntimeException{

    public ResourceNoContentException(String message) {
        super(message);
    }
}
