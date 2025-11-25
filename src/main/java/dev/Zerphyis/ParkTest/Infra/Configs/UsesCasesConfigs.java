package dev.Zerphyis.ParkTest.Infra.Configs;

import dev.Zerphyis.ParkTest.Aplication.Service.ParkingService;
import dev.Zerphyis.ParkTest.Aplication.UseCases.*;
import dev.Zerphyis.ParkTest.Domain.Interfaces.ResidentPaymentRepository;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsesCasesConfigs {

    @Bean
    public RegisterEntry registerEntry(VehicleRepository repository) {
        return new RegisterEntry(repository);
    }

    @Bean
    public RegisterExit registerExit(VehicleRepository repository) {
        return new RegisterExit(repository);
    }

    @Bean
    public StartMonth startMonth(VehicleRepository repository) {
        return new StartMonth(repository);
    }

    @Bean
    public AddResidentVehicle addResidentVehicle(VehicleRepository repository) {
        return new AddResidentVehicle(repository);
    }

    @Bean
    public AddOfficialVehicle addOfficialVehicle(VehicleRepository repository) {
        return new AddOfficialVehicle(repository);
    }

    @Bean
    public FindAllVehicles findAllVehicles(VehicleRepository repository) {
        return new FindAllVehicles(repository);
    }

    @Bean
    public FindVehicleByPlate findVehicleByPlate(VehicleRepository repository) {
        return new FindVehicleByPlate(repository);
    }

    @Bean
    public FindVehiclesByTypes findVehiclesByTypes(VehicleRepository repository) {
        return new FindVehiclesByTypes(repository);
    }

    @Bean
    public GenerateResidentPaymentsReport generateResidentPaymentsReport(
            VehicleRepository vehicleRepository,
            ResidentPaymentRepository residentPaymentRepository
    ) {
        return new GenerateResidentPaymentsReport(vehicleRepository, residentPaymentRepository);
    }


    @Bean
    public ParkingService parkingService(
            RegisterEntry registerEntry,
            RegisterExit registerExit,
            AddOfficialVehicle addOfficialVehicle,
            AddResidentVehicle addResidentVehicle,
            StartMonth startMonth,
            FindAllVehicles findAllVehicles,
            FindVehiclesByTypes findVehiclesByTypes,
            FindVehicleByPlate findVehicleByPlate,
            GenerateResidentPaymentsReport generateReport
    ) {
        return new ParkingService(
                registerEntry,
                registerExit,
                addOfficialVehicle,
                addResidentVehicle,
                startMonth,
                findAllVehicles,
                findVehiclesByTypes,
                findVehicleByPlate,
                generateReport
        );
    }

}
