package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.DemandAudit;
import com.doniso.Doniso.Models.Etat;
import com.doniso.Doniso.Models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FormationRepo extends JpaRepository<Formation, Long> {

    // Validation::::::::
    Formation findFormationByIdFormat(Long idFormat); // Methode validation demande formation par id

    // ::::::::::::::
    Formation findByTitre(String titre);

}
