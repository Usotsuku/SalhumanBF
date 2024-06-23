package com.example.salhuman.controllers;

import com.example.salhuman.dto.FichePaieDTO;

import com.example.salhuman.models.Element_Salaire;

import com.example.salhuman.models.Fiche_Paie;
import com.example.salhuman.services.FichePaieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/fichePaie")
public class FichePaieController {

    @Autowired
    private FichePaieService fichePaieService;

    @PostMapping("/calculer")
    public ResponseEntity<FichePaieDTO> calculerFichePaie(@RequestBody Fiche_Paie fichePaie) {
        Fiche_Paie fichePaieCalculée = fichePaieService.calculerFichePaie(fichePaie);
        return ResponseEntity.ok(fichePaieService.convertToDTO(fichePaieCalculée));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FichePaieDTO> getFichePaie(@PathVariable Long id) {
        Fiche_Paie fichePaie = fichePaieService.getFichePaie(id);
        if (fichePaie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fichePaieService.convertToDTO(fichePaie));
    }

    @PostMapping("/{id}/addElement")
    public ResponseEntity<FichePaieDTO> addElementSalaire(@PathVariable Long id, @RequestBody Element_Salaire elementSalaire) {
        Fiche_Paie fichePaie = fichePaieService.getFichePaie(id);
        if (fichePaie == null) {
            return ResponseEntity.notFound().build();
        }
        fichePaie.getElementsSalaires().add(elementSalaire);
        elementSalaire.setFichePaie(fichePaie);
        fichePaieService.calculerFichePaie(fichePaie);
        return ResponseEntity.ok(fichePaieService.convertToDTO(fichePaie));
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> getFichePaiePdf(@PathVariable Long id) {
        ByteArrayInputStream bis = fichePaieService.generateFichePaiePdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=fiche_paie_" + id + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }
}
