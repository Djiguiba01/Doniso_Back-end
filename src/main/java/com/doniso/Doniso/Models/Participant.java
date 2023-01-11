package com.doniso.Doniso.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(length = 15)
    private String Prenom;

    @Column(length = 12)
    private  String genre;
    @Column(length = 12)
    private  String contact;

    @Column(length = 12)
    private  String Profession;
}
