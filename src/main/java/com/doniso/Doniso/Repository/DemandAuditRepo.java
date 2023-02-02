package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.DemandAudit;
import com.doniso.Doniso.Models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandAuditRepo extends JpaRepository<DemandAudit, Long> {

    List<DemandAudit>  findByUtilisateurs (Utilisateurs utilisateurs); // Voir formations par utilisateur

    DemandAudit findDemandAuditByIdDemand(Long idDemand); // Methode validation demande formation
}
