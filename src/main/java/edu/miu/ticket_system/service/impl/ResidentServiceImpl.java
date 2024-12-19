package edu.miu.ticket_system.service.impl;

import edu.miu.ticket_system.entity.Resident;
import edu.miu.ticket_system.repository.ResidentRepository;
import edu.miu.ticket_system.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public List<Resident> findResidentByLastname() {
        return List.of();
    }

    @Override
    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }

    @Override
    public Resident getResidentById(Integer id) {
        return residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found with id: " + id));
    }

    @Override
    public Resident saveResident(Resident resident) {
        resident.setCreatedAt(LocalDateTime.now());
        return residentRepository.save(resident);
    }

    @Override
    public Resident updateResident(Integer id, Resident updatedResident) {
        Resident existingResident = getResidentById(id);
        updatedResident.setId(existingResident.getId());
        return residentRepository.save(updatedResident);
    }

    @Override
    public void deleteResident(Integer id) {
        residentRepository.deleteById(id);
    }
}
