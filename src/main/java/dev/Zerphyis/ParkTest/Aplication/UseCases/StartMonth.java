package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Interfaces.StationInterface;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

public class StartMonth {

    private final VehicleRepository vehicleRepository;
    private final StationInterface stationService;

    public StartMonth(VehicleRepository vehicleRepository, StationInterface stationService) {
        this.vehicleRepository = vehicleRepository;
        this.stationService = stationService;
    }

    public void execute() {

        Duration residentBonusDuration = Duration.ofHours(10);

        List<VehicleDomain> vehicles = vehicleRepository.findAll();

        for (VehicleDomain v : vehicles) {

            v.resetAccumulatedTime();
            v.setPending(BigDecimal.ZERO);
            if (v.isOfficial()) {
                stationService.deleteStationsByVehicle(v.getId());
            }

            if (v.isResident()) {
                v.increaseParkingTimeForMonthStart(residentBonusDuration);
            }

            vehicleRepository.save(v);
        }
    }
}
