package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Email.EmailConstructor;
import com.doniso.Doniso.Models.ERole;
import com.doniso.Doniso.Models.Role;
import com.doniso.Doniso.Models.Sexe;
import com.doniso.Doniso.Models.Utilisateurs;
import com.doniso.Doniso.Repository.RoleRepository;
import com.doniso.Doniso.Repository.UtilisateursRepository;
import com.doniso.Doniso.payload.Autres.ConfigImages;
import com.doniso.Doniso.payload.request.LoginRequest;
import com.doniso.Doniso.payload.request.SignupRequest;
import com.doniso.Doniso.payload.response.JwtResponse;
import com.doniso.Doniso.payload.response.MessageResponse;
import com.doniso.Doniso.security.jwt.JwtUtils;
import com.doniso.Doniso.security.services.UtilisateursDetails;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/* In AuthController.java */
// @CrossOrigin(origins = "*", maxAge = 3600)
//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins ={ "http://localhost:4200/", "http://localhost:8100/" }, maxAge = 3600, allowCredentials="true")

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  /// em
  @Autowired
  private EmailConstructor emailConstructor;

  @Autowired
  private JavaMailSender mailSender;

  private static final Logger log = LoggerFactory.getLogger(AuthController.class);

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UtilisateursRepository utilisateursRepository;

  @Autowired
  RoleRepository roleRepository;

  //encoder du password
  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  //@Valid assure la validation de l'ensemble de l'objet
  @PostMapping("/connexion")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    String url = "C:/Users/bddjiguiba/Desktop/Soutenance_ODC/Doniso-front-end/src/assets/images";

    /*
    String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
    System.out.println(nomfile);
    ConfigImage.saveimgA(url, nomfile, file);
    */


    /*
     AuthenticationManager est comme un coordinateur o?? vous pouvez enregistrer plusieurs fournisseurs et,
     en fonction du type de demande, il enverra une demande d'authentification au bon fournisseur.
     */

    //authenticate effectue l'authentification avec la requ??te.

     /*
       AuthenticationManager utilise DaoAuthenticationProvider(avec l'aide de
       UserDetailsService& PasswordEncoder) pour valider l'instance de UsernamePasswordAuthenticationToken,
       puis renvoie une instance enti??rement remplie Authenticationen cas d'authentification r??ussie.
     */

    Authentication authentication = authenticationManager.authenticate(
            //on lui fournit un objet avec username et password fournit par l'admin
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    /*
      SecurityContext et SecurityContextHolder sont deux classes fondamentales de Spring Security .
      Le SecurityContext est utilis?? pour stocker les d??tails de l'utilisateur
      actuellement authentifi??, ??galement appel?? principe. Donc, si vous devez obtenir
      le nom d'utilisateur ou tout autre d??tail d'utilisateur, vous devez d'abord obtenir
      ce SecurityContext . Le SecurityContextHolder est une classe d'assistance qui permet
      d'acc??der au contexte de s??curit??.
     */

    //on stocke les informations de connexion de l'utilisateur actuelle souhaiter se connecter dans SecurityContextHolder
    SecurityContextHolder.getContext().setAuthentication(authentication);

    //on envoie encore les infos au generateur du token
    String jwt = jwtUtils.generateJwtToken(authentication);

    //on recupere les infos de l'user
    UtilisateursDetails utilisateursDetails = (UtilisateursDetails) authentication.getPrincipal();

    //on recupere les roles de l'users
    List<String> roles = utilisateursDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    log.info("conexion controlleur");

    //on retourne une reponse, contenant l'id username, e-mail et le role du collaborateur
    return ResponseEntity.ok(new JwtResponse(jwt,
                         utilisateursDetails.getId(),
                         utilisateursDetails.getUsername(),
                         utilisateursDetails.getEmail(), roles,
                         utilisateursDetails.getContact(),
                         utilisateursDetails.getProfession(),
                         utilisateursDetails.getNomcomplet(),
                         utilisateursDetails.getPhoto()
                         ));
  }

  //@PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/inscription")//@valid s'assure que les donn??es soit valid??es
  public ResponseEntity<?> registerUser(

          @Valid @RequestParam(value = "file", required = true) MultipartFile file,
          @Valid  @RequestParam(value = "data") String donneesuser) throws IOException {

    //chemin de stockage des images
   String url = "C:/Projet_Ionic/Doniso/src/assets/images/Back-end";

    //recupere le nom de l'image
    String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
   System.out.println(nomfile);

    //envoie le nom, url et le fichier ?? la classe ConfigImages qui se chargera de sauvegarder l'image
   ConfigImages.saveimg(url, nomfile, file);



    //converssion du string re??u en classe SignupRequest
    SignupRequest signUpRequest = new JsonMapper().readValue(donneesuser, SignupRequest.class);



    signUpRequest.setPhoto(nomfile);


    if (utilisateursRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Erreur: Ce nom d'utilisateur existe d??j??!"));
    }

    if (utilisateursRepository.existsByEmail(signUpRequest.getEmail())) {

      //confectionne l'objet de retour ?? partir de ResponseEntity(une classe de spring boot) et MessageResponse
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Erreur: Cet email est d??j?? utilis??!"));
    }

    // Create new user's account
    Utilisateurs utilisateurs = new Utilisateurs(signUpRequest.getUsername(), signUpRequest.getNomcomplet(), signUpRequest.getProfession(),
            signUpRequest.getContact(), signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword())
                , signUpRequest.getPhoto()
            );

    //on recupere le role de l'user dans un tableau ordonn?? de type string
    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      System.out.println("####################################" + signUpRequest.getRole() + "###########################################");

      //on recupere le role de l'utilisateur
      Role userRole = roleRepository.findByName(ERole.ROLE_USER);
      roles.add(userRole);//on ajoute le role de l'user ?? roles
    } else {
      strRoles.forEach(role -> {//on parcours le role
        switch (role) {
        case "admin"://si le role est ?? ??gale ?? admin
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
          roles.add(adminRole);

            case "formateur"://si le role est ?? ??gale ?? admin
              Role formateurRole = roleRepository.findByName(ERole.ROLE_FORMATEUR);
              roles.add(formateurRole);

          break;
        default://dans le cas ??cheant

          //on recupere le role de l'utilisateur
          Role userRole = roleRepository.findByName(ERole.ROLE_USER);
          roles.add(userRole);
        }
      });
    }

    //on ajoute le role au collaborateur
    utilisateurs.setRoles(roles);

    if(signUpRequest.getSexe().equals("homme")){
      utilisateurs.setSexe(Sexe.HOMME);
    }else {
      utilisateurs.setSexe(Sexe.FEMME);
    }

    utilisateursRepository.save(utilisateurs);
    //em
    //mailSender.send(emailConstructor.constructNewUserEmail(utilisateurs));

    return ResponseEntity.ok(new MessageResponse("Utilisateur enregistr?? avec succ??s!"));
  }

}
