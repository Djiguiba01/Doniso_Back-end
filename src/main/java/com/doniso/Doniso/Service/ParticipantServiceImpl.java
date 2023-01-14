package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.Formation;
import com.doniso.Doniso.Models.Participant;
import com.doniso.Doniso.Repository.ParticipantRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ParticipantServiceImpl implements ParticipantService{

    @Autowired
    private final ParticipantRepo participantRepo;

    @Override
    public Participant creer(Participant participant) {
        return participantRepo.save(participant);
    }

    @Override
    public List<Participant> lire() {
        return participantRepo.findAll();
    }

    @Override
    public Optional<Participant> lireParID(Long idPart) {
        return participantRepo.findById(idPart);
    }


    @Override
    public Participant modifier(Long idPart, Participant participant) {
        return participantRepo.findById(idPart)
                .map(p-> {
                    p.setNom(participant.getNom());
                    p.setGenre(participant.getGenre());
                    p.setEmail(participant.getEmail());
                    p.setDeuxNom(participant.getDeuxNom());
                    p.setProfession(participant.getProfession());
                    return participantRepo.save(p);
                }).orElseThrow(() -> new RuntimeException("Participant non trouvé !!"));
    }


    @Override
    public String supprimer(Long idPart) {
        participantRepo.deleteById(idPart);
        return "Participant supprimé avec succès !!";    }
}
