package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Models.Etat;
import com.doniso.Doniso.Models.Formation;
import com.doniso.Doniso.Models.Notification;
import com.doniso.Doniso.Models.Utilisateurs;
import com.doniso.Doniso.Repository.FormationRepo;
import com.doniso.Doniso.Repository.NotificationRepo;
import com.doniso.Doniso.Repository.UtilisateursRepository;
import com.doniso.Doniso.Service.FormationService;
import com.doniso.Doniso.payload.Autres.ConfigImages;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formation")
@AllArgsConstructor
public class FormationControl {

    @Autowired
    private final FormationService formationService;
    @Autowired
    private final FormationRepo formationRepo;

    @Autowired
    NotificationRepo notificationRepo;

    @Autowired
    UtilisateursRepository utilisateursRepository;


    @PostMapping("/ajout")
    //@PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Formation create(
            @Param ("titre") String titre,
            @Param ("lieu") String lieu,
            @Param ("description") String description,
            @Param ("contact") int contact,
            @Param ("heure") int heure,
            @Param ("datedebut") LocalDate datedebut,
            @Param ("datefin") LocalDate datefin,
            @Param ("etat") Etat etat,
            @Param ("idutilisateur") Long idutilisateur,
            @Param("file")MultipartFile file) throws IOException {
        Formation formation = new Formation();
        formation.setLieu(lieu);
        formation.setDescription(description);
        formation.setContact(contact);
        formation.setHeure(heure);
        formation.setDatedebut(datedebut);
        formation.setDatefin(datefin);
        formation.setEtat(etat);
        formation.setUtilisateurs(new Utilisateurs(idutilisateur));
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "C:/Projet_Ionic/Doniso/src/assets/images/Back-end";
        ConfigImages.saveimg(uploadDir, imageName, file);



        Notification notification = new Notification();

        Utilisateurs utilisateurs = utilisateursRepository.findById(idutilisateur).get();
        notification.setTitre(formation.getTitre());
        notification.setDescription(utilisateurs.getUsername() + " a ajouté une nouvelle formation.\n Pour plus d'information, veuillez contacter " + formation.getContact());
        notification.getUtilisateurs().add(utilisateurs);
        notificationRepo.save(notification);

           return formationService.creer(formation);


    }

    @GetMapping("/voir")
    @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public List<Formation> read(){
        return formationService.lire();
    }

    @GetMapping("/voir/{idFormat}")
    @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public Optional<Formation> read(@PathVariable("idFormat") Long idFormat){
        return formationService.lireParID(idFormat);
    }

    @PutMapping("/update/{idFormat}")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Formation update(@PathVariable Long idFormat, @RequestBody Formation formation) {
        return formationService.modifier(idFormat, formation);
    }

    @DeleteMapping("/supprimer/{idFormat}")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String delete(@PathVariable Long idFormat){
        return formationService.supprimer(idFormat);
    }
}
