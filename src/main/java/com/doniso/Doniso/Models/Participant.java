package com.doniso.Doniso.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name="participant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPart;

    @Column(length = 20)
    private  String nom;

    private String deuxNom;

    @Column(length = 12)
    private  String genre;
    @Column(length = 50)
    @Email
    private  String email;

    @Column(length = 60)
    private String profession;

    // Les liaisons

    @Enumerated(EnumType.STRING)
    @Column(length = 40)
    private ValidParticipant status;
}
