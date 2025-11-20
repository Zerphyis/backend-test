package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;

public class AddOfficialVehicle {
    private final VehicleRepository repository;

    public AddOfficialVehicle(VehicleRepository repository) {
        this.repository = repository;
    }
    public VehicleDomain execute(PlateVehicle plate){
        return  repository.addOfficial(plate);
    }
}
