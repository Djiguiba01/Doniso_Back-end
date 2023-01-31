package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.Commentaire;
import com.doniso.Doniso.Repository.CommentaireRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentaireServiceImpl  implements CommentaireService{

    @Autowired
    private  final CommentaireRepo commentaireRepo;


    @Override
    public Commentaire creer(Commentaire commentaire) {
        return commentaireRepo.save(commentaire);
    }

    @Override
    public List<Commentaire> lire() {
        return commentaireRepo.findAll();
    }

    @Override
    public Optional<Commentaire> lireParID(Long idCom) {
        return commentaireRepo.findById(idCom);
    }

    @Override
    public Commentaire modifier(Long idCom, Commentaire commentaire) {
        return commentaireRepo.findById(idCom)
                .map(c-> {
                   // c.setNom(commentaire.getNom());
                    c.setDescription(commentaire.getDescription());
                    return  commentaireRepo.save(c);
                }).orElseThrow(() -> new RuntimeException("Commentaire non trouvé !!"));
    }

    @Override
    public String supprimer(Long idCom) {
        commentaireRepo.deleteById(idCom);
        return "Commentaire supprimée !";
    }
}