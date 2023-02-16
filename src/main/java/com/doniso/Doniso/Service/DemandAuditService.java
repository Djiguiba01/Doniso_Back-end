package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.AuditDemand;
import com.doniso.Doniso.Models.DemandAudit;
import com.doniso.Doniso.Models.Etat;
import com.doniso.Doniso.Models.Utilisateurs;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DemandAuditService {

    // Methode validation service :::::::::::::::::::::::::::::::::::::::::::::::::::::::

    DemandAudit encousAudit(Long idDemand);

    DemandAudit valideAudit(Long idDemand);

    DemandAudit refugeAudit(Long idDemand);

    // Voir formations par utilisateur::::::::::::::::::::
    List<DemandAudit> Voirparutilisateur (Utilisateurs utilisateurs);

    List<Object> voirauditstatus(AuditDemand auditstatus); // Voir Etat Participants



    // CRUD ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    DemandAudit creer(DemandAudit demandAudit);

    List<DemandAudit> lire();

    Optional<DemandAudit> lireParID(Long idDemand);


    DemandAudit modifier(Long idDemand, DemandAudit demandAudit);

    String supprimer(Long idDemand);





}
