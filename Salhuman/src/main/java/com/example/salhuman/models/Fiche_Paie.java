package com.example.salhuman.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fiche_Paie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long FicheId;
    private String periode;
    private float montant_brut;
    private float montant_net;
    private float amo;
    private float cnss;
    private float impotSurRevenu;
    @ManyToOne
    private Employe employe;
    @OneToMany(mappedBy = "fiche_paie", fetch = FetchType.EAGER)
    private List<Element_Salaire> Elements_Salaires = new ArrayList<>();
}
