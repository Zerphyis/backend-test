package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Domain.Interfaces.FindByTypeVehicle;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;

import java.util.List;

public class FindVehiclesByTypes implements FindByTypeVehicle {

    private final VehicleRepository repository;

    public FindVehiclesByTypes(VehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<VehicleDomain> execute(TypesVehicles type) {
        return repository.findByType(type);
    }
}
