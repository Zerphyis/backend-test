package dev.Zerphyis.ParkTest.Infra.Configs;

import dev.Zerphyis.ParkTest.Domain.Interfaces.ResidentPaymentRepository;
import dev.Zerphyis.ParkTest.Infra.EntityCore.InterfaceImpls.ResidentPaymentRepositoryFileImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResidentPaymentRepositoryConfig {
    @Bean
    public ResidentPaymentRepository residentPaymentRepository() {
        return new ResidentPaymentRepositoryFileImpl();
    }
}
