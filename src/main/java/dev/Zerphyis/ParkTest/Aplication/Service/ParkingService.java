package dev.Zerphyis.ParkTest.Aplication.Service;

import dev.Zerphyis.ParkTest.Aplication.UseCases.*;
import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {

    private final RegisterEntry registerEntry;
    private final RegisterExit registerExit;
    private final AddOfficialVehicle addOfficialVehicle;
    private final AddResidentVehicle addResidentVehicle;
    private final StartMonth startMonth;
    private final GenerateResidentPaymentsReport generateReport;

    public ParkingService(RegisterEntry registerEntry, RegisterExit registerExit, AddOfficialVehicle addOfficialVehicle, AddResidentVehicle addResidentVehicle, StartMonth startMonth, GenerateResidentPaymentsReport generateReport) {
        this.registerEntry = registerEntry;
        this.registerExit = registerExit;
        this.addOfficialVehicle = addOfficialVehicle;
        this.addResidentVehicle = addResidentVehicle;
        this.startMonth = startMonth;
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
}


