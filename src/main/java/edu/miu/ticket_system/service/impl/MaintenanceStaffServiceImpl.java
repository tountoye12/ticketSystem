package edu.miu.ticket_system.service.impl;

import edu.miu.ticket_system.entity.MaintenanceStaff;
import edu.miu.ticket_system.repository.MaintenanceStaffRepository;
import edu.miu.ticket_system.service.MaintenanceStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MaintenanceStaffServiceImpl implements MaintenanceStaffService {

    @Autowired
    private MaintenanceStaffRepository maintenanceStaffRepository;

    @Override
    public List<MaintenanceStaff> getAllMaintenanceStaff() {
        return maintenanceStaffRepository.findAll();
    }

    @Override
    public MaintenanceStaff getMaintenanceStaffById(Integer id) {
        return maintenanceStaffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance staff not found with id: " + id));
    }

    @Override
    public MaintenanceStaff saveMaintenanceStaff(MaintenanceStaff maintenanceStaff) {
        maintenanceStaff.setCreatedAt(LocalDateTime.now());
        return maintenanceStaffRepository.save(maintenanceStaff);
    }

    @Override
    public MaintenanceStaff updateMaintenanceStaff(Integer id, MaintenanceStaff updatedStaff) {
        MaintenanceStaff existingStaff = getMaintenanceStaffById(id);
        updatedStaff.setId(existingStaff.getId());
        return maintenanceStaffRepository.save(updatedStaff);
    }

    @Override
    public void deleteMaintenanceStaff(Integer id) {
        maintenanceStaffRepository.deleteById(id);
    }
}
