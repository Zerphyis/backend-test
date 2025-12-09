package dev.Zerphyis.ParkTest.Domain.Entity.Mapper;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Infra.EntityCore.VehicleEntity;

public class VehicleMapper {

    public static VehicleEntity toEntity(VehicleDomain d) {
        if (d == null) return null;

        VehicleEntity e = new VehicleEntity();
        e.setId(d.getId());
        e.setPlate(d.getPlate());
        e.setType(d.getType());
        e.setEntry(d.getEntry());
        e.setExit(d.getExit());
        e.setAccumulatedTime(d.getAccumulatedTime());
        e.setPending(d.getPending());

        return e;
    }

    public static VehicleDomain toDomain(VehicleEntity e) {
        if (e == null) return null;

        TypesVehicles type = e.getType() != null ? e.getType() : TypesVehicles.NORESIDENTS;

        return new VehicleDomain(
                e.getId(),
                e.getPlate(),
                type,
                e.getEntry(),
                e.getExit(),
                e.getAccumulatedTime(),
                e.getPending()
        );
    }
}
