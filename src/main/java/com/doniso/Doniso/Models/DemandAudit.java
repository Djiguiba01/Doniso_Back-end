package com.doniso.Doniso.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name="DemandeFormation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemandAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemand;

    private String structure;

    private String lieu;

    @Email
    private String email;

    private String type;

    private int personnes;

    private String photo;

    // Les liaisons
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AuditDemand auditstatus;


}
