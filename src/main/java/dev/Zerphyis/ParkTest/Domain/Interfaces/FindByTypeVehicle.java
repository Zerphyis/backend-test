package dev.Zerphyis.ParkTest.Domain.Interfaces;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;

import java.util.List;


public interface FindByTypeVehicle {
    List<VehicleDomain> execute(TypesVehicles type);
}
