package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.Etat;
import com.doniso.Doniso.Models.Formation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FormationService {


    // Etat formation
    // Methode validation service :::::::::::::::::::::::::::::::::::::::::::::::::::::::
    Formation encours(Long idFormat);

    Formation terminer(Long idFormat);

    List<Object> voiretat(Etat etat); // Voir Etat formation


    // CRUD::::::::::::::::::::::::::::::::::::::::::::::::::::
    String creer(Formation formation);

    List<Formation> lire();

    Optional<Formation> lireParID(Long idFormat);

    Formation modifier(Long idFormat, Formation formation);

    String supprimer(Long idFormat);
}
