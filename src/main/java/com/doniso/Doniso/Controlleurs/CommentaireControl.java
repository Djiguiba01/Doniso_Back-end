package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Models.Commentaire;
import com.doniso.Doniso.Service.CommentaireService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commentaire")
@AllArgsConstructor
public class CommentaireControl {

    private final CommentaireService commentaireService;

    @PostMapping("/ajout")
    public Commentaire create(@RequestBody Commentaire commentaire) {
        return  commentaireService.creer(commentaire);
    }

    @GetMapping("/voir")
    public List<Commentaire> read(){
        return commentaireService.lire();
    }

    @GetMapping("/voir/{idCom}")
    public Optional<Commentaire> reada(@PathVariable("idCom") Long idCom){
        return commentaireService.lireParID(idCom);
    }

    @PutMapping("/update/{idCom}")
    public Commentaire update(@PathVariable Long idCom, @RequestBody Commentaire commentaire) {
        return commentaireService.modifier(idCom, commentaire);
    }

    @DeleteMapping("/supprimer/{idCom}")
    public String delete(@PathVariable Long idCom){
        return commentaireService.supprimer(idCom);
    }
}
