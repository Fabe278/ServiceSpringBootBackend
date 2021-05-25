package at.fhainzinger.data;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "services")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String date;

    private String longitude;

    private String latitude;

    private Integer employeeId;

    public ServiceEntity() {
    }

    public ServiceEntity(Integer id, String name, String date, String longitude, String latitude, Integer employeeId) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
        this.employeeId = employeeId;
    }

    public Service convertToService(){
        Service result = new Service();
        result.setId(id);
        result.setDate(date);
        result.setLatitude(latitude);
        result.setLongitude(longitude);
        result.setName(name);
        result.setEmployeeId(employeeId);
        return result;
    }
}
