package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.DemandAudit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DemandAuditService {

    DemandAudit creer(DemandAudit demandAudit);

    List<DemandAudit> lire();

    Optional<DemandAudit> lireParID(Long idDemand);

    DemandAudit modifier(Long idDemand, DemandAudit demandAudit);

    String supprimer(Long idDemand);
}
