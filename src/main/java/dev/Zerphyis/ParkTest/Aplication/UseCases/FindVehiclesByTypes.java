package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Domain.Interfaces.FindByTypeVehicle;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class FindVehiclesByTypes implements FindByTypeVehicle {

    private final VehicleRepository repository;

    public FindVehiclesByTypes(VehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<VehicleDomain> execute(TypesVehicles type, int page, int size) {

        Page<VehicleDomain> fullPage = repository.findAll(page, size);

        List<VehicleDomain> filtered = fullPage.getContent().stream()
                .filter(v -> v.getType() == type)
                .toList();

        return new PageImpl<>(filtered, PageRequest.of(page, size), filtered.size());
    }


}
