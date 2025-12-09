package dev.Zerphyis.ParkTest.Aplication.Service;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Infra.Exceptions.PlateExistOtherList;

import java.util.Optional;

public class VehicleValidation {

    private final VehicleRepository repository;

    public VehicleValidation(VehicleRepository repository) {
        this.repository = repository;
    }


    public void assertPlateNotExists(String plate, TypesVehicles newType) {
        String normalized = plate.toUpperCase();

        Optional<VehicleDomain> existing = repository.findByPlate(normalized);

        if (existing.isEmpty()) {
            return;
        }

        VehicleDomain vehicle = existing.get();
        TypesVehicles currentType = vehicle.getType();

        if (currentType.equals(TypesVehicles.NORESIDENTS)) {
            return;
        }

        if (currentType.equals(newType)) {
            return;
        }

        throw new PlateExistOtherList(
                "A placa '" + normalized + "' já está cadastrada como " + currentType + "."
        );
    }
}
