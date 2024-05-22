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
    private Long ElementId;
    private String type;
    private float montant;
    @ManyToOne
    private Fiche_Paie fiche_paie;
}
