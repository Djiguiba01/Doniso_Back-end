package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepo extends JpaRepository<Participant, Long> {
    Participant findParticipantByIdPart(Long idPart);
}
