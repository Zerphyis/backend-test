package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;

public class RegisterEntry {

    private final VehicleRepository repository;

    public RegisterEntry(VehicleRepository repository) {
        this.repository = repository;
    }

    public VehicleDomain execute(PlateVehicle plate) {
        return repository.registerEntry(plate);
    }
}
