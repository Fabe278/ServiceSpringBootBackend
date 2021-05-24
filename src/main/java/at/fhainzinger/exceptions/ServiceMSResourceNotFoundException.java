package at.fhainzinger.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ServiceMSResourceNotFoundException extends RuntimeException{
    public ServiceMSResourceNotFoundException(String message) {
        super(message);
    }
}
