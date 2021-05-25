package at.fhainzinger.services;

import at.fhainzinger.data.LongitudeLatitude;
import at.fhainzinger.data.ServiceResource;
import at.fhainzinger.exceptions.locationiq.LocationIQDataServiceException;
import at.fhainzinger.exceptions.locationiq.LocationIQNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import javax.management.ServiceNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Component
public class LocationIQDataService {
    private static final String LOCATIONIQ_MICROSERVICE_GET_ADDRESS_BY_LONGITUDE_LATITUDE = "http://localhost:9002/locationiq/addresses?longitude={longitude}&latitude={latitude}";
    private static final String LOCATIONIQ_MICROSERVICE_GET_LONG_LAT_BY_ADDRESS= "http://localhost:9002/locationiq/longlat?address={address}";

    public String getAddressByLongitudeLatitude(String longitude, String latitude){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<>();
        vars.put("longitude", longitude);
        vars.put("latitude", latitude);
        try{
            return restTemplate.getForObject(LOCATIONIQ_MICROSERVICE_GET_ADDRESS_BY_LONGITUDE_LATITUDE, String.class, vars);
        } catch (RestClientResponseException e) {
            if(e.getRawStatusCode() == 400) {
                throw new LocationIQNotFoundException("The location was not found");
            } else {
                throw new LocationIQDataServiceException(e.getResponseBodyAsString());
            }
        }
    }

    public LongitudeLatitude getLongitudeLatitudeByAddress(String address){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<>();
        vars.put("address", address);
        try{
            return restTemplate.getForObject(LOCATIONIQ_MICROSERVICE_GET_LONG_LAT_BY_ADDRESS, LongitudeLatitude.class, vars);
        } catch (RestClientResponseException e) {
            if(e.getRawStatusCode() == 400) {
                throw new LocationIQNotFoundException("The location was not found");
            } else {
                throw new LocationIQDataServiceException(e.getResponseBodyAsString());
            }
        }
    }
}
