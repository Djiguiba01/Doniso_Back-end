package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.Formation;
import com.doniso.Doniso.Repository.FormationRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FormationServiceImpl implements FormationService{

    @Autowired
    private  final FormationRepo formationRepo;

    @Override
    public Formation creer(Formation formation) {


            return formationRepo.save(formation);

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
