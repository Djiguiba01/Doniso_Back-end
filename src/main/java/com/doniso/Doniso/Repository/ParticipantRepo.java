package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.Formation;
import com.doniso.Doniso.Models.Participant;
import com.doniso.Doniso.Models.ValidParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepo extends JpaRepository<Participant, Long> {

    // La liste des formations par participant
   // List<Formation> findFormationsByParticipant(@Param("idPart") Long idPart);

    Participant findParticipantByIdPart(Long idPart);


    List<Object> findByStatus(ValidParticipant validParticipant); // Voir Validation participant


}
