package edu.miu.ticket_system.repository;

import edu.miu.ticket_system.entity.LeaseOfficeStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LeaseOfficeStaffRepository extends JpaRepository<LeaseOfficeStaff, Integer> {
}
