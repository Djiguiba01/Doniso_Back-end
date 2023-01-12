package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepo extends JpaRepository<Commentaire, Long> {
}
