package at.fhainzinger.config;

import at.fhainzinger.services.ServiceDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public ServiceDataService createServiceDataService(){
        return new ServiceDataService();
    }
}
