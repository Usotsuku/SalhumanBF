package com.example.salhuman.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Element_Salaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long elementId;

    private String type;

    private float montant;

    @ManyToOne
    @JoinColumn(name = "fiche_paie_id", nullable = false)
    private Fiche_Paie fichePaie;
}
