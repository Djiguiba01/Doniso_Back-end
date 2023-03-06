package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.DemandAudit;
import com.doniso.Doniso.Models.EtreFormateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtreFormateurRepo extends JpaRepository<EtreFormateur, Long> {


}
