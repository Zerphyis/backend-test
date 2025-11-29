package dev.Zerphyis.ParkTest.Domain.Interfaces;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import org.springframework.data.domain.Page;


public interface FindByTypeVehicle {
    Page<VehicleDomain> execute(TypesVehicles type, int page, int size);
}
