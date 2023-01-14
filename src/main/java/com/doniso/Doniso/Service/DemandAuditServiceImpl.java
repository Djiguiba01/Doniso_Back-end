package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.AuditDemand;
import com.doniso.Doniso.Models.DemandAudit;
import com.doniso.Doniso.Repository.DemandAuditRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DemandAuditServiceImpl implements DemandAuditService{

    @Autowired
    private  final DemandAuditRepo demandAuditRepo;

    @Override
    public DemandAudit creer(DemandAudit demandAudit) {
        demandAudit.setAuditDemand(AuditDemand.ENCOURS_TRAITEMENT);
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
                    d.setAuditDemand(demandAudit.getAuditDemand());
                    return  demandAuditRepo.save(d);
                }).orElseThrow(() -> new RuntimeException("Formation non trouvée !!"));
    }
    @Override
    public String supprimer(Long idDemand) {
        demandAuditRepo.deleteById(idDemand);
        return "Formation supprimée !";
    }
}
