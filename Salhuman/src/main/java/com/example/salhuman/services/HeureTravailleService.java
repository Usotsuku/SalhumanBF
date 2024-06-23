package com.example.salhuman.services;

import com.example.salhuman.models.Employe;
import com.example.salhuman.models.Heure_Travaille;
import com.example.salhuman.repositories.EmployeRepository;
import com.example.salhuman.repositories.HeureTravailleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HeureTravailleService {

    @Autowired
    private HeureTravailleRepository heureTravailleRepository;

    @Autowired
    private EmployeRepository employeRepository;

    public Heure_Travaille saveHeureTravaille(Heure_Travaille heureTravaille) {
        return heureTravailleRepository.save(heureTravaille);
    }

    public List<Heure_Travaille> getHeuresByEmployeAndDateRange(Long employeId, Date startDate, Date endDate) {
        return heureTravailleRepository.findByEmployeIdAndDateBetween(employeId, startDate, endDate);
    }

    public List<Heure_Travaille> getHeuresByEmploye(Long employeId) {
        Optional<Employe> employe = employeRepository.findById(employeId);;
        return heureTravailleRepository.findByEmploye(employe);
    }
}