package at.fhainzinger.data;

import lombok.Data;

@Data
public class ServiceDto {
    private String name;
    private String date;
    private String address;
    private Integer employeeId;
}
