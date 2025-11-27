package dev.Zerphyis.ParkTest.Infra.Controller;


import dev.Zerphyis.ParkTest.Aplication.Service.ParkingService;
import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping("/entry")
    public ResponseEntity<VehicleDomain> registerEntry(@RequestBody PlateVehicle plate) {
        return ResponseEntity.ok(parkingService.registerEntry(plate));
    }

    @PostMapping("/exit")
    public ResponseEntity<VehicleDomain> registerExit(@RequestBody PlateVehicle plate) {
        return ResponseEntity.ok(parkingService.registerExit(plate));
    }

    @PostMapping("/resident")
    public ResponseEntity<VehicleDomain> addResident(@RequestBody PlateVehicle plate) {
        return ResponseEntity.ok(parkingService.addResident(plate));
    }

    @PostMapping("/official")
    public ResponseEntity<VehicleDomain> addOfficial(@RequestBody PlateVehicle plate) {
        return ResponseEntity.ok(parkingService.addOfficial(plate));
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleDomain>> getAllVehicles(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(parkingService.getAllVehicles(page));
    }

    @GetMapping("/vehicles/{plate}")
    public ResponseEntity<?> getByPlate(@PathVariable String plate) {
        return ResponseEntity.ok(parkingService.getVehicleByPlate(plate));
    }

    @GetMapping("/vehicles/type/{type}")
    public ResponseEntity<List<VehicleDomain>> getByType(@PathVariable TypesVehicles type) {
        return ResponseEntity.ok(parkingService.getVehiclesByType(type));
    }
}