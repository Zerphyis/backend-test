package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Domain.Interfaces.FindByTypeVehicle;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import org.springframework.data.domain.Page;


public class FindVehiclesByTypes implements FindByTypeVehicle {

    private final VehicleRepository repository;
    private static final int PageFix = 4;

    public FindVehiclesByTypes(VehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<VehicleDomain> execute(TypesVehicles type, int page, int size) {

        return repository.findByType(type, PageFix, size);
    }


}
