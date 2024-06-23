package com.example.salhuman.controllers;

import com.example.salhuman.models.Fiche_Paie;
import com.example.salhuman.repositories.Fiche_PaieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class DeclarationController {
    @Autowired
    private Fiche_PaieRepository fiche_PaieRepository;

    @GetMapping("/api/declarations")
    public ResponseEntity<Map<String, Object>> genererDeclarations() {
        List<Fiche_Paie> fichesPaie = fiche_PaieRepository.findAll();
        float totalAmo = 0;
        float totalCnss = 0;
        float totalImpot = 0;
        for (Fiche_Paie fichePaie : fichesPaie) {
            totalAmo += fichePaie.getAmo();
            totalCnss += fichePaie.getCnss();
            totalImpot += fichePaie.getImpotSurRevenu();
        }
        Map<String, Object> response = new HashMap<>();
        response.put("fichesPaie", fichesPaie);
        response.put("totalAmo", totalAmo);
        response.put("totalCnss", totalCnss);
        response.put("totalImpot", totalImpot);

        return ResponseEntity.ok(response);
    }
}
