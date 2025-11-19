package dev.Zerphyis.ParkTest.Domain.Entity.Mapper;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Infra.EntityCore.VehicleEntity;

public class VehicleMapper {

    public static VehicleEntity toEntity(VehicleDomain domain){
        VehicleEntity entity= new VehicleEntity();
        entity.setPlate(domain.getPlate());
        entity.setEntry(domain.getEntry());
        entity.setExit(domain.getExit());
        entity.setPending(domain.getPending());
        return  entity;
    }


    public static VehicleDomain toDomain(VehicleEntity entity) {
        VehicleDomain domain = new VehicleDomain(entity.getPlate());
        domain.registerExit();
        domain.updatePending(entity.getPending());

        try {
            var exitField = VehicleDomain.class.getDeclaredField("exit");
            exitField.setAccessible(true);
            exitField.set(domain, entity.getExit());

            var entryField = VehicleDomain.class.getDeclaredField("entry");
            entryField.setAccessible(true);
            entryField.set(domain, entity.getEntry());
        } catch (Exception ignored) {}

        return domain;
    }

}
