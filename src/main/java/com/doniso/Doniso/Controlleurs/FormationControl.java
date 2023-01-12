package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Models.Formation;
import com.doniso.Doniso.Service.FormationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formation")
@AllArgsConstructor
public class FormationControl {

    private final FormationService formationService;

    @PostMapping("/ajout")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Formation create(@RequestBody Formation formation) {
        return  formationService.creer(formation);
    }

    @GetMapping("/voir")
    @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public List<Formation> read(){
        return formationService.lire();
    }

    @GetMapping("/voir/{idFormat}")
    @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public Optional<Formation> reada(@PathVariable("idFormat") Long idFormat){
        return formationService.lireParID(idFormat);
    }

    @PutMapping("/update/{idFormat}")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Formation update(@PathVariable Long idFormat, @RequestBody Formation formation) {
        return formationService.modifier(idFormat, formation);
    }

    @DeleteMapping("/supprimer/{idFormat}")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String delete(@PathVariable Long idFormat){
        return formationService.supprimer(idFormat);
    }
}
