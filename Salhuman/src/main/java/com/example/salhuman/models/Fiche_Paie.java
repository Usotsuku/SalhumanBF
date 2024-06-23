package com.example.salhuman.models;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fiche_paie")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fiche_Paie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ficheId;

    @Column(nullable = false)
    private String periode;

    private String departement;

    @Column(nullable = false)
    private float montantBrut;

    @Column(nullable = false)
    private float montantNet;

    @Column(nullable = false)
    private float amo;

    @Column(nullable = false)
    private float cnss;

    @Column(nullable = false)
    private float impotSurRevenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employe_id", nullable = false)
    private Employe employe;

    @OneToMany(mappedBy = "fichePaie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Element_Salaire> elementsSalaires = new ArrayList<>();
}
