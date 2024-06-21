package com.example.salhuman.controllers;

import com.example.salhuman.models.Employe;
import com.example.salhuman.models.Heure_Travaille;
import com.example.salhuman.services.EmployeService;
import com.example.salhuman.services.HeureTravailleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Heure_TravailleController {

    @Autowired
    private HeureTravailleService heureTravailleService;

    @Autowired
    private EmployeService employeService;

    @GetMapping("/employes")
    public ResponseEntity<List<Employe>> getAllEmployes() {
        List<Employe> employes = (List<Employe>) employeService.getAllEmployes();
        return ResponseEntity.ok(employes);
    }

    @PostMapping("/heuresTravaille")
    public ResponseEntity<Heure_Travaille> saveHeureTravaille(@RequestBody Heure_Travaille heureTravaille) {
        Heure_Travaille savedHeureTravaille = heureTravailleService.saveHeureTravaille(heureTravaille);
        return ResponseEntity.ok(savedHeureTravaille);
    }

    @DeleteMapping("/heuresTravaille/{id}")
    public ResponseEntity<Void> deleteHeureTravaille(@PathVariable Long id) {
        Heure_Travaille heureTravaille = heureTravailleService.getHeureTravailleById(id);
        if (heureTravaille == null) {
            return ResponseEntity.notFound().build();
        }
        heureTravailleService.deleteHeureTravailleById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/heuresTravail/{employeId}")
    public ResponseEntity<List<Heure_Travaille>> listerHeuresTravailEmploye(@PathVariable Long employeId) {
        Employe employe = employeService.getEmployeById(employeId).getEmploye();
        if (employe == null) {
            return ResponseEntity.notFound().build();
        }

        List<Heure_Travaille> heuresTravail = heureTravailleService.getHeuresTravailByEmploye(employeId);
        return ResponseEntity.ok(heuresTravail);
    }

    @PutMapping("/heuresTravaille/{heureTravailleId}/approuver")
    public ResponseEntity<Heure_Travaille> approuverHeureTravaille(@PathVariable Long heureTravailleId) {
        Heure_Travaille heureTravaille = heureTravailleService.getHeureTravailleById(heureTravailleId);
        if (heureTravaille == null) {
            return ResponseEntity.notFound().build();
        }

        heureTravaille.setStatut("APPROVED");
        Heure_Travaille updatedHeureTravaille = heureTravailleService.saveHeureTravaille(heureTravaille);
        return ResponseEntity.ok(updatedHeureTravaille);
    }
}
