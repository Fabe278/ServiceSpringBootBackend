package at.fhainzinger.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ServiceMSBadRequestException  extends RuntimeException{
    public ServiceMSBadRequestException(String message) {
        super(message);
    }
}
