package edu.miu.ticket_system.service;

import edu.miu.ticket_system.entity.MaintenanceStaff;

import java.util.List;

public interface MaintenanceStaffService {

    List<MaintenanceStaff> getAllMaintenanceStaff();
    MaintenanceStaff getMaintenanceStaffById(Integer id);
    MaintenanceStaff saveMaintenanceStaff(MaintenanceStaff maintenanceStaff);
    MaintenanceStaff updateMaintenanceStaff(Integer id, MaintenanceStaff updatedStaff);
    void deleteMaintenanceStaff(Integer id);
}
