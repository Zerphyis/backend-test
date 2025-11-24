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

        validator.assertPlateNotExists(plate.plate());

        VehicleDomain domain = new VehicleDomain(plate.plate(), TypesVehicles.RESIDENTS);

        return repository.save(domain);
    }
}
