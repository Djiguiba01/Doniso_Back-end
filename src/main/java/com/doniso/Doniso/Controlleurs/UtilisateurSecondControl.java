package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Models.Utilisateurs;
import com.doniso.Doniso.Service.UtilisateurSecondService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utlisateursecond")
@AllArgsConstructor
public class UtilisateurSecondControl {

    @Autowired
    private final UtilisateurSecondService utilisateurSecondService;
//    private final UtilisateurSecondRepo utilisateurSecondRepo;


    @PostMapping("/ajout")
    public Utilisateurs create(@RequestBody Utilisateurs utilisateurs) {
        return  utilisateurSecondService.creer(utilisateurs);
    }

    @GetMapping("/voir")
    public List<Utilisateurs> read(){
        return utilisateurSecondService.lire();
    }

    @GetMapping("/voir/{id}")
    public Optional<Utilisateurs> read(@PathVariable("id") Long id){
        return utilisateurSecondService.lireParID(id);
    }


    @PutMapping("/update/{id}")
    public Utilisateurs modifier(@PathVariable Long id, @RequestBody Utilisateurs utilisateurs) {
        return utilisateurSecondService.modifier(id, utilisateurs);
    }

    @DeleteMapping("supprimer/{id}")
    public String delete(@PathVariable Long id){
        return utilisateurSecondService.supprimer(id);
    }




}
