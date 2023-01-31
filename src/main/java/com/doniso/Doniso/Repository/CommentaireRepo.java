package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.Commentaire;
import com.doniso.Doniso.Models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepo extends JpaRepository<Commentaire, Long> {

    // Voir Commentaire par formation
    List<Commentaire> findByFormation(Formation formation);

}
