package dev.Zerphyis.ParkTest.Domain.repositorys;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface VehicleRepository {

    Optional<VehicleDomain> findByPlate(String plate);


    Page<VehicleDomain> findAll(int page, int size);

    Page<VehicleDomain> findByType(TypesVehicles type, int page, int size);

    VehicleDomain save(VehicleDomain vehicle);

    void resetOfficialVehiclesMonth();
}
