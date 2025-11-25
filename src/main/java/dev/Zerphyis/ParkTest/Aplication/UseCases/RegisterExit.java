package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;
import dev.Zerphyis.ParkTest.Infra.Exceptions.VehiclePlateNotFoundException;

import java.time.Duration;

public class RegisterExit {

    private final VehicleRepository repository;

    public RegisterExit(VehicleRepository repository) {
        this.repository = repository;
    }

    public VehicleDomain execute(PlateVehicle plateDto) {
        String plate = plateDto.plate().toUpperCase();

        VehicleDomain vehicle = repository.findByPlate(plate)
                .orElseThrow(() -> new VehiclePlateNotFoundException("Veículo não encontrado"));

        vehicle.assertHasEntry();
        vehicle.markExitNow();

        Duration visit = vehicle.currentVisitDuration();
        vehicle.addAccumulated(visit);

        vehicle.clearEntryExit();

        return repository.save(vehicle);
    }
}
