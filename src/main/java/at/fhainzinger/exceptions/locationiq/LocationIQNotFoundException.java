package at.fhainzinger.exceptions.locationiq;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LocationIQNotFoundException extends RuntimeException {
    public LocationIQNotFoundException(String message) {
        super(message);
    }
}
