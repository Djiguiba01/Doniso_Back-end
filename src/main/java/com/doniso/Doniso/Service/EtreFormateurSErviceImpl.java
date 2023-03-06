package com.doniso.Doniso.Service;


import com.doniso.Doniso.Models.EtreFormateur;
import com.doniso.Doniso.Repository.DemandAuditRepo;
import com.doniso.Doniso.Repository.EtreFormateurRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class EtreFormateurSErviceImpl implements EtreFormateurService{

    @Autowired
    private  final EtreFormateurRepo etreFormateurRepo;

    @Override
    public EtreFormateur creer(EtreFormateur etreFormateur) {
        return etreFormateurRepo.save(etreFormateur);
    }

    @Override
    public List<EtreFormateur> lire() {
       return etreFormateurRepo.findAll();
    }

    @Override
    public Optional<EtreFormateur> lireParID(Long idetreform) {
        return etreFormateurRepo.findById(idetreform);
    }

    @Override
    public EtreFormateur modifier(Long idetreform, EtreFormateur etreFormateur) {
        return etreFormateurRepo.findById(idetreform)
                .map(d-> {
                    d.setEmail(etreFormateur.getEmail());
                    d.setImagecv(etreFormateur.getImagecv());
                    return  etreFormateurRepo.save(d);
                }).orElseThrow(() -> new RuntimeException("Démande non trouvée !!"));
    }

    @Override
    public String supprimer(Long idetreform) {
        etreFormateurRepo.deleteById(idetreform);
        return "Démande supprimée !";
    }
}
