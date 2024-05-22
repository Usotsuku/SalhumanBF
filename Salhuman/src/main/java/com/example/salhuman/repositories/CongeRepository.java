package com.example.salhuman.repositories;

import com.example.salhuman.models.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CongeRepository extends JpaRepository<Conge,Long> {
}
