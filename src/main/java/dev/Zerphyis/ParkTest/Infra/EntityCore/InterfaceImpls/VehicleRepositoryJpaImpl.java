package dev.Zerphyis.ParkTest.Infra.EntityCore.InterfaceImpls;

import dev.Zerphyis.ParkTest.Domain.Entity.Mapper.VehicleMapper;
import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Domain.repositorys.jpa.VehicleRepositoryJpa;
import dev.Zerphyis.ParkTest.Infra.EntityCore.VehicleEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<VehicleDomain> findAll() {
        return jpa.findAll()
                .stream()
                .map(VehicleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDomain> findByType(TypesVehicles type) {
        return jpa.findByType(type)
                .stream()
                .map(VehicleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public VehicleDomain save(VehicleDomain domain) {
        VehicleEntity entity = VehicleMapper.toEntity(domain);
        VehicleEntity saved = jpa.save(entity);
        return VehicleMapper.toDomain(saved);
    }

    @Override
    public void resetOfficialVehiclesMonth() {
        jpa.resetOfficialVehiclesMonth();
    }
}
