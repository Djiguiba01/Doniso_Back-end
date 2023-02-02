package com.doniso.Doniso.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="demande")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Participant {

    public static final ValidParticipant ENCOURS_TRAITEMENT = ValidParticipant.ENCOURS_TRAITEMENT;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPart;

    @Column(length = 20)
    private  String nom;

    private String deuxNom;

  /*  @Column(length = 12)
    private  String genre;
   */
    @Column(length = 50)
    @Email
    private  String email;

    @Column(length = 60)
    private String profession;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Sexe sexe;

    // Les liaisons
    @Enumerated(EnumType.STRING)
    @Column(length = 40)
    private ValidParticipant status;

    // Liaison
    @ManyToMany
    List<Utilisateurs> utilisateurs = new ArrayList<>();
}
