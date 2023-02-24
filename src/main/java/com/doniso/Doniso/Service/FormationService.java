package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FormationService {

    // Voir sa participation à une formation
    //List<Formation> voirSaParticipation (Participant participant);


    // Etat formation
    // Methode validation service :::::::::::::::::::::::::::::::::::::::::::::::::::::::

    Formation initial(Long idFormat);

    Formation encours(Long idFormat);

    Formation terminer(Long idFormat);

    List<Object> voiretat(Etat etat); // Voir Etat formation

    // Ajout lieu déroulement de la formation::::::::
    Formation validePresidentielle(Long idFormat);

    Formation valideEnLigne(Long idFormat);

    Formation nonvalider(Long idFormat);

    Formation valider(Long idFormat);

    List<Object> voirformationformateur(ValidFormateur validFormateur); // Voir Etat formation

    //List<Etat> findByEtat(Etat etat);



    // CRUD::::::::::::::::::::::::::::::::::::::::::::::::::::
    String creer(Formation formation);


    List<Formation> lire();

    Optional<Formation> lireParID(Long idFormat);

    Formation modifier(Long idFormat, Formation formation);

    String supprimer(Long idFormat);


    // Participation à une formation
}
