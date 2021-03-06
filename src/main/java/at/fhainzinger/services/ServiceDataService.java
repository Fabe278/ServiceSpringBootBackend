package at.fhainzinger.services;

import at.fhainzinger.data.*;
import at.fhainzinger.database.ServiceRepository;
import at.fhainzinger.exceptions.services.ServiceMSBadRequestException;
import at.fhainzinger.exceptions.services.ServiceMSResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ServiceDataService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private LocationIQDataService locationIQDataService;

    public List<ServiceResource> getServiceResources() {
        List<ServiceResource> result = new ArrayList<>();
        for (ServiceEntity s : serviceRepository.findAll()){
            result.add(s.convertToService().convertToServiceResource());
        }
        return result;
    }

    public ServiceResource addService(ServiceDto serviceDto) {
        checkService(serviceDto);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:MM");
        try{
            Date theDate = dateFormat.parse(serviceDto.getDate());
        }catch(Exception ignored){
            throw new ServiceMSResourceNotFoundException("The param date is not valid. Format must be dd.MM.yyyy hh:MM");
        }

        Service service = new Service();

        service.setId(-1);
        service.setName(serviceDto.getName());
        service.setDate(serviceDto.getDate());
        service.setEmployeeId(serviceDto.getEmployeeId());
        LongitudeLatitude longitudeLatitude = locationIQDataService.getLongitudeLatitudeByAddress(serviceDto.getAddress());
        service.setLatitude(longitudeLatitude.getLatitude());
        service.setLongitude(longitudeLatitude.getLongitude());

        ServiceEntity storedEntity = this.serviceRepository.save(service.convertToServiceEntity());

        return storedEntity.convertToService().convertToServiceResource();
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.equals("");
    }

    private void checkService(ServiceDto serviceDto){
        if(serviceDto.getName().length() <= 4)
            throw new ServiceMSBadRequestException("The param name is not valid. Length must be > 4");
        if(isNullOrEmpty(serviceDto.getName()))
            throw new ServiceMSBadRequestException("The param name is not valid");
        if(isNullOrEmpty(serviceDto.getDate()))
            throw new ServiceMSBadRequestException("The param date is not valid");
        if(isNullOrEmpty(serviceDto.getAddress()))
            throw new ServiceMSBadRequestException("The param address is not valid");
        if(serviceDto.getAddress().length() <= 5)
            throw new ServiceMSBadRequestException("The param address is not valid. Length must be > 5");
    }

    public ServiceResource getService(int serviceId) {
        Optional<ServiceEntity> foundService = serviceRepository.findById(serviceId);
        if(!foundService.isPresent())
            throw new ServiceMSResourceNotFoundException(String.format("The service with the id %d does not exist!", serviceId));
        return foundService.get().convertToService().convertToServiceResource();
    }

    public ServiceResource editService(int serviceId, ServiceDto serviceDto) {
        Optional<ServiceEntity> foundService = serviceRepository.findById(serviceId);
        if(!foundService.isPresent())
            throw new ServiceMSResourceNotFoundException(String.format("The service with the id %d does not exist!", serviceId));
        Service serviceToChange = foundService.get().convertToService();

        checkService(serviceDto);

        serviceToChange.setDate(serviceDto.getDate());
        serviceToChange.setName(serviceDto.getName());
        serviceToChange.setEmployeeId(serviceDto.getEmployeeId());
        LongitudeLatitude longitudeLatitude = locationIQDataService.getLongitudeLatitudeByAddress(serviceDto.getAddress());
        serviceToChange.setLatitude(longitudeLatitude.getLatitude());
        serviceToChange.setLongitude(longitudeLatitude.getLongitude());
        ServiceEntity changedEntity = serviceRepository.save(serviceToChange.convertToServiceEntity());
        return changedEntity.convertToService().convertToServiceResource();
    }

    public ServiceResource deleteService(int serviceId) {
        Optional<ServiceEntity> foundService = serviceRepository.findById(serviceId);
        if(!foundService.isPresent()){
            throw new ServiceMSResourceNotFoundException(String.format("The service with the id %d does not exist!", serviceId));}
        else {
            ServiceEntity deletedEntity = foundService.get();
            serviceRepository.delete(deletedEntity);
            return deletedEntity.convertToService().convertToServiceResource();
        }
    }

    public List<ServiceResource> getServicesByEmployeeId(int employeeId) {
        return serviceRepository.findAllByEmployeeId(employeeId).stream().map(x -> x.convertToService().convertToServiceResource()).collect(Collectors.toList());
    }
}
