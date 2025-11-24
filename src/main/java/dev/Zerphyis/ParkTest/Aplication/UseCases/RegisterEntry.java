package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;
import dev.Zerphyis.ParkTest.Infra.Exceptions.VehiclePlateNotFoundException;

public class RegisterEntry {

    private final VehicleRepository repository;

    public RegisterEntry(VehicleRepository repository) {
        this.repository = repository;
    }

    public VehicleDomain execute(PlateVehicle plateDto) {
        String plate = plateDto.plate().toUpperCase();
        VehicleDomain vehicle =repository.findByPlate(plate).orElseThrow(() -> new VehiclePlateNotFoundException("Veículo não encontrado"));

        vehicle.assertNotInside();
        vehicle.markEntryNow();

        return repository.save(vehicle);
    }
}
