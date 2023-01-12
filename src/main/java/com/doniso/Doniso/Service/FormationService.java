package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.Formation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FormationService {


    Formation creer(Formation formation);

    List<Formation> lire();

    Optional<Formation> lireParID(Long idFormat);

    Formation modifier(Long idFormat, Formation formation);

    String supprimer(Long idFormat);
}
