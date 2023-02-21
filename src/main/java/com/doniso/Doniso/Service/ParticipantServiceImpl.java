package com.doniso.Doniso.Service;

import com.doniso.Doniso.Email.EmailConstructor;
import com.doniso.Doniso.Models.Participant;
import com.doniso.Doniso.Models.ValidParticipant;
import com.doniso.Doniso.Repository.FormationRepo;
import com.doniso.Doniso.Repository.ParticipantRepo;
import com.doniso.Doniso.Repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ParticipantServiceImpl implements ParticipantService{

    @Autowired
    private final ParticipantRepo participantRepo;
    private final RoleRepository roleRepository;
    private final FormationRepo formationRepo;

    // Etat Participant::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    @Autowired
    private final EmailConstructor emailConstructor; // Connection avec class email ( EmailConstructor )

    private final JavaMailSender mailSender; // Envoie gmail



    // Non_Participant
    @Override
    public Participant nonvalideParticipant(Long idPart) {
        Participant participant = participantRepo.findParticipantByIdPart(idPart);
        participant.setStatus(ValidParticipant.NON_VALIDER);
       mailSender.send(emailConstructor.constructNonValideParticEmail(participant));// Permet d'envoyer gmail
        return participantRepo.save(participant);
    }

    // Service implement status Participant
    @Override
    public List<Object> voirParticipantstatus(ValidParticipant validParticipant) {
        return participantRepo.findByStatus(validParticipant);
    }

    @Override
    public Participant encoursParticipant(Long idPart) {
        Participant participant = participantRepo.findParticipantByIdPart(idPart);
        participant.setStatus(ValidParticipant.ENCOURS_TRAITEMENT);
        //mailSender.send(emailConstructor.constructValideParticipantEmail(participant));// Permet d'envoyer gmail
        return participantRepo.save(participant);
    }

    // Validation Participant
    @Override
    public Participant valideParticipant(Long idPart) {
        Participant participant = participantRepo.findParticipantByIdPart(idPart);
        participant.setStatus(ValidParticipant.VALIDER);
        mailSender.send(emailConstructor.constructValideParticipantEmail(participant));// Permet d'envoyer gmail
        return participantRepo.save(participant);
    }


// ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    @Override
    public Participant creer(Participant participant) {
        participant.setStatus(Participant.ENCOURS_TRAITEMENT); // Enrégistrement Par defaux etat encours_traitement
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
                    p.setSexe(participant.getSexe());
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
