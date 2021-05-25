package at.fhainzinger.config;

import at.fhainzinger.data.ServiceEntity;
import at.fhainzinger.database.ServiceRepository;
import at.fhainzinger.services.ServiceDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    private static final Logger log = LoggerFactory.getLogger(AppConfiguration.class);

    @Bean
    CommandLineRunner initDatabase(ServiceRepository repository){
        return args -> {
            if(repository.count() == 0){
                log.info("Preloading " + repository.save(new ServiceEntity(1, "Service 1", "13.04.2020 13:34", "13.577984", "48.268140", 1)));
                log.info("Preloading " + repository.save(new ServiceEntity(2, "Service 2", "27.04.2021 13:34", "13.835329", "48.235252", 2)));
            }
        };
    }
}
