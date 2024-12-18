package edu.miu.ticket_system.controller;

import edu.miu.ticket_system.entity.MaintenanceStaff;
import edu.miu.ticket_system.service.MaintenanceStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/maintenance-staff")
public class MaintenanceStaffController {

    @Autowired
    private MaintenanceStaffService maintenanceStaffService;

    @GetMapping
    public List<MaintenanceStaff> getAllMaintenanceStaff() {
        return maintenanceStaffService.getAllMaintenanceStaff();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceStaff> getMaintenanceStaffById(@PathVariable Integer id) {
        MaintenanceStaff staff = maintenanceStaffService.getMaintenanceStaffById(id);
        return ResponseEntity.ok(staff);
    }

    @PostMapping
    public ResponseEntity<MaintenanceStaff> createMaintenanceStaff(@RequestBody MaintenanceStaff maintenanceStaff) {
        maintenanceStaff.setCreatedAt(LocalDateTime.now());
        maintenanceStaff.setUpdatedAt(LocalDateTime.now());
        MaintenanceStaff savedStaff = maintenanceStaffService.saveMaintenanceStaff(maintenanceStaff);
        return ResponseEntity.ok(savedStaff);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceStaff> updateMaintenanceStaff(@PathVariable Integer id,
                                                                   @RequestBody MaintenanceStaff updatedStaff) {
        updatedStaff.setUpdatedAt(LocalDateTime.now());
        MaintenanceStaff staff = maintenanceStaffService.updateMaintenanceStaff(id, updatedStaff);
        return ResponseEntity.ok(staff);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMaintenanceStaff(@PathVariable Integer id) {
        maintenanceStaffService.deleteMaintenanceStaff(id);
        return ResponseEntity.ok("Maintenance staff deleted successfully");
    }
}
