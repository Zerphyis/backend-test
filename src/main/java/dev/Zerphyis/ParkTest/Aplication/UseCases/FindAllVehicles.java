package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;

import java.util.List;

public class FindAllVehicles {

    private final VehicleRepository repository;

    public FindAllVehicles(VehicleRepository repository) {
        this.repository = repository;
    }

    public List<VehicleDomain> execute() {
        return repository.findAll();
    }
}
