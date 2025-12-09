package dev.Zerphyis.ParkTest.Infra.EntityCore.InterfaceImpls;

import dev.Zerphyis.ParkTest.Domain.Interfaces.StationInterface;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;

public class StationInterfaceImpl implements StationInterface {

    private final VehicleRepository repository;

    public StationInterfaceImpl(VehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteStationsByVehicle(Long vehicleId) {
        if (vehicleId == null) {
            return;
        }

        repository.deleteStationsByVehicle(vehicleId);
    }
}
