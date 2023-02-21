package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.DemandAudit;
import com.doniso.Doniso.Models.Etat;
import com.doniso.Doniso.Models.Formation;
import com.doniso.Doniso.Models.Participant;
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

    //List<Etat> findByEtat(Etat etat);


    // CRUD::::::::::::::::::::::::::::::::::::::::::::::::::::
    String creer(Formation formation);


    List<Formation> lire();

    Optional<Formation> lireParID(Long idFormat);

    Formation modifier(Long idFormat, Formation formation);

    String supprimer(Long idFormat);


    // Participation à une formation
}
