package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public class StartMonth {

    private final VehicleRepository repository;

    public StartMonth(VehicleRepository repository) {
        this.repository = repository;
    }

    public void execute() {

        int page = 0;
        int size = 100;

        Page<VehicleDomain> result;

        do {
            result = repository.findAll(page, size);

            for (VehicleDomain v : result.getContent()) {
                v.resetAccumulatedTime();
                v.setPending(BigDecimal.ZERO);
                repository.save(v);
            }

            page++;

        } while (!result.isLast());
    }
}
