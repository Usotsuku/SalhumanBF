package com.example.salhuman.services;

import com.example.salhuman.models.Conge;
import com.example.salhuman.models.Employe;
import com.example.salhuman.models.Fiche_Paie;
import com.example.salhuman.models.Heure_Travaille;
import com.example.salhuman.repositories.CongeRepository;
import com.example.salhuman.repositories.EmployeRepository;
import com.example.salhuman.repositories.HeureTravailleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    CongeRepository congeRepository;

    public Employe saveEmploye(Employe employe) {
        return employeRepository.save(employe);
    }


    public Employe updateEmploye(Employe employe) {
        return employeRepository.save(employe);
    }


    public Employe getEmploye(Long id) {
        return employeRepository.findById(id).get();
    }


    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }


    public void deleteEmployeById(Long id) {
        employeRepository.deleteById(id);
    }


    public void deleteAllEmployes() {
        employeRepository.deleteAll();
    }


    public Employe getEmployeById(long id) {
        Optional<Employe> optionalEmploye = employeRepository.findById(id);
        return optionalEmploye.orElse(null);
    }



    public void demanderConge(Conge conge) {
        congeRepository.save(conge);
    }


    public Fiche_Paie getLatestfichpaie(Employe employe) {

        List<Fiche_Paie> fiches = employe.getFiches();
        if (fiches.isEmpty()) {
            return null; // No payslips available
        }

        // Sort the list of payslips by date in descending order
        fiches.sort(Comparator.comparing(Fiche_Paie::getPeriode).reversed());

        // Return the first (most recent) payslip
        return fiches.get(0);
    }


    @Autowired
    private HeureTravailleRepository heureTravailRepository;


    public List<Heure_Travaille> getHeuresTravailByEmploye(Employe employe) {

        return heureTravailRepository.findByEmploye(employe);
    }
    @Transactional
    public void approuverConge(Conge conge) {
        conge.setStatuts("APPROUVED");
        congeRepository.save(conge);
    }
    public Conge getDemandeCongeById(Long congeId) {
        return congeRepository.findById(congeId).orElse(null);
    }
}