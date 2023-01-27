package com.doniso.Doniso.Service;

import com.doniso.Doniso.Email.EmailConstructor;
import com.doniso.Doniso.Models.Etat;
import com.doniso.Doniso.Models.Formation;
import com.doniso.Doniso.Repository.FormationRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FormationServiceImpl implements FormationService{

    @Autowired
    private  final FormationRepo formationRepo;

    @Autowired
    private final EmailConstructor emailConstructor; // Connection avec class email ( EmailConstructor )

    @Autowired
    private final JavaMailSender mailSender; // Envoie gmail

    // Etat feormation:::::::::::::::::::::::::::::::::::::::::::::::
    // Encours::::::::::
    @Override
    public Formation encours(Long idFormat) {
        Formation formation = formationRepo.findFormationByIdFormat(idFormat);
        formation.setEtat(Formation.ENCOURS);
        //mailSender.send(emailConstructor.constructFormationEtat(formation));// Permet d'envoyer gmail
        return formationRepo.save(formation);
    }
    // Terminer:::::::::::
    @Override
    public Formation terminer(Long idFormat) {
        Formation formation = formationRepo.findFormationByIdFormat(idFormat);
        formation.setEtat(Formation.TERMINER);
        //mailSender.send(emailConstructor.constructFormationEtat(formation));// Permet d'envoyer gmail
        return formationRepo.save(formation);
    }
//  Voir Etat formation
    @Override
    public List<Object> voiretat(Etat etat) {
        return formationRepo.findByEtat(etat);
    }


    // CRUD::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // Condition date::::::::::::::
    @Override
    public String creer(Formation formation) {

        if(formation.getDatedebut().after(formation.getDatefin())){
            return "Veuillez donner une date corecte !";
        }
        else{
             formationRepo.save(formation);
            return "Formation ajoutée avec succès";
        }

    }

    @Override
    public List<Formation> lire() {
        return formationRepo.findAll();
    }

    @Override
    public Optional<Formation> lireParID(Long idFormat) {
        return formationRepo.findById(idFormat);
    }


    @Override
    public Formation modifier(Long idFormat, Formation formation) {
        return formationRepo.findById(idFormat)
                .map(f-> {
                    f.setTitre(formation.getTitre());
                    f.setLieu(formation.getLieu());
                    f.setDescription(formation.getDescription());
                    f.setContact(formation.getContact());
                    f.setHeure(formation.getHeure());
                    f.setEmailformateur(formation.getEmailformateur());
                    f.setDatedebut(formation.getDatedebut());
                    f.setDatefin(formation.getDatefin());
                    f.setImage(formation.getImage());
                    f.setEtat(formation.getEtat());
                    return  formationRepo.save(f);
                }).orElseThrow(() -> new RuntimeException("Formation non trouvée !!"));
    }

    @Override
    public String supprimer(Long idFormat) {

        formationRepo.deleteById(idFormat);
        return "Formation supprimée !";
    }
}
