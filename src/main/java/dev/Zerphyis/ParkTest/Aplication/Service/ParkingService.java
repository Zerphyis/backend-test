package dev.Zerphyis.ParkTest.Aplication.Service;

import dev.Zerphyis.ParkTest.Aplication.UseCases.*;
import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;

import java.util.List;
import java.util.Optional;

public class ParkingService {

    private final RegisterEntry registerEntry;
    private final RegisterExit registerExit;
    private final AddOfficialVehicle addOfficialVehicle;
    private final AddResidentVehicle addResidentVehicle;
    private final StartMonth startMonth;
    private final FindAllVehicles findAllVehicles;
    private final FindVehiclesByTypes findVehiclesByTypes;
    private final FindVehicleByPlate findVehicleByPlate;
    private final GenerateResidentPaymentsReport generateReport;

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
        this.registerEntry = registerEntry;
        this.registerExit = registerExit;
        this.addOfficialVehicle = addOfficialVehicle;
        this.addResidentVehicle = addResidentVehicle;
        this.startMonth = startMonth;
        this.findAllVehicles = findAllVehicles;
        this.findVehiclesByTypes = findVehiclesByTypes;
        this.findVehicleByPlate = findVehicleByPlate;
        this.generateReport = generateReport;
    }

    public VehicleDomain registerEntry(PlateVehicle plate) {
        return registerEntry.execute(plate);
    }

    public VehicleDomain registerExit(PlateVehicle plate) {
        return registerExit.execute(plate);
    }

    public VehicleDomain addResident(PlateVehicle plate) {
        return addResidentVehicle.execute(plate);
    }

    public VehicleDomain addOfficial(PlateVehicle plate) {
        return addOfficialVehicle.execute(plate);
    }

    public void startNewMonth() {
        startMonth.execute();
    }

    public void generateResidentReport(String filename) {
        generateReport.execute(filename);
    }

    public List<VehicleDomain> getAllVehicles(int page) {
        return findAllVehicles.execute(page);
    }

    public Optional<VehicleDomain> getVehicleByPlate(String plate) {
        return findVehicleByPlate.execute(plate);
    }

    public List<VehicleDomain> getVehiclesByType(TypesVehicles type) {
        return findVehiclesByTypes.execute(type);
    }
}
