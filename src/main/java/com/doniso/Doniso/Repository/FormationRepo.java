package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepo extends JpaRepository<Formation, Long> {
}
