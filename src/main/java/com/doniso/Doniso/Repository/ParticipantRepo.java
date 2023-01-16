package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.Participant;
import com.doniso.Doniso.Models.ValidParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepo extends JpaRepository<Participant, Long> {
    Participant findParticipantByIdPart(Long idPart);


    List<Object> findByStatus(ValidParticipant validParticipant); // Voir Validation participant


}
