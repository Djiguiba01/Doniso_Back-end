package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.Notification;
import com.doniso.Doniso.Models.Participant;
import com.doniso.Doniso.Models.Utilisateurs;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UtilisateurSecondService {

    Utilisateurs creer(Utilisateurs utilisateurs);

    List<Utilisateurs> lire();

    Optional<Utilisateurs> lireParID(Long id);


    Utilisateurs modifier(Long id, Utilisateurs utilisateurs);

    String supprimer(Long id);
}
