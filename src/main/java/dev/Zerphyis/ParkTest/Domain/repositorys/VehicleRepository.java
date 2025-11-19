package dev.Zerphyis.ParkTest.Domain.repositorys;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;

public interface VehicleRepository {
    VehicleDomain registerEntry(PlateVehicle entry);
    VehicleDomain registerExit(PlateVehicle exit);
}
