package edu.miu.ticket_system.controller;

import edu.miu.ticket_system.entity.Resident;
import edu.miu.ticket_system.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/residents")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @GetMapping
    public List<Resident> getAllResidents() {
        return residentService.getAllResidents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resident> getResidentById(@PathVariable Integer id) {
        Resident resident = residentService.getResidentById(id);
        return ResponseEntity.ok(resident);
    }

    @PostMapping
    public ResponseEntity<Resident> createResident(@RequestBody Resident resident) {
        Resident savedResident = residentService.saveResident(resident);
        return ResponseEntity.ok(savedResident);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resident> updateResident(@PathVariable Integer id, @RequestBody Resident updatedResident) {
        Resident resident = residentService.updateResident(id, updatedResident);
        return ResponseEntity.ok(resident);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResident(@PathVariable Integer id) {
        residentService.deleteResident(id);
        return ResponseEntity.ok("Resident deleted successfully");
    }
}
