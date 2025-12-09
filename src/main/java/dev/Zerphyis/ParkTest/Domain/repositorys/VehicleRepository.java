package dev.Zerphyis.ParkTest.Domain.repositorys;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {

    Optional<VehicleDomain> findByPlate(String plate);

    List<VehicleDomain> findAll();

    List<VehicleDomain> findByType(TypesVehicles type);

    VehicleDomain save(VehicleDomain vehicle);


    void deleteStationsByVehicle(Long vehicleId);
}
