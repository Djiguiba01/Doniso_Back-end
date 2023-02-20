package com.doniso.Doniso.Models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users",
    /*
    * garantissent l'unicité d'une colonne ou d'un ensemble de colonnes.
    * */
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
@Data
public class Utilisateurs {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 80)
  private String nomcomplet;

  @NotBlank
  @Size(max = 80)
  private String profession;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @NotBlank
  @Size(max = 25)
  private String photo;

  @NotBlank
  @Size(max = 120)
  private String contact;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private Sexe sexe;


  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(mappedBy = "formateur")
  List<Formation> formations = new ArrayList<>();


  public Utilisateurs() {
  }

  public Utilisateurs(String username, String nomcomplet, String profession, String contact,String photo, Sexe sexe,  String password, String email)
  {
    this.username = username;
    this.nomcomplet = nomcomplet;
    this.profession = profession;
    this.contact = contact;
//Sexe
    this.photo = photo;
    this.password = password;
    this.email = email;
    this.sexe = sexe;
  }

  public Utilisateurs(String username, String nomcomplet, String profession, String contact, String email, String encode, String photo) {
    this.username = username;
    this.nomcomplet = nomcomplet;
    this.profession = profession;
    this.contact = contact;
//Sexe
    this.photo = photo;
    this.password = encode;
    this.email = email;
  }

  public Utilisateurs(Long idutilisateur) {
    this.id = idutilisateur;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

}
