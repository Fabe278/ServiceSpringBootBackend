package at.fhainzinger.database;

import at.fhainzinger.data.ServiceEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRepository extends CrudRepository<ServiceEntity, Integer> {
    List<ServiceEntity> findAllByEmployeeId(Integer employeeId);
}
