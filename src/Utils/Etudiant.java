/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.time.LocalDate;

/**
 *
 * @author Hanaa
 */

public class Etudiant {


    private Integer idEtudiant;

    private String nom ,groupe, uniteAv, dureeAv;
    private int age;
    private String niveau;
   private String ancienMemorisation;
    private String dispositionRecitation;
    private boolean autreEnseignant;
   private LocalDate dateInscription;

    public Etudiant() {
    }

    public Etudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Etudiant(Integer idEtudiant, String nom, int age, String niveau, String ancienMemorisation, String dispositionRecitation, String uniteAv, String dureeAv, boolean autreEnseignant, LocalDate dateInscription) {
        this.idEtudiant = idEtudiant;
        this.nom = nom;
        this.age = age;
        this.niveau = niveau;
        this.ancienMemorisation = ancienMemorisation;
        this.dispositionRecitation = dispositionRecitation;
        this.uniteAv = uniteAv;
        this.dureeAv = dureeAv;
        this.autreEnseignant = autreEnseignant;
        this.dateInscription = dateInscription;
    }

    public Etudiant( String nom, String groupe, int age, String niveau, String ancienMemorisation, String dispositionRecitation, boolean autreEnseignant) {
        this.nom = nom;
        this.groupe = groupe;
        this.age = age;
        this.niveau = niveau;
        this.ancienMemorisation = ancienMemorisation;
        this.dispositionRecitation = dispositionRecitation;
        this.autreEnseignant = autreEnseignant;
    }

    public Etudiant(Integer idEtudiant, String nom, String groupe, String uniteAv,String dureeAv) {
        this.idEtudiant = idEtudiant;
        this.nom = nom;
        this.groupe = groupe;
        this.uniteAv = uniteAv;
        this.dureeAv = dureeAv;
    }

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getAncienMemorisation() {
        return ancienMemorisation;
    }

    public void setAncienMemorisation(String ancienMemorisation) {
        this.ancienMemorisation = ancienMemorisation;
    }

    public String getDispositionRecitation() {
        return dispositionRecitation;
    }

    public void setDispositionRecitation(String dispositionRecitation) {
        this.dispositionRecitation = dispositionRecitation;
    }

    public boolean getAutreEnseignant() {
        return autreEnseignant;
    }

    public void setAutreEnseignant(boolean autreEnseignant) {
        this.autreEnseignant = autreEnseignant;
    }

    public String getUniteAv() {
        return uniteAv;
    }

    public void setUniteAv(String uniteAv) {
        this.uniteAv = uniteAv;
    }

    public String getDureeAv() {
        return dureeAv;
    }

    public void setDureeAv(String dureeAv) {
        this.dureeAv = dureeAv;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }
    
}
