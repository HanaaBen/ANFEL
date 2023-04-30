/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.time.LocalTime;

/**
 *
 * @author Hanaa
 */

public class Emploi  {


    private Integer idEmploi;
    private int idGroupe;
    private String jour;
    private LocalTime heure;
    
    private boolean isValid;

    public Emploi() {
    }

    public Emploi(Integer idEmploi) {
        this.idEmploi = idEmploi;
    }

    public Emploi(int idEmploi, int idGroupe, String jour, LocalTime heure, boolean isValid) {
        this.idEmploi = idEmploi;
        this.idGroupe = idGroupe;
        this.jour = jour;
        this.heure = heure;
        this.isValid = isValid;
    }


    public Integer getIdEmploi() {
        return idEmploi;
    }

    public void setIdEmploi(Integer idEmploi) {
        this.idEmploi = idEmploi;
    }

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    
}
