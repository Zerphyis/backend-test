package dev.Zerphyis.ParkTest.Aplication.Service;

import dev.Zerphyis.ParkTest.Aplication.UseCases.*;
import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;

import java.util.List;
import java.util.Optional;

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

    public VehicleDomain registerEntry(PlateVehicle plate) {
        return registerEntryUseCase.execute(plate);
    }

    public VehicleDomain registerExit(PlateVehicle plate) {
        return registerExitUseCase.execute(plate);
    }

    public VehicleDomain addResident(PlateVehicle plate) {
        return addResidentVehicleUseCase.execute(plate);
    }

    public VehicleDomain addOfficial(PlateVehicle plate) {
        return addOfficialVehicleUseCase.execute(plate);
    }

    public void startNewMonth() {
        startMonthUseCase.execute();
    }

    public void generateResidentReport(String filename) {
        generateReportUseCase.execute(filename);
    }

    public List<VehicleDomain> getAllVehicles(int page) {
        return findAllVehiclesUseCase.execute(page);
    }

    public Optional<VehicleDomain> getVehicleByPlate(String plate) {
        return findVehicleByPlateUseCase.execute(plate);
    }

    public List<VehicleDomain> getVehiclesByType(TypesVehicles type) {
        return findVehiclesByTypesUseCase.execute(type);
    }
}
