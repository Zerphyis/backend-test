package dev.Zerphyis.ParkTest.Domain.repositorys.jpa;

import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Infra.EntityCore.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface VehicleRepositoryJpa extends JpaRepository<VehicleEntity, Long> {

    Optional<VehicleEntity> findByPlate(String plate);

    List<VehicleEntity> findByType(TypesVehicles type);

    @Modifying
    @Transactional
    @Query("DELETE FROM VehicleEntity v WHERE v.id = :vehicleId")
    void deleteByIdCustom(@Param("vehicleId") Long vehicleId);


}
