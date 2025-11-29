package dev.Zerphyis.ParkTest.Infra.EntityCore.InterfaceImpls;

import dev.Zerphyis.ParkTest.Domain.Entity.Mapper.VehicleMapper;
import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Domain.repositorys.jpa.VehicleRepositoryJpa;
import dev.Zerphyis.ParkTest.Infra.EntityCore.VehicleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class VehicleRepositoryJpaImpl implements VehicleRepository {

    private final VehicleRepositoryJpa jpa;

    public VehicleRepositoryJpaImpl(VehicleRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<VehicleDomain> findByPlate(String plate) {
        return jpa.findByPlate(plate)
                .map(VehicleMapper::toDomain);
    }

    @Override
    public Page<VehicleDomain> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jpa.findAll(pageable)
                .map(VehicleMapper::toDomain);
    }

    @Override
    public VehicleDomain save(VehicleDomain vehicle) {
        VehicleEntity entity = VehicleMapper.toEntity(vehicle);
        VehicleEntity saved = jpa.save(entity);
        return VehicleMapper.toDomain(saved);
    }

    @Override
    public void resetOfficialVehiclesMonth() {
        jpa.resetOfficialVehiclesMonth();
    }
}
