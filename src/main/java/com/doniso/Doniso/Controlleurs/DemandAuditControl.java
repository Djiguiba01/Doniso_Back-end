package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Models.DemandAudit;
import com.doniso.Doniso.Models.Utilisateurs;
import com.doniso.Doniso.Repository.UtilisateursRepository;
import com.doniso.Doniso.Service.DemandAuditService;
import com.doniso.Doniso.payload.Autres.ConfigImages;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins ={ "http://localhost:4200/", "http://localhost:8100/" }, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/demandpart")
@AllArgsConstructor
public class DemandAuditControl {

    private final DemandAuditService demandAuditService; // Injection demande service
    private final UtilisateursRepository utilisateursRepository;

   /* @PostMapping("/ajout")
    @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public DemandAudit create(@RequestBody DemandAudit demandAudit, @Param("file") MultipartFile file) throws IOException {
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
        demandAudit.setPhoto(nomfile);
         String uploaDir = "C:/Projet_Ionic/Doniso/src/assets/images/Back-end";
          ConfigImages.saveimg(uploaDir, nomfile, file);

        return  demandAuditService.creer(demandAudit);
    }

    */

   // Validation Control ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
   @PostMapping("/accepter/{idDemande}") // Acception Control:::::::::::::::::::::::::::
   //@PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
   public DemandAudit accepterDemande(@PathVariable long idDemande) throws IOException {
       return  demandAuditService.valideAudit(idDemande);
   }
    @PostMapping("/refus/{idDemande}") // Refus Control::::::::::::::::::::::::::::::
   // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public DemandAudit refusDemande(@PathVariable long idDemande) throws IOException {
        return  demandAuditService.refugeAudit(idDemande);
    }

    // Voir formations par utilisateur::::::::::::::::::::
    @GetMapping("/voirformation/{idutilisateur}")
    public List<DemandAudit> voirformationdemand (@PathVariable Long idutilisateur){
        Utilisateurs utilisateurs= utilisateursRepository.findById(idutilisateur).get();
        return demandAuditService.Voirparutilisateur(utilisateurs);
    }


    // CRUD CONTROL :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
      @PostMapping("/ajout/{idutilisateur}")
    //@PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public DemandAudit create(@Valid @RequestParam(value = "donneesaudit") String donneesaudit,
                              @Param("file") MultipartFile file,
                              @PathVariable Long idutilisateur) throws IOException {
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
        DemandAudit demandAudit1 = new JsonMapper().readValue(donneesaudit, DemandAudit.class);
        demandAudit1.setUtilisateurs(new Utilisateurs(idutilisateur));
        demandAudit1.setPhoto(nomfile);
        String uploaDir = "C:/Projet_Ionic/Doniso/src/assets/images/Back-end";
        ConfigImages.saveimg(uploaDir, nomfile, file);

        return  demandAuditService.creer(demandAudit1);
    }




    @GetMapping("/voir")
   // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<DemandAudit> read(){
        return demandAuditService.lire();
    }

    @GetMapping("/voir/{idDemand}")
   // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Optional<DemandAudit> read(@PathVariable("idDemand") Long idDemand){
        return demandAuditService.lireParID(idDemand);
    }

    @PutMapping("/update/{idDemand}")
   // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public DemandAudit update(@PathVariable Long idDemand, @RequestBody DemandAudit demandAudit) {
        return demandAuditService.modifier(idDemand, demandAudit);
    }

    @DeleteMapping("/supprimer/{idDemand}")
    //@PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String delete(@PathVariable Long idDemand){
        return demandAuditService.supprimer(idDemand);
    }


}
