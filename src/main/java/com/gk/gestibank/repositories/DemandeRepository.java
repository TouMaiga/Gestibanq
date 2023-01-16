package com.gk.gestibank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gk.gestibank.entities.Demande;

public interface DemandeRepository extends JpaRepository<Demande, Integer> {

}
