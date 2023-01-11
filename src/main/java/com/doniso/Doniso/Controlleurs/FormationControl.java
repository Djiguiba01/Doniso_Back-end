package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Models.Formation;
import com.doniso.Doniso.Service.FormationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formation")
@AllArgsConstructor
public class FormationControl {

    private final FormationService formationService;

    @PostMapping("/ajout")
    public Formation create(@RequestBody Formation formation) {
        return  formationService.creer(formation);
    }

    @GetMapping("/voir")
    public List<Formation> read(){
        return formationService.lire();
    }

    @GetMapping("/voir/{idFormat}")
    public Optional<Formation> reada(@PathVariable("idFormat") Long idFormat){
        return formationService.lireParID(idFormat);
    }

    @PutMapping("/update/{idFormat}")
    public Formation update(@PathVariable Long idFormat, @RequestBody Formation formation) {
        return formationService.modifier(idFormat, formation);
    }

    @DeleteMapping("/supprimer/{idFormat}")
    public String delete(@PathVariable Long idFormat){
        return formationService.supprimer(idFormat);
    }
}
