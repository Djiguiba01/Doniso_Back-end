package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Models.DemandAudit;
import com.doniso.Doniso.Service.DemandAuditService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demandpart")
@AllArgsConstructor
public class DemandAuditControl {

    private final DemandAuditService demandAuditService;

    @PostMapping("/ajout")
    @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public DemandAudit create(@RequestBody DemandAudit demandAudit) {
        return  demandAuditService.creer(demandAudit);
    }

    @GetMapping("/voir")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<DemandAudit> read(){
        return demandAuditService.lire();
    }

    @GetMapping("/voir/{idDemand}")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Optional<DemandAudit> read(@PathVariable("idDemand") Long idDemand){
        return demandAuditService.lireParID(idDemand);
    }

    @PutMapping("/update/{idDemand}")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public DemandAudit update(@PathVariable Long idDemand, @RequestBody DemandAudit demandAudit) {
        return demandAuditService.modifier(idDemand, demandAudit);
    }

    @DeleteMapping("/supprimer/{idDemand}")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String delete(@PathVariable Long idDemand){
        return demandAuditService.supprimer(idDemand);
    }


}
