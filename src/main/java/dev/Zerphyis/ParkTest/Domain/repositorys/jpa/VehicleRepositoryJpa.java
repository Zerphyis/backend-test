package dev.Zerphyis.ParkTest.Domain.repositorys.jpa;

import dev.Zerphyis.ParkTest.Infra.EntityCore.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepositoryJpa extends JpaRepository<VehicleEntity,Long> {
    Optional<VehicleEntity> findByPlate(String plate);
}
