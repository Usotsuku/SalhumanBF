package com.example.salhuman.services;

import com.example.salhuman.models.Employe;
import com.example.salhuman.models.Heure_Travaille;
import com.example.salhuman.repositories.EmployeRepository;
import com.example.salhuman.repositories.HeureTravailleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    public Heure_Travaille updateHeureTravaille(Heure_Travaille heureTravaille) {
        return heureTravailleRepository.save(heureTravaille);
    }

    public Heure_Travaille getHeureTravailleById(Long id) {
        return heureTravailleRepository.findById(id).orElse(null);
    }

    public Page<Heure_Travaille> getAllHeuresTravailleByPage(int page, int size) {
        return heureTravailleRepository.findAll(PageRequest.of(page, size));
    }

    public void deleteHeureTravailleById(Long id) {
        heureTravailleRepository.deleteById(id);
    }

    public void deleteAllHeuresTravaille() {
        heureTravailleRepository.deleteAll();
    }

    public List<Heure_Travaille> getHeuresTravailByEmploye(Long employeId) {
        Optional<Employe> employeOptional = employeRepository.findById(employeId);
        return employeOptional.map(heureTravailleRepository::findByEmploye).orElse(null);
    }

    @Transactional
    public void approuverHeureTravaille(Long heureTravailleId) {
        Heure_Travaille heureTravaille = getHeureTravailleById(heureTravailleId);
        if (heureTravaille != null) {
            heureTravaille.setStatut("APPROVED");
            saveHeureTravaille(heureTravaille);
        }
    }
}
