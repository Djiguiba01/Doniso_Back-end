package com.doniso.Doniso.Service;

import com.doniso.Doniso.Email.EmailConstructor;
import com.doniso.Doniso.Models.*;
import com.doniso.Doniso.Repository.UtilisateursRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UtilisateurSecondServiceImpl implements UtilisateurSecondService{

    @Autowired
    private final UtilisateursRepository utilisateursRepository;

    private final NotificationRepository notificationRepository;

    // :::::::::::::::::::::::::::::::::::::
    @Autowired
    private final EmailConstructor emailConstructor; // Connection avec class email ( EmailConstructor )

    private final JavaMailSender mailSender; // Envoie gmail

    @Override // Voir par role utilisateur:::::::::::::::::::::::::::::
    public List<Utilisateurs> voirParrole(Role role) {
        return utilisateursRepository.findByRoles(role);
    }

    @Override
    public Utilisateurs creer(Utilisateurs utilisateurs) {
        return utilisateursRepository.save(utilisateurs);
    }

    @Override
    public List<Utilisateurs> lire() {

        return utilisateursRepository.findAll();
    }

    @Override
    public Optional<Utilisateurs> lireParID(Long id) {
        return utilisateursRepository.findById(id);
    }


    @Override
    public Utilisateurs modifier(Long id, Utilisateurs utilisateurs) {
        return utilisateursRepository.findById(id)
                .map(u-> {
                    u.setUsername(utilisateurs.getUsername());
                    u.setNomcomplet(utilisateurs.getNomcomplet());
                    u.setProfession(utilisateurs.getProfession());
                    u.setEmail(utilisateurs.getEmail());
                    u.setContact(utilisateurs.getContact());
                    u.setSexe(utilisateurs.getSexe());
                    u.setPhoto(utilisateurs.getPhoto());
                    return  utilisateursRepository.save(u);
                }).orElseThrow(() -> new RuntimeException("Utilisateur non trouv?? !!"));
    }

    @Override
    public String supprimer(Long id) {
        utilisateursRepository.deleteById(id);
        return "Utilisateur supprim?? avec succ??s !!";
    }
}
