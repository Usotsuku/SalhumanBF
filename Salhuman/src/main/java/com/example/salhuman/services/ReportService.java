package com.example.salhuman.services;

import com.example.salhuman.models.Fiche_Paie;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReportService {

    public byte[] generatePdfReport(List<Fiche_Paie> fichesPaie) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Rapport de Fiches de Paie"));
        document.add(new Paragraph(" "));

        for (Fiche_Paie fichePaie : fichesPaie) {
            document.add(new Paragraph(fichePaie.toString()));
            document.add(new Paragraph(" "));
        }

        document.close();
        return out.toByteArray();
    }
}
