package by.bnicky.computergames.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason="There is no data.")
public class ResourceNoContentExceprion extends RuntimeException{

    public ResourceNoContentExceprion(String message) {
        super(message);
    }
}
