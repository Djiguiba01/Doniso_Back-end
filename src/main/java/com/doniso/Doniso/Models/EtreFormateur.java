package com.doniso.Doniso.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name="etreFormateur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtreFormateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idetreform;

    @Email
    private String email;
    private  String imagecv;
}
