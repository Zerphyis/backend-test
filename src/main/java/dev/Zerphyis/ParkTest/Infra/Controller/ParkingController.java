package dev.Zerphyis.ParkTest.Infra.Controller;

import dev.Zerphyis.ParkTest.Aplication.Service.ParkingService;
import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<VehicleDomain>> getAllVehicles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        return ResponseEntity.ok(parkingService.getAllVehicles(page, size));
    }

    @GetMapping("/vehicles/{plate}")
    public ResponseEntity<?> getByPlate(@PathVariable String plate) {
        return parkingService.getVehicleByPlate(plate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/vehicles/type/{type}")
    public ResponseEntity<Page<VehicleDomain>> getByType(
            @PathVariable TypesVehicles type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        return ResponseEntity.ok(parkingService.getVehiclesByType(type, page, size));
    }
}
