package dev.Zerphyis.ParkTest.Domain.repositorys.jpa;

import dev.Zerphyis.ParkTest.Infra.EntityCore.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VehicleRepositoryJpa extends JpaRepository<VehicleEntity,Long> {
    Optional<VehicleEntity> findByPlate(String plate);

    @Modifying
    @Query("UPDATE VehicleEntity v SET v.totalStays = 0 WHERE v.type = 'OFFICIAL'")
    void deleteAllStaysOfOfficialVehicles();
}
