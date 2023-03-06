package com.doniso.Doniso.Service;


import com.doniso.Doniso.Models.DemandAudit;
import com.doniso.Doniso.Models.EtreFormateur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EtreFormateurService {

    // CRUD ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    EtreFormateur creer(EtreFormateur etreFormateur);

    List<EtreFormateur> lire();

    Optional<EtreFormateur> lireParID(Long idetreform);


    EtreFormateur modifier(Long idetreform, EtreFormateur etreFormateur);

    String supprimer(Long idetreform);


}
