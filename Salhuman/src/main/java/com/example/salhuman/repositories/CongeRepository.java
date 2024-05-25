package com.example.salhuman.repositories;

import com.example.salhuman.models.Conge;
import com.example.salhuman.models.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongeRepository extends JpaRepository<Conge,Long> {
    List<Conge> findByEmploye(Employe employe);;
}
