package com.doniso.Doniso.Service;

import com.doniso.Doniso.Email.EmailConstructor;
import com.doniso.Doniso.Models.*;
import com.doniso.Doniso.Repository.DemandAuditRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DemandAuditServiceImpl implements DemandAuditService{

    @Autowired
    private  final DemandAuditRepo demandAuditRepo;



    // Acceptation demande formation ::::::::::::::::::::::::::::::::
    @Autowired
    private final EmailConstructor emailConstructor; // Connection avec class email ( EmailConstructor )
    @Autowired
    private final JavaMailSender mailSender; // Envoie gmail

    @Override
    public DemandAudit valideAudit(Long idDemand)  {
        DemandAudit demandAudit = demandAuditRepo.findDemandAuditByIdDemand(idDemand);
        demandAudit.setAuditstatus(AuditDemand.ACCEPTER);
        //mailSender.send(emailConstructor.constructDemandAuditEmail(demandAudit));// Permet d'envoyer gmail
        return demandAuditRepo.save(demandAudit);
    }

    // Refus demande formation :::::::::::::::::::::::::::::::
    @Override
    public DemandAudit refugeAudit(Long idDemand)  {
        DemandAudit demandAudit = demandAuditRepo.findDemandAuditByIdDemand(idDemand);
        demandAudit.setAuditstatus(AuditDemand.NON_ACCEPTER);
        //mailSender.send(emailConstructor.constructRefusAuditEmail(demandAudit)); // Permet d'envoyer gmail
        return demandAuditRepo.save(demandAudit);
    }
// Voir formations par utilisateur:::::::::::
    @Override
    public List<DemandAudit> Voirparutilisateur(Utilisateurs utilisateurs) {
        return demandAuditRepo.findByUtilisateurs(utilisateurs);
    }


    // Les CRUD ::::::::::::::::::::::::::::::::::::::::::::::::::::::
    @Override
    public DemandAudit creer(DemandAudit demandAudit) {
        demandAudit.setAuditstatus(AuditDemand.ENCOURS_TRAITEMENT);
        return demandAuditRepo.save(demandAudit);
    }

    @Override
    public List<DemandAudit> lire() {
        return demandAuditRepo.findAll();
    }

    @Override
    public Optional<DemandAudit> lireParID(Long idDemand) {
        return demandAuditRepo.findById(idDemand);
    }



    @Override
    public DemandAudit modifier(Long idDemand, DemandAudit demandAudit) {
        return demandAuditRepo.findById(idDemand)
                .map(d-> {
                    d.setStructure(demandAudit.getStructure());
                    d.setLieu(demandAudit.getLieu());
                    d.setPhoto(demandAudit.getPhoto());
                    d.setEmail(demandAudit.getEmail());
                    d.setType(demandAudit.getType());
                    d.setPersonnes(demandAudit.getPersonnes());
                    d.setAuditstatus(demandAudit.getAuditstatus());
                    return  demandAuditRepo.save(d);
                }).orElseThrow(() -> new RuntimeException("Formation non trouvée !!"));
    }
    @Override
    public String supprimer(Long idDemand) {
        demandAuditRepo.deleteById(idDemand);
        return "Formation supprimée !";
    }
}
