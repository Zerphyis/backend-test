package dev.Zerphyis.ParkTest.Infra.EntityCore.RepoImpl;

import dev.Zerphyis.ParkTest.Domain.Entity.Mapper.VehicleMapper;
import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Domain.repositorys.jpa.VehicleRepositoryJpa;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;
import dev.Zerphyis.ParkTest.Infra.EntityCore.VehicleEntity;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryImplements  implements VehicleRepository {
     private final VehicleRepositoryJpa repositoryJpa;

    public RepositoryImplements(VehicleRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public VehicleDomain registerEntry(PlateVehicle dto) {
        VehicleDomain domain = new VehicleDomain(dto.plate());
        VehicleEntity saved = repositoryJpa.save(VehicleMapper.toEntity(domain));
        return VehicleMapper.toDomain(saved);
    }

    @Override
    public VehicleDomain registerExit(PlateVehicle dto) {
        VehicleEntity entity = repositoryJpa.findByPlate(dto.plate().toUpperCase())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        entity.setExit(java.time.LocalDateTime.now());
        VehicleEntity saved = repositoryJpa.save(entity);

        return VehicleMapper.toDomain(saved);
    }
}
