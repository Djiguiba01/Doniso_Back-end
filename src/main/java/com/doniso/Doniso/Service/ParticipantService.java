package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.Participant;

import java.util.List;

public interface ParticipantService {

    Participant creer(Participant participant);

    List<Participant> lire();

    Participant modifier(Long idPart, Participant participant);

    String supprimer(Long idPart);
}
