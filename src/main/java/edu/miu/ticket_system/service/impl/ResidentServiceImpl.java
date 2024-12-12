package edu.miu.ticket_system.service.impl;
import edu.miu.ticket_system.entity.Resident;
import edu.miu.ticket_system.repository.ResidentRepository;
import edu.miu.ticket_system.service.ResidentService;
import java.util.List;
public class ResidentServiceImpl implements ResidentService {
    private ResidentRepository residentRepository;
    @Override
    public List<Resident> findResidentByLastname() {
        return List.of();
    }
    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }
}
