package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Models.Commentaire;
import com.doniso.Doniso.Models.Formation;
import com.doniso.Doniso.Models.Utilisateurs;
import com.doniso.Doniso.Repository.CommentaireRepo;
import com.doniso.Doniso.Repository.FormationRepo;
import com.doniso.Doniso.Service.CommentaireService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins ={ "http://localhost:4200/", "http://localhost:8100/" }, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/commentaire")
@AllArgsConstructor
public class CommentaireControl {

    private final CommentaireService commentaireService;
    private final CommentaireRepo commentaireRepo;
    private final FormationRepo formationRepo;

    // Voir les commentaires d'une formation
    @GetMapping("/voirparformation/{idformation}")
   // @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public List<Commentaire> commentaireparformation(@PathVariable("idformat") Formation formation){
        return commentaireRepo.findByFormation(formation);
    }

    // Ajouter un commentaire à une formation
    // Et il prend en compte id de l'utilisateur et de la formation
    @PostMapping("/ajout/{idutilisateur}/{idformation}")
   // @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public Commentaire create(@Param("description") String description,
                              @PathVariable long idutilisateur,
                              @PathVariable long idformation) {
        Commentaire commentaire =new Commentaire(description);
        commentaire.setUtilisateurs(new Utilisateurs(idutilisateur));
        commentaire.setFormation(new Formation(idformation));
        return  commentaireService.creer(commentaire);
    }

    @GetMapping("/voir")
   // @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public List<Commentaire> read(){
        return commentaireService.lire();
    }



    @GetMapping("/voir/{idCom}")
    //@PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public Optional<Commentaire> read(@PathVariable("idCom") Long idCom){
        return commentaireService.lireParID(idCom);
    }

    @PutMapping("/update/{idCom}")
    //@PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public Commentaire update(@PathVariable Long idCom, @RequestBody Commentaire commentaire) {
        return commentaireService.modifier(idCom, commentaire);
    }

    @DeleteMapping("/supprimer/{idCom}")
   // @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public String delete(@PathVariable Long idCom){
        return commentaireService.supprimer(idCom);
    }
}
