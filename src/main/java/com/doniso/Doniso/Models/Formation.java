package com.doniso.Doniso.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Formation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Formation {

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

    @JsonFormat(pattern = "yyyy-MM-dd", shape =  JsonFormat.Shape.STRING)
    @Column(length = 16)
    private LocalDate datedebut;

    @JsonFormat(pattern = "yyyy-MM-dd", shape =  JsonFormat.Shape.STRING)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(length = 16)
    private LocalDate datefin;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Etat etat;

    // Liaison
    @OneToMany(mappedBy = "formation")
    List<Commentaire> commentaires = new ArrayList<>();
}
