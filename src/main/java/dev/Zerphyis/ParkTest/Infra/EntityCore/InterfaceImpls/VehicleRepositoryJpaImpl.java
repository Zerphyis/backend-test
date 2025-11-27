package dev.Zerphyis.ParkTest.Infra.EntityCore.InterfaceImpls;

import dev.Zerphyis.ParkTest.Domain.Entity.Mapper.VehicleMapper;
import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Domain.repositorys.jpa.VehicleRepositoryJpa;
import dev.Zerphyis.ParkTest.Infra.EntityCore.VehicleEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VehicleRepositoryJpaImpl implements VehicleRepository {

    private final VehicleRepositoryJpa jpa;

    public VehicleRepositoryJpaImpl(@Lazy VehicleRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<VehicleDomain> findByPlate(String plate) {
        return jpa.findByPlate(plate)
                .map(VehicleMapper::toDomain);
    }

    @Override
    public List<VehicleDomain> findAll(int page) {
        return jpa.findAll().stream()
                .map(VehicleMapper::toDomain)
                .toList();
    }

    @Override
    public VehicleDomain save(VehicleDomain vehicle) {
        VehicleEntity entity = VehicleMapper.toEntity(vehicle);
        return VehicleMapper.toDomain(jpa.save(entity));
    }

    @Override
    public void resetOfficialVehiclesMonth() {
        jpa.resetOfficialVehiclesMonth();
    }
}
