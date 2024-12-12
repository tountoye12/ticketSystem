package edu.miu.ticket_system.repository;

import edu.miu.ticket_system.entity.MaintenanceStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MaintenanceStaffRepository extends JpaRepository<MaintenanceStaff, Integer> {
}
