package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Models.Commentaire;
import com.doniso.Doniso.Service.CommentaireService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commentaire")
@AllArgsConstructor
public class CommentaireControl {

    private final CommentaireService commentaireService;

    @PostMapping("/ajout")
    @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public Commentaire create(@RequestBody Commentaire commentaire) {
        return  commentaireService.creer(commentaire);
    }

    @GetMapping("/voir")
    @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public List<Commentaire> read(){
        return commentaireService.lire();
    }

    @GetMapping("/voir/{idCom}")
    @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public Optional<Commentaire> read(@PathVariable("idCom") Long idCom){
        return commentaireService.lireParID(idCom);
    }

    @PutMapping("/update/{idCom}")
    @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public Commentaire update(@PathVariable Long idCom, @RequestBody Commentaire commentaire) {
        return commentaireService.modifier(idCom, commentaire);
    }

    @DeleteMapping("/supprimer/{idCom}")
    @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public String delete(@PathVariable Long idCom){
        return commentaireService.supprimer(idCom);
    }
}
