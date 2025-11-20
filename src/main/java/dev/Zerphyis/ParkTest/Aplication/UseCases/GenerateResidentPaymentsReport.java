package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;

public class GenerateResidentPaymentsReport {
    private final VehicleRepository repository;

    public GenerateResidentPaymentsReport(VehicleRepository repository) {
        this.repository = repository;
    }

    public void execute(String filename) {
        repository.generateResidentPayments(filename);
    }
}
