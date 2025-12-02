package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;

import java.math.BigDecimal;
import java.util.List;

public class StartMonth {

    private final VehicleRepository repository;

    public StartMonth(VehicleRepository repository) {
        this.repository = repository;
    }

    public void execute() {

        List<VehicleDomain> vehicles = repository.findAll();

        for (VehicleDomain v : vehicles) {
            v.resetAccumulatedTime();
            v.setPending(BigDecimal.ZERO);
            repository.save(v);
        }
    }
}
