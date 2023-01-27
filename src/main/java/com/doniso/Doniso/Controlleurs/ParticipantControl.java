package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Models.Participant;
import com.doniso.Doniso.Models.ValidParticipant;
import com.doniso.Doniso.Service.ParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins ={ "http://localhost:4200/", "http://localhost:8100/" }, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/participant")
@AllArgsConstructor
public class ParticipantControl {
    @Autowired
    private final ParticipantService participantService;


    // Validation Control ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // Valide Control:::::::::::::::::::::::::::
    @PostMapping("/valide/{idPartipant}")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Participant valideParticipant(@PathVariable long idPartipant) throws IOException {
        return  participantService.valideParticipant(idPartipant);
    }
    // Non Valide Control:::::::::::::::::::::::::::
    @PostMapping("/rejeter/{idParticipant}")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Participant nonvalideParticipant(@PathVariable long idParticipant) throws IOException {
        return  participantService.nonvalideParticipant(idParticipant);
    }

    // Voir Etat formation
    @GetMapping("/status/{statuspart}")
    @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public Object reaObject(@PathVariable String statuspart){
        if (statuspart.equals("desoler")){
            return participantService.voirParticipantstatus(ValidParticipant.NON_VALIDER);
        }
        else if (statuspart.equals("valider")) {
            return participantService.voirParticipantstatus(ValidParticipant.VALIDER);
        }else if (statuspart.equals("encours")){
            return participantService.voirParticipantstatus(ValidParticipant.ENCOURS_TRAITEMENT);
        } else {
            return "Veuilez réesayer";
        }
    }




    // CRUD CONTROL :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    @PostMapping("/ajout")
    //@PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public Participant create(@RequestBody Participant participant) {
        return  participantService.creer(participant);
    }

    @GetMapping("/voir")
 //   @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<Participant> read(){
        return participantService.lire();
    }

    @GetMapping("/voir/{idPart}")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Optional<Participant> read(@PathVariable("idPart") Long idPart){
        return participantService.lireParID(idPart);
    }

    @PutMapping("/update/{idPart}")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Participant update(@PathVariable Long idPart, @RequestBody Participant participant) {
        return participantService.modifier(idPart, participant);
    }

    @DeleteMapping("supprimer/{idpart}")
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String delete(@PathVariable Long idpart){
        return participantService.supprimer(idpart);
    }
}
