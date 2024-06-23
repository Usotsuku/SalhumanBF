package com.example.salhuman.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FichePaieDTO {
    private Long ficheId;
    private LocalDate periode;
    private float montantBrut;
    private float montantNet;
    private float amo;
    private float cnss;
    private float impotSurRevenu;
    private String employeNom;
    private List<ElementSalaireDTO> elementsSalaires;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ElementSalaireDTO {
        private String type;
        private float montant;
    }
}
