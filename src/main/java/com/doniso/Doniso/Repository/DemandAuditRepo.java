package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandAuditRepo extends JpaRepository<DemandAudit, Long> {


    List<DemandAudit>  findByUtilisateurs (Utilisateurs utilisateurs); // Voir formations par utilisateur

    DemandAudit findDemandAuditByIdDemand(Long idDemand); // Methode validation demande formation

    List<Object> findByAuditstatus (AuditDemand auditstatus); // Voir Participant part Etat


    // Ajout lieu déroulement de la formation::::::::
    DemandAudit findDemandLigneByIdDemand(Long idPart);// Méthode Ajout type de demande

}
