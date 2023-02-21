package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.DemandAudit;
import com.doniso.Doniso.Models.Etat;
import com.doniso.Doniso.Models.Formation;
import com.doniso.Doniso.Models.Participant;
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


    // ::::::::::::::
    Formation findByTitre(String titre);

}
