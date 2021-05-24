package at.fhainzinger.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceMSInternalServerErrorException extends RuntimeException{
    public ServiceMSInternalServerErrorException(String message) {
        super(message);
    }
}
