package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;

public class AddResidentVehicle {
    private final VehicleRepository repository;

    public AddResidentVehicle(VehicleRepository repository) {
        this.repository = repository;
    }

    public VehicleDomain execute(PlateVehicle plate){
        return repository.addResident(plate);
    }
}
