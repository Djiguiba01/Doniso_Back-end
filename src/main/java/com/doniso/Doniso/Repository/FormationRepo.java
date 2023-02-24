package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FormationRepo extends JpaRepository<Formation, Long> {

    // Voir sa participation à une formation
   // List<Formation> findByParticipant (Participant participant);

    // Validation::::::::
    Formation findFormationByIdFormat(Long idFormat);// Methode validation demande formation par id



    List<Object> findByEtat (Etat etat); // Voir formation part Etat


    // Ajout lieu déroulement de la formation::::::::
    Formation findLieuFormationByIdFormat(Long idFormat);// Méthode Ajout type de demande

    // Validation des formateurs
     Formation findValidFormateurByIdFormat(Long idFormat);//

    // Voir formation par validation des formateurs
    List<Object> findByValidFormateur (ValidFormateur validFormateur);

    // ::::::::::::::
    Formation findByTitre(String titre);

}
