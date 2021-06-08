package at.fhainzinger.data;

import lombok.Data;

@Data
public class ServiceWithLongLatDto {
    private String name;
    private String date;
    private String longitude;
    private String latitude;
    private Integer employeeId;
}
