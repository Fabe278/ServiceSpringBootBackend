package at.fhainzinger.controller;

import at.fhainzinger.data.ServiceDto;
import at.fhainzinger.data.ServiceResource;
import at.fhainzinger.services.ServiceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/services")
public class ServiceRESTController {
    @Autowired
    private ServiceDataService serviceDataService;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<ServiceResource>> getAllServices(){
        List<ServiceResource> serviceResources = serviceDataService.getServiceResources();
        return new HttpEntity<>(serviceResources);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ServiceResource addService(@RequestBody ServiceDto serviceDto){
        return serviceDataService.addService(serviceDto);
    }

    @RequestMapping(value ="/{serviceId}", method = RequestMethod.GET)
    public ServiceResource getService(@PathVariable int serviceId){
        return serviceDataService.getService(serviceId);
    }

    @RequestMapping(value ="/{serviceId}", method = RequestMethod.PUT)
    public ServiceResource editService(@PathVariable int serviceId, @RequestBody ServiceDto serviceDto){
        return serviceDataService.editService(serviceId, serviceDto);
    }

    @RequestMapping(value ="/{serviceId}", method = RequestMethod.DELETE)
    public ServiceResource deleteService(@PathVariable int serviceId){
        return serviceDataService.deleteService(serviceId);
    }

    @RequestMapping(value ="/emp/{employeeId}", method = RequestMethod.GET)
    public List<ServiceResource> getServicesByEmployeeId(@PathVariable int employeeId){
        return serviceDataService.getServicesByEmployeeId(employeeId);
    }

}
