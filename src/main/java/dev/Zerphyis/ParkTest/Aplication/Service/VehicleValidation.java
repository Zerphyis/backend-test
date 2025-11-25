package dev.Zerphyis.ParkTest.Aplication.Service;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Infra.Exceptions.DuplicatePlateException;
import dev.Zerphyis.ParkTest.Infra.Exceptions.PlateExistOtherList;

import java.util.Optional;

public class VehicleValidation {
    private final VehicleRepository repository;

    public VehicleValidation(VehicleRepository repository) {
        this.repository = repository;
    }

    public void assertPlateNotExists(String plate) {
        String normalized = plate.toUpperCase();

        Optional<VehicleDomain> existing = repository.findByPlate(normalized);

        if (existing.isPresent()) {
            throw new DuplicatePlateException(
                    "A placa '" + normalized + "' já está cadastrada."
            );
        }
    }

    public void assertPlateNotInOtherList(String plate, TypesVehicles expectedType) {
        String normalized = plate.toUpperCase();

        Optional<VehicleDomain> existing = repository.findByPlate(normalized);

        if (existing.isEmpty()) {
            return;
        }

        VehicleDomain vehicle = existing.get();

        if (!vehicle.getType().equals(expectedType)) {
            throw new PlateExistOtherList(
                    "A placa '" + normalized + "' já está cadastrada como " + vehicle.getType() + "."
            );
        }
    }
}
