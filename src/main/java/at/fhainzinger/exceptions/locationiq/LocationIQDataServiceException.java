package at.fhainzinger.exceptions.locationiq;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class LocationIQDataServiceException extends RuntimeException {
    public LocationIQDataServiceException(String message) { super(message);}
}
