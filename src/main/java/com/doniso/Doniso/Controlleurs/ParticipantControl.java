package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Models.*;
import com.doniso.Doniso.Repository.FormationRepo;
import com.doniso.Doniso.Repository.ParticipantRepo;
import com.doniso.Doniso.Service.FormationService;
import com.doniso.Doniso.Service.ParticipantService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.doniso.Doniso.Models.ValidParticipant.VALIDER;

@CrossOrigin(origins ={ "http://localhost:4200/", "http://localhost:8100/" }, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/participant")
@AllArgsConstructor
public class ParticipantControl {
    @Autowired
    private final ParticipantService participantService;
    @Autowired
    private final FormationService formationService;
    @Autowired
    private FormationRepo formationRepo;
    @Autowired
    private ParticipantRepo participantRepo;


    // Validation Control ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // Valide Control:::::::::::::::::::::::::::

    @PostMapping("/ENCOURS_TRAITEMENT/{idPartipant}")
    // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Participant encoursParticipant(@PathVariable long idPartipant) throws IOException {
        return  participantService.encoursParticipant(idPartipant);
    }

    @PostMapping("/VALIDER/{idPartipant}")
   // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Participant valideParticipant(@PathVariable long idPartipant) throws IOException {
        return  participantService.valideParticipant(idPartipant);
    }
    // Non Valide Control:::::::::::::::::::::::::::
    @PostMapping("/NON_VALIDER/{idParticipant}")
   // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Participant nonvalideParticipant(@PathVariable long idParticipant) throws IOException {
        return  participantService.nonvalideParticipant(idParticipant);
    }

   // @GetMapping("/voirformationuser/{idutilisateur}")
   // public List<Formation> voirformationparticp(@PathVariable Long idutilisateur){
     //   return participantService.Voirlisteformationparticiper(idutilisateur);
    //}



    // Voir Etat formation
    @GetMapping("/status/{statuspart}")
    //@PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public Object reaObject(@PathVariable String statuspart){
        if (statuspart.equals("desoler")){
            return participantService.voirParticipantstatus(ValidParticipant.NON_VALIDER);
        }
        else if (statuspart.equals("valider")) {
            return participantService.voirParticipantstatus(VALIDER);
        }else if (statuspart.equals("encours")){
            return participantService.voirParticipantstatus(ValidParticipant.ENCOURS_TRAITEMENT);
        } else {
            return "Veuilez réesayer";
        }
    }

    // Voir les Etat participant
    @GetMapping("/enumValues")
    public List<String> getEnumValues() {
        return Arrays.stream(ValidParticipant.values())
                .map(ValidParticipant::name)
                .collect(Collectors.toList());
    }


    // CRUD CONTROL :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    @PostMapping("/ajout/{idFormat}")
    //@PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public Participant create(@RequestParam("participant") String participant2,@PathVariable("idFormat") Long idFormat) {
        try{
            Participant participant = new JsonMapper().readValue(participant2, Participant.class);
            Formation idFormation = formationService.lireParID(idFormat).get();
            Participant participant1 = new Participant(
                    participant.getNom(),participant.getDeuxNom(), participant.getEmail(),
                    participant.getProfession(),participant.getSexe()
            );
            if(idFormation != null){
                participant1.setFormation(idFormation);
                return  participantService.creer(participant1);
            }else {
                return null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/voir")
 //   @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<Participant> read(){
        return participantService.lire();
    }


    @GetMapping("/voir/{idPart}")
   // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Participant read(@PathVariable("idPart") Long idPart){
        return participantService.lireParID(idPart).get();
    }


    @PutMapping("/update/{idPart}")
   // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Participant update(@PathVariable Long idPart, @RequestBody Participant participant) {
        return participantService.modifier(idPart, participant);
    }

    @DeleteMapping("supprimer/{idpart}")
   // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String delete(@PathVariable Long idpart){
        return participantService.supprimer(idpart);
    }

    // Voir tout les participants Par formation
    @GetMapping("/voirpart/{idFormat}")
    public List<Participant> formations(@PathVariable("idFormat") Long idFormat){
        Formation formation = formationRepo.getReferenceById(idFormat);
        System.err.println(formation);
        return participantRepo.getByformation(idFormat);
    }

    // Voir tout les participants Par formation en fonction de son status
    @GetMapping("/encoursparticip/{status}/{idFormat}")
    public List<Participant> formationsencours(@PathVariable("idFormat") Formation idFormat, @PathVariable ValidParticipant status){
        List<Participant> participants = participantRepo.findAll();
                return participantRepo.findByFormationAndStatus(idFormat, status);
    }



}
