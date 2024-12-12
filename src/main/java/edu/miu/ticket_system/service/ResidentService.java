package edu.miu.ticket_system.service;


import edu.miu.ticket_system.entity.Resident;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResidentService {

    List<Resident> findResidentByLastname();
    List<Resident> getAllResidents();
    Resident getResidentById(Integer id);
    Resident saveResident(Resident resident);
    Resident updateResident(Integer id, Resident updatedResident);
    void deleteResident(Integer id);
}
