package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantService {

    // Validation Participant::::::::::::::::::::::::::::::::::::::::::::
    Participant valideParticipant(Long idPart);
    Participant nonvalideParticipant(Long idPart);


    // CRUD:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    Participant creer(Participant participant);

    List<Participant> lire();

    Optional<Participant> lireParID(Long idPart);


    Participant modifier(Long idPart, Participant participant);

    String supprimer(Long idPart);
}
