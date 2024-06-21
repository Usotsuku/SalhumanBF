package com.example.salhuman.services;

import com.example.salhuman.models.Fiche_Paie;
import com.example.salhuman.repositories.Fiche_PaieRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Optional;

@Service
public class FichePaieService {

    @Autowired
    private Fiche_PaieRepository fichePaieRepository;

    private static final double TAUX_CNSS = 0.05;
    private static final double TAUX_AMO = 0.015;
    private static final double TAUX_IMPOT = 0.10;

    public Fiche_Paie calculerFichePaie(Fiche_Paie fichePaie) {
        float montantBrut = fichePaie.getMontantBrut();

        fichePaie.setCnss((float) (montantBrut * TAUX_CNSS));
        fichePaie.setAmo((float) (montantBrut * TAUX_AMO));
        fichePaie.setImpotSurRevenu((float) (montantBrut * TAUX_IMPOT));

        float montantNet = (float) (montantBrut - fichePaie.getCnss() - fichePaie.getAmo() - fichePaie.getImpotSurRevenu());
        fichePaie.setMontantNet(montantNet);

        // Sauvegarder la fiche de paie calculée
        return fichePaieRepository.save(fichePaie);
    }

    public Fiche_Paie getFichePaie(Long id) {
        return fichePaieRepository.findById(id).orElse(null);
    }

    public ByteArrayInputStream generateFichePaiePdf(Long fichePaieId) {
        Fiche_Paie fichePaie = getFichePaie(fichePaieId);
        if (fichePaie == null) {
            throw new IllegalArgumentException("Fiche de paie non trouvée");
        }

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc, PageSize.A4);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            document.setFont(font);

            document.add(new Paragraph("Fiche de Paie").setFontSize(20).setBold());
            document.add(new Paragraph("Période: " + fichePaie.getPeriode()));
            document.add(new Paragraph("Employé: " + fichePaie.getEmploye().getNom()));
            document.add(new Paragraph("Salaire Brut: " + fichePaie.getMontantBrut()));
            document.add(new Paragraph("CNSS: " + fichePaie.getCnss()));
            document.add(new Paragraph("AMO: " + fichePaie.getAmo()));
            document.add(new Paragraph("Impôt sur le Revenu: " + fichePaie.getImpotSurRevenu()));
            document.add(new Paragraph("Salaire Net: " + fichePaie.getMontantNet()));

            document.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF: " + e.getMessage(), e);
        }
    }
}
