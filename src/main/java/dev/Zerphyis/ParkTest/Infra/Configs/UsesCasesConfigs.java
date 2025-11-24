package dev.Zerphyis.ParkTest.Infra.Configs;

import dev.Zerphyis.ParkTest.Aplication.UseCases.*;
import dev.Zerphyis.ParkTest.Domain.Interfaces.ResidentPaymentRepository;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsesCasesConfigs {

    @Bean
    public RegisterEntry registerEntry(VehicleRepository repository){
        return new RegisterEntry(repository);
    }

    @Bean
    public RegisterExit registerExit(VehicleRepository repository){
        return new RegisterExit(repository);
    }

    @Bean
    public StartMonth startMonth(VehicleRepository repository){
        return new StartMonth(repository);
    }

    @Bean
    public AddResidentVehicle addResidentVehicle(VehicleRepository repository){
        return new AddResidentVehicle(repository);
    }

    @Bean
    public AddOfficialVehicle addOfficialVehicle(VehicleRepository repository){
        return new AddOfficialVehicle(repository);
    }

    @Bean
    public GenerateResidentPaymentsReport generateResidentPaymentsReport(
            VehicleRepository vehicleRepository,
            ResidentPaymentRepository residentPaymentRepository
    ) {
        return new GenerateResidentPaymentsReport(vehicleRepository, residentPaymentRepository);
    }

}
