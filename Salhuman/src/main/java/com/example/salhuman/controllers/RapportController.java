package com.example.salhuman.controllers;

import com.example.salhuman.models.Fiche_Paie;
import com.example.salhuman.repositories.Fiche_PaieRepository;
import com.example.salhuman.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/rapports")
public class RapportController {

    private final Fiche_PaieRepository fiche_PaieRepository;
    private final ReportService reportService;

    @Autowired
    public RapportController(Fiche_PaieRepository fiche_PaieRepository, ReportService reportService) {
        this.fiche_PaieRepository = fiche_PaieRepository;
        this.reportService = reportService;
    }

    @PostMapping("/generer")
    public ResponseEntity<?> genererRapport(@RequestParam("periode") String periode,
                                            @RequestParam("departement") String departement) {
        try {
            // Validation des entrées
            if (periode == null || departement == null || periode.isEmpty() || departement.isEmpty()) {
                return ResponseEntity.badRequest().body("Veuillez spécifier une période et un département valides.");
            }

            // Interroger la base de données pour récupérer les données pertinentes
            List<Fiche_Paie> fichesPaie = fiche_PaieRepository.findByPeriodeAndDepartement(periode, departement);

            // Vérifier si des résultats ont été trouvés
            if (fichesPaie.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune fiche de paie trouvée pour la période et le département spécifiés.");
            }

            // Return the data in JSON format
            return ResponseEntity.ok(fichesPaie);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la génération du rapport.");
        }
    }

    @PostMapping("/telecharger")
    public ResponseEntity<?> telechargerRapport(@RequestParam("periode") String periode,
                                                @RequestParam("departement") String departement) {
        try {

            if (periode == null || departement == null || periode.isEmpty() || departement.isEmpty()) {
                return ResponseEntity.badRequest().body("Veuillez spécifier une période et un département valides.");
            }

            List<Fiche_Paie> fichesPaie = fiche_PaieRepository.findByPeriodeAndDepartement(periode, departement);


            if (fichesPaie.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune fiche de paie trouvée pour la période et le département spécifiés.");
            }


            byte[] report = reportService.generatePdfReport(fichesPaie);


            ByteArrayResource resource = new ByteArrayResource(report);//

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=rapport.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(report.length)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la génération du rapport.");
        }
    }
}
