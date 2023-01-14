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
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String create(
            @Param("file")MultipartFile file,
            @Valid @RequestParam(value = "donneesformation") String donneesformation) throws IOException {

        //  Partie Insertion Image
        Formation formation = new JsonMapper().readValue(donneesformation, Formation.class);
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "C:/Projet_Ionic/Doniso/src/assets/images/Back-end";
        ConfigImages.saveimg(uploadDir, imageName, file);

        // Envoie Notification automatiquement lors ajout formation
        Notification notification = new Notification();
        LocalDate dt = LocalDate.now();
        System.out.println(dt);
        Utilisateurs utilisateurs = utilisateursRepository.findById(formation.getUtilisateurs().getId()).get();
        notification.setTitre(formation.getTitre());
        formation.setEtat(Etat.INITIE);
        notification.setDescription(utilisateurs.getUsername() + " a ajouté une nouvelle formation.\n Pour plus d'information, veuillez contacter " + formation.getContact());
        notification.getUtilisateurs().add(utilisateurs);
        notificationRepo.save(notification);
        formation.setImage(imageName);

        return formationService.creer(formation);

    }

    // Voir formation
    @GetMapping("/voir")
    @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public List<Formation> read(){
        return formationService.lire();
    }

    // Voir formation par id
    @GetMapping("/voir/{idFormat}")
    @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public Optional<Formation> read(@PathVariable("idFormat") Long idFormat){
        return formationService.lireParID(idFormat);
    }

    // Faire mise à jour formation
    @PutMapping("/update/{idFormat}")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Formation update(@PathVariable Long idFormat, @RequestBody Formation formation) {
        return formationService.modifier(idFormat, formation);
    }

    // Supprimer formation formation
    @DeleteMapping("/supprimer/{idFormat}")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String delete(@PathVariable Long idFormat){
        return formationService.supprimer(idFormat);
    }
}
