package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Aplication.Service.VehicleValidation;
import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;

public class RegisterEntry {

    private final VehicleRepository repository;
    private final VehicleValidation validator;

    public RegisterEntry(VehicleRepository repository) {
        this.repository = repository;
        this.validator = new VehicleValidation(repository);
    }

    public VehicleDomain execute(PlateVehicle plateDto) {
        String plate = plateDto.plate().toUpperCase();

        validator.assertPlateNotExists(plate, TypesVehicles.NORESIDENTS);

        VehicleDomain vehicle = repository.findByPlate(plate)
                .orElseGet(() -> new VehicleDomain(plate, TypesVehicles.NORESIDENTS));

        vehicle.assertNotInside();
        vehicle.markEntryNow();

        return repository.save(vehicle);
    }
}
