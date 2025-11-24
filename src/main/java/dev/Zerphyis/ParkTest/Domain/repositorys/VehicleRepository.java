package dev.Zerphyis.ParkTest.Domain.repositorys;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {

    Optional<VehicleDomain> findByPlate(String plate);

    List<VehicleDomain> findAll(int page);

    VehicleDomain save(VehicleDomain vehicle);
}
