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

public class Groupe {

    private Integer idGroupe;
    private int idEnseignant;
    private String groupe;
    private String lien;
    private String description;
    private boolean isActive;
    private LocalDate dateCreation;

    public Groupe() {
    }

    public Groupe(Integer idGroupe) {
        this.idGroupe = idGroupe;
    }

    public Groupe(Integer idGroupe, int idEnseignant, String groupe, String lien, String description, boolean isActive, LocalDate dateCreation) {
        this.idGroupe = idGroupe;
        this.idEnseignant = idEnseignant;
        this.groupe = groupe;
        this.lien = lien;
        this.description = description;
        this.isActive = isActive;
        this.dateCreation = dateCreation;
    }

    public Groupe(int idEnseignant, String groupe, String lien, String description) {
        this.idEnseignant = idEnseignant;
        this.groupe = groupe;
        this.lien = lien;
        this.description = description;
    }

    public Integer getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(Integer idGroupe) {
        this.idGroupe = idGroupe;
    }

    public int getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(int idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
    
}
