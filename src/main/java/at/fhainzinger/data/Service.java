package at.fhainzinger.data;

import lombok.Data;

@Data
public class Service {
    private int id;
    private String name;
    private String date;
    private String longitude;
    private String latitude;
    private Integer employeeId;

    public ServiceResource convertToServiceResource(){
        ServiceResource result = new ServiceResource();
        result.setId(id);
        result.setDate(date);
        result.setLatitude(latitude);
        result.setLongitude(longitude);
        result.setName(name);
        result.setEmployeeId(employeeId);
        return result;
    }

    public ServiceEntity convertToServiceEntity(){
        ServiceEntity result = new ServiceEntity();
        if(id != -1) {
            result.setId(id);
        }
        result.setDate(date);
        result.setLatitude(latitude);
        result.setLongitude(longitude);
        result.setName(name);
        result.setEmployeeId(employeeId);
        return result;
    }
}
