package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.Commentaire;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentaireService {

    Commentaire creer(Commentaire commentaire);

    List<Commentaire> lire();

    Optional<Commentaire> lireParID(Long idCom);

    Commentaire modifier(Long idCom, Commentaire commentaire);

    String supprimer(Long idCom);
}
