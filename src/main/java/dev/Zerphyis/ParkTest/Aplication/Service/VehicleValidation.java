package dev.Zerphyis.ParkTest.Aplication.Service;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Infra.Exceptions.DuplicatePlateException;

import java.util.Optional;

public class VehicleValidation {
    private final VehicleRepository repository;

    public VehicleValidation(VehicleRepository repository) {
        this.repository = repository;
    }

    public void assertPlateNotExists(String plate) {
        Optional<VehicleDomain> existing = repository.findByPlate(plate.toUpperCase());

        if (existing.isPresent()) {
            throw new DuplicatePlateException("A placa '" + plate + "' já está cadastrada.");
        }
    }
}
