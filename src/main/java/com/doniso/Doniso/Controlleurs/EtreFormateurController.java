package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Models.DemandAudit;
import com.doniso.Doniso.Models.EtreFormateur;
import com.doniso.Doniso.Models.Utilisateurs;
import com.doniso.Doniso.Service.DemandAuditService;
import com.doniso.Doniso.Service.EtreFormateurService;
import com.doniso.Doniso.payload.Autres.ConfigImages;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@CrossOrigin(origins ={ "http://localhost:4200/", "http://localhost:8100" }, maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/demandetreformateur")
@AllArgsConstructor
public class EtreFormateurController {

    private final EtreFormateurService etreFormateurService; // Injection demande service



    @GetMapping("/voir")
    // @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<EtreFormateur> read(){
        return etreFormateurService.lire();
    }

    @PostMapping("/ajout")
    //@PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public EtreFormateur create(@Valid @RequestParam(value = "donneesaudit") String donneesaudit,
                                @Param("file") MultipartFile file) throws IOException {
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
        EtreFormateur demandAudit1 = new JsonMapper().readValue(donneesaudit, EtreFormateur.class);
//        demandAudit1.setUtilisateurs(new Utilisateurs(idutilisateur));
        demandAudit1.setImagecv(nomfile);
        String uploaDir = "C:/Users/bddjiguiba/Desktop/Soutenance_ODC/Doniso_Dashboard/src/assets/images";
        ConfigImages.saveimg(uploaDir, nomfile, file);
        return  etreFormateurService.creer(demandAudit1);
    }

}
