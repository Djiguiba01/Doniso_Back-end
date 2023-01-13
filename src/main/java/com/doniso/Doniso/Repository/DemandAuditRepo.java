package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.DemandAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandAuditRepo extends JpaRepository<DemandAudit, Long> {
}
