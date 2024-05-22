package com.example.salhuman.repositories;

import com.example.salhuman.models.Fiche_Paie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Fiche_PaieRepository extends JpaRepository<Fiche_Paie,Long> {
}
