package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.Commentaire;
import com.doniso.Doniso.Models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurSecondRepo extends JpaRepository<Utilisateurs, Long> {
}
