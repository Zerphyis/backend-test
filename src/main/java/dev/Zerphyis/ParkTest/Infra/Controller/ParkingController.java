package dev.Zerphyis.ParkTest.Infra.Controller;

import dev.Zerphyis.ParkTest.Aplication.Service.ParkingService;
import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.PlateVehicle;
import dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos.ResponseVehicle;
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

    private ResponseVehicle toResponseDTO(VehicleDomain domain) {
        return ResponseVehicle.fromDomain(domain);
    }


    @PostMapping("/entry")
    public ResponseEntity<ResponseVehicle> registerEntry(@RequestBody PlateVehicle plate) {
        return ResponseEntity.ok(toResponseDTO(parkingService.registerEntry(plate)));
    }

    @PostMapping("/exit")
    public ResponseEntity<ResponseVehicle> registerExit(@RequestBody PlateVehicle plate) {
        return ResponseEntity.ok(toResponseDTO(parkingService.registerExit(plate)));
    }

    @PostMapping("/resident")
    public ResponseEntity<ResponseVehicle> addResident(@RequestBody PlateVehicle plate) {
        return ResponseEntity.ok(toResponseDTO(parkingService.addResident(plate)));
    }

    @PostMapping("/official")
    public ResponseEntity<ResponseVehicle> addOfficial(@RequestBody PlateVehicle plate) {
        return ResponseEntity.ok(toResponseDTO(parkingService.addOfficial(plate)));
    }


    @GetMapping("/vehicles")
    public ResponseEntity<Page<ResponseVehicle>> getAllVehicles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        Page<VehicleDomain> domainPage = parkingService.getAllVehicles(page, size);
        Page<ResponseVehicle> dtoPage = domainPage.map(this::toResponseDTO);
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/vehicles/{plate}")
    public ResponseEntity<?> getByPlate(@PathVariable String plate) {
        return parkingService.getVehicleByPlate(plate)
                .map(this::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/vehicles/type/{type}")
    public ResponseEntity<Page<ResponseVehicle>> getByType(
            @PathVariable TypesVehicles type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        Page<VehicleDomain> domainPage = parkingService.getVehiclesByType(type, page, size);
        Page<ResponseVehicle> dtoPage = domainPage.map(this::toResponseDTO);
        return ResponseEntity.ok(dtoPage);
    }

    @PostMapping("/admin/start-month")
    public ResponseEntity<Void> startNewMonth() {
        parkingService.startNewMonth();
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/admin/report")
    public ResponseEntity<String> generateResidentReport(
            @RequestParam(defaultValue = "resident_payments.txt") String filename
    ) {
        parkingService.generateResidentReport(filename);
        return ResponseEntity.ok("Relatório gerado com sucesso no arquivo: " + filename);
    }
}