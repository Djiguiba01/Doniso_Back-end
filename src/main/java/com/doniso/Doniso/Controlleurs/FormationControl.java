package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Email.EmailConstructor;
import com.doniso.Doniso.Models.*;
import com.doniso.Doniso.Repository.FormationRepo;
import com.doniso.Doniso.Repository.NotificationRepo;
import com.doniso.Doniso.Repository.ParticipantRepo;
import com.doniso.Doniso.Repository.UtilisateursRepository;
import com.doniso.Doniso.Service.FormationService;
import com.doniso.Doniso.Service.ParticipantService;
import com.doniso.Doniso.payload.Autres.ConfigImages;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins ={ "http://localhost:4200/", "http://localhost:8100" }, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/formation")
@AllArgsConstructor
public class FormationControl {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private final FormationService formationService;
    @Autowired
    private final FormationRepo formationRepo;

    @Autowired
    private ParticipantRepo participantRepo;

    @Autowired
    NotificationRepo notificationRepo;


    @Autowired
    UtilisateursRepository utilisateursRepository;


    @Autowired
    private final EmailConstructor emailConstructor; // Connection avec class email ( EmailConstructor )

//    @Autowired
    private final JavaMailSender mailSender; // Envoie gmail

    // Voir sa paeticipation à une formation
//    @GetMapping("/mesformation/{participant}")
//    public List<Formation> voirformation(@PathVariable("idFormat") Participant participant){
//        return formationService.voirSaParticipation(participant);
//    }


    // Etat Formation Control ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // Encours::::::::::::::
    @PostMapping("/INITIE/{idFormat}") // Acception Control:::::::::::::::::::::::::::
    // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Formation initialFormation(@PathVariable long idFormat) throws IOException {
        return  formationService.initial(idFormat);
    }
    // Encours::::::::::::::
    @PostMapping("/ENCOURS/{idFormat}") // Acception Control:::::::::::::::::::::::::::
   // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Formation encoursFormation(@PathVariable long idFormat) throws IOException {
        return  formationService.encours(idFormat);
    }

    // Encours::::::::::::::
    @PostMapping("/TERMINER/{idFormat}") // Acception Control:::::::::::::::::::::::::::
   // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Formation terminerFormation(@PathVariable long idFormat) throws IOException {
        return  formationService.terminer(idFormat);
    }

    // Ajout lieu déroulement de la formation::::::::
    @PostMapping("/PRESIDENTIELLE/{idFormat}") // Acception Control:::::::::::::::::::::::::::
    //@PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Formation presidentielleDemande(@PathVariable long idFormat) throws IOException {
        return  formationService.validePresidentielle(idFormat);
    }

    @PostMapping("/EN_LIGNE/{idFormat}") // Acception Control:::::::::::::::::::::::::::
    //@PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Formation enligneDemande(@PathVariable long idFormat) throws IOException {
        return  formationService.valideEnLigne(idFormat);
    }

    // Vooir Etat DemandAudit
    @GetMapping("/lieuformation")
    public List<String> getEnumValue() {
        return Arrays.stream(LieuFormation.values())
                .map(LieuFormation::name)
                .collect(Collectors.toList());
    }



    // CRUD REQUETTE:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    @PostMapping("/ajout/{id}")
   // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String create(
            @RequestParam("file")MultipartFile file,
            @PathVariable long id,
            @Valid @RequestParam(value = "formati") String donneesformation,
            @Param("formateur") String formateur) throws IOException {

        //  Partie Insertion Image
        Formation formation = new JsonMapper().readValue(donneesformation, Formation.class);
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "C:/Users/bddjiguiba/Desktop/Fin_ODC/Doniso-front-end/src/assets/images";
       ConfigImages.saveimg(uploadDir, imageName, file);

        // Envoie Notification automatiquement lors ajout formation
        Notification notification = new Notification();
        LocalDate dt = LocalDate.now();
        System.out.println(dt);
        //Utilisateurs formateur = utilisateursRepository.findById(formation.getFormateur().getId()).get();
        notification.setTitre(formation.getTitre());
        formation.setEtat(Etat.INITIE);
        Utilisateurs createur = utilisateursRepository.findById(id).get();
        formation.setCreateur(createur);
        formation.setFormateur(utilisateursRepository.findByUsername(formateur).get());
        notification.setDescription(createur.getUsername() + " a ajouté une nouvelle formation.\n Pour plus d'information, veuillez contacter " + formation.getContact());
        notification.getUtilisateurs().add(createur);
        notificationRepo.save(notification);
       formation.setImage(imageName);
       // mailSender.send(emailConstructor.constructFormateurEmail(formateur)); // Permet d'envoyer gmail
        return formationService.creer(formation);

    }

    // Voir Etat formation
    @GetMapping("/regarder/{etat}")
    //@PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Object rea(@PathVariable String etat){
        if (etat.equals("encours")){
            return formationService.voiretat(Etat.ENCOURS);
        }
        else if (etat.equals("termine")) {
            return formationService.voiretat(Etat.TERMINER);
        }else if (etat.equals("initier")){
            return formationService.voiretat(Etat.INITIE);
        } else {
            return "ssss dddd";
        }
    }


    // Voir formation
    @GetMapping("/voir")
    //@PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public List<Formation> read(){
        return formationService.lire();
    }



    // Voir formation par id
    @GetMapping("/voir/{idFormat}")
   //@PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public Formation read(@PathVariable("idFormat") Long idFormat){
        return formationService.lireParID(idFormat).get();
    }

    // Faire mise à jour formation
    @PutMapping("/update/{idFormat}")
   // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Formation update(@PathVariable Long idFormat, @RequestBody Formation formation) {
        return formationService.modifier(idFormat, formation);
    }

    // Supprimer formation formation
    @DeleteMapping("/supprimer/{idFormat}")
    //@PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String delete(@PathVariable Long idFormat){
        return formationService.supprimer(idFormat);
    }


// Voir les Etat
    @GetMapping("/enumValues")
    public List<String> getEnumValues() {
        return Arrays.stream(Etat.values())
                .map(Etat::name)
                .collect(Collectors.toList());
    }

    // Ajouter Participant à une formation



// Voir la liste des participants à une formation
    @GetMapping("/{idFormat}/participants")
    public ResponseEntity<List<Participant>> getParticipantsByFormation(@PathVariable("idFormat") Long idFormat) {
        Optional<Formation> formation = formationRepo.findById(idFormat);
        if (!formation.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(formation.get().getParticipants().stream().collect(Collectors.toList()), HttpStatus.OK);
    }


}
