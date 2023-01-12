package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Models.Participant;
import com.doniso.Doniso.Service.ParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participant")
@AllArgsConstructor
public class ParticipantControl {
    @Autowired
    private final ParticipantService participantService;

    @PostMapping("/ajout")
    public Participant create(@RequestBody Participant participant) {
        return  participantService.creer(participant);
    }

    @GetMapping("/voir")
    public List<Participant> read(){
        return participantService.lire();
    }

    @GetMapping("/voir/{idPart}")
    public Optional<Participant> reada(@PathVariable("idPart") Long idPart){
        return participantService.lireParID(idPart);
    }

    @PutMapping("/update/{idPart}")
    public Participant update(@PathVariable Long idPart, @RequestBody Participant participant) {
        return participantService.modifier(idPart, participant);
    }

    @DeleteMapping("supprimer/{idpart}")
    public String delete(@PathVariable Long idpart){
        return participantService.supprimer(idpart);
    }
}
