package com.doniso.Doniso.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Formation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Formation {

    // A voir après
    public static final Etat ENCOURS = Etat.ENCOURS ;
    public static final Etat TERMINER = Etat.TERMINER;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormat;

    @Column(length = 15)
    private  String titre;

    //@Column(length = 15)
    //@Lob
    //@Column(name = "images", length = 900)
    private  String image;
    @Column(length = 20)
    private String lieu;
    @Column(length = 50)
    private  String description;

    @Column(length = 50)
    private  int contact;

    @Column(length = 15)
    private  int heure;

   /* @NotBlank
    @Size(max = 50)
    @Email
    private String emailformateur;
    */

    @JsonFormat(pattern = "yyyy-MM-dd", shape =  JsonFormat.Shape.STRING)
    @Column(length = 16)
    private Date datedebut;

    @JsonFormat(pattern = "yyyy-MM-dd", shape =  JsonFormat.Shape.STRING)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(length = 16)
    private Date datefin;

    // Les liaisons
    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private Etat etat;

    @OneToMany(mappedBy = "formation")
    List<Commentaire> commentaires = new ArrayList<>();

    @ManyToOne
    Utilisateurs createur;

    @ManyToOne
    Utilisateurs formateur;

    public Formation(long idformation) {
        this.idFormat = idformation;
    }
}
