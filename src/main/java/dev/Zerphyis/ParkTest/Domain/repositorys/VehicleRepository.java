package dev.Zerphyis.ParkTest.Domain.repositorys;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {

    Optional<VehicleDomain> findByPlate(String plate);

    List<VehicleDomain> findAll();

    VehicleDomain save(VehicleDomain vehicle);

    VehicleDomain update(VehicleDomain vehicle);


    VehicleDomain registerEntry(PlateVehicle plate);

    VehicleDomain registerExit(PlateVehicle plate);

    VehicleDomain addOfficial(PlateVehicle plate);
    VehicleDomain addResident(PlateVehicle plate);

    void startMonth();

    void generateResidentPayments(String filename);

    void deleteAllStaysOfOfficialVehicles();
}
