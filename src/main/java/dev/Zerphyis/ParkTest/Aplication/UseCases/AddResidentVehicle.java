package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Aplication.Service.VehicleValidation;
import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;

public class AddResidentVehicle {

    private final VehicleRepository repository;
    private final VehicleValidation validator;

    public AddResidentVehicle(VehicleRepository repository) {
        this.repository = repository;
        this.validator = new VehicleValidation(repository);
    }

    public VehicleDomain execute(PlateVehicle plate) {

        String plateStr = plate.plate().toUpperCase();
        validator.assertPlateNotExists(plateStr);

        VehicleDomain domain = new VehicleDomain(plateStr, TypesVehicles.RESIDENTS);
        return repository.save(domain);
    }
}
