package dev.Zerphyis.ParkTest.Domain.Entity.Mapper;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Infra.EntityCore.VehicleEntity;

public class VehicleMapper {

    public static VehicleEntity toEntity(VehicleDomain d) {
        VehicleEntity e = new VehicleEntity();
        e.setPlate(d.getPlate());
        e.normalizePlate();
        e.setType(d.getType());
        e.setEntry(d.getEntry());
        e.setExit(d.getExit());
        e.setAccumulatedTime(d.getAccumulatedTime());
        e.setPending(d.getPending());
        return e;
    }

    public static VehicleDomain toDomain(VehicleEntity e) {
        TypesVehicles type = e.getType() == null ? TypesVehicles.NORESIDENTS : e.getType();
        VehicleDomain d = new VehicleDomain(
                e.getPlate() == null ? "" : e.getPlate(),
                type,
                e.getEntry(),
                e.getExit(),
                e.getAccumulatedTime(),
                e.getPendingSafe()
        );
        return d;
    }
}
