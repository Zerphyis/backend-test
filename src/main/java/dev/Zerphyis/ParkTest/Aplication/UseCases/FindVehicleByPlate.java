package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Infra.Exceptions.VehiclePlateNotFoundException;

import java.util.Optional;

public class FindVehicleByPlate {

    private final VehicleRepository repository;

    public FindVehicleByPlate(VehicleRepository repository) {
        this.repository = repository;
    }

    public Optional<VehicleDomain> execute(String plate) {

        if (plate == null || plate.isBlank()) {
            throw new VehiclePlateNotFoundException("Placa inválida.");
        }

        return repository.findByPlate(plate.toUpperCase());
    }
}
