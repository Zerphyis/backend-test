package dev.Zerphyis.ParkTest.Aplication.Service;

import dev.Zerphyis.ParkTest.Aplication.UseCases.*;
import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;
import java.util.Optional;

import static dev.Zerphyis.ParkTest.Domain.Enums.CurrencyType.BRL;

public class ParkingService {

    private final RegisterEntry registerEntryUseCase;
    private final RegisterExit registerExitUseCase;
    private final AddOfficialVehicle addOfficialVehicleUseCase;
    private final AddResidentVehicle addResidentVehicleUseCase;
    private final StartMonth startMonthUseCase;
    private final FindAllVehicles findAllVehiclesUseCase;
    private final FindVehiclesByTypes findVehiclesByTypesUseCase;
    private final FindVehicleByPlate findVehicleByPlateUseCase;
    private final GenerateResidentPaymentsReport generateReportUseCase;

    public ParkingService(
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
        this.registerEntryUseCase = registerEntry;
        this.registerExitUseCase = registerExit;
        this.addOfficialVehicleUseCase = addOfficialVehicle;
        this.addResidentVehicleUseCase = addResidentVehicle;
        this.startMonthUseCase = startMonth;
        this.findAllVehiclesUseCase = findAllVehicles;
        this.findVehiclesByTypesUseCase = findVehiclesByTypes;
        this.findVehicleByPlateUseCase = findVehicleByPlate;
        this.generateReportUseCase = generateReport;
    }

    @Caching(evict = {
            @CacheEvict(value = "vehicles_all", allEntries = true),
            @CacheEvict(value = "vehicles_by_plate", key = "#plate.plate"),
            @CacheEvict(value = "vehicles_by_type", allEntries = true)
    })
    public VehicleDomain registerEntry(PlateVehicle plate) {
        return registerEntryUseCase.execute(plate);
    }

    @Caching(evict = {
            @CacheEvict(value = "vehicles_all", allEntries = true),
            @CacheEvict(value = "vehicles_by_plate", key = "#plate.plate"),
            @CacheEvict(value = "vehicles_by_type", allEntries = true)
    })
    public VehicleDomain registerExit(PlateVehicle plate) {
        return registerExitUseCase.execute(plate);
    }

    @Caching(evict = {
            @CacheEvict(value = "vehicles_all", allEntries = true),
            @CacheEvict(value = "vehicles_by_plate", key = "#plate.plate"),
            @CacheEvict(value = "vehicles_by_type", allEntries = true)
    })
    public VehicleDomain addResident(PlateVehicle plate) {
        return addResidentVehicleUseCase.execute(plate);
    }

    @Caching(evict = {
            @CacheEvict(value = "vehicles_all", allEntries = true),
            @CacheEvict(value = "vehicles_by_plate", key = "#plate.plate"),
            @CacheEvict(value = "vehicles_by_type", allEntries = true)
    })
    public VehicleDomain addOfficial(PlateVehicle plate) {
        return addOfficialVehicleUseCase.execute(plate);
    }

    @Caching(evict = {
            @CacheEvict(value = "vehicles_all", allEntries = true),
            @CacheEvict(value = "vehicles_by_plate", allEntries = true),
            @CacheEvict(value = "vehicles_by_type", allEntries = true)
    })
    public void startNewMonth() {
        startMonthUseCase.execute();
    }

    public void generateResidentReport(String filename) {
        generateReportUseCase.execute(filename, BRL);
    }



    @Cacheable("vehicles_all")
    public List<VehicleDomain> getAllVehicles() {
        return findAllVehiclesUseCase.execute();
    }

    @Cacheable(value = "vehicles_by_plate", key = "#plate")
    public Optional<VehicleDomain> getVehicleByPlate(String plate) {
        return findVehicleByPlateUseCase.execute(plate);
    }

    @Cacheable(value = "vehicles_by_type", key = "#type")
    public List<VehicleDomain> getVehiclesByType(TypesVehicles type) {
        return findVehiclesByTypesUseCase.execute(type);
    }
}
