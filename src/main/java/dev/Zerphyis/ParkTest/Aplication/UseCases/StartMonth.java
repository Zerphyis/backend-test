package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;

public class StartMonth {
    private final VehicleRepository repository;

    public StartMonth(VehicleRepository repository) {
        this.repository = repository;
    }

    public void execute() {
        repository.startMonth();
    }
}
