package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import org.springframework.data.domain.Page;

public class FindAllVehicles {

    private final VehicleRepository repository;
    private static final int PageFix = 4;

    public FindAllVehicles(VehicleRepository repository) {
        this.repository = repository;
    }

    public Page<VehicleDomain> execute(int page, int size) {
        return repository.findAll(PageFix, size);
    }
}
