package com.doniso.Doniso.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="commentaire")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCom;

    private String nom;

    private String description;

    boolean b1 =  Boolean . parseBoolean( " Vrai " );

    // Liaison
    @ManyToOne
  private Formation formation;

}
