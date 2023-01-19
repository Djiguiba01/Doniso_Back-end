package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.ERole;
import com.doniso.Doniso.Models.Role;
import com.doniso.Doniso.Models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateursRepository extends JpaRepository<Utilisateurs, Long> {

  Optional<Utilisateurs> findByUsername(String username);

  Boolean existsByUsername(String username);  //


  Boolean existsByEmail(String email); // Envoie notification aux utilisateurs::::::::::::

}
