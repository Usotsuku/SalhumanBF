package com.example.salhuman.repositories;

import com.example.salhuman.models.Fiche_Paie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Fiche_PaieRepository extends JpaRepository<Fiche_Paie, Long> {
    List<Fiche_Paie> findByPeriodeAndDepartement(String periode, String departement);
}
