package com.example.sge.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String cin;
    private LocalDate dateNaissance;

    private double moyenne; // ✅ champ important pour les requêtes

    private String groupe;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module; // ✅ relation ajoutée avec Module

    // ===== CONSTRUCTEURS =====
    public Etudiant() {}

    public Etudiant(String nom, String prenom, String email, String cin,
                    LocalDate dateNaissance, double moyenne, String groupe) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
        this.moyenne = moyenne;
        this.groupe = groupe;
    }

    // ===== GETTERS & SETTERS =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public double getMoyenne() { return moyenne; }
    public void setMoyenne(double moyenne) { this.moyenne = moyenne; }

    public String getGroupe() { return groupe; }
    public void setGroupe(String groupe) { this.groupe = groupe; }

    public Filiere getFiliere() { return filiere; }
    public void setFiliere(Filiere filiere) { this.filiere = filiere; }

    public Module getModule() { return module; }
    public void setModule(Module module) { this.module = module; }
}