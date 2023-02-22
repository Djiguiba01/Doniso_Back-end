package com.doniso.Doniso.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotif;
    @Column(length = 1120)
    private  String titre;

    @Column(length = 1000)
    private String description;

    @ManyToMany
    Set<Utilisateurs> utilisateurs = new HashSet<>();


}
