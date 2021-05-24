package at.fhainzinger.data;

import lombok.Data;

@Data
public class ServiceResource {
    private int id;
    private String name;
    private String date;
    private String longitude;
    private String latitude;
    private Integer employeeId;
}
