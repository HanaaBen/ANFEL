/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;



/**
 *
 * @author Hanaa
 */
public class AvoirGroupe  {


    private int idEtudiant;
    private int idGroupe;

    public AvoirGroupe() {
    }

    public AvoirGroupe(int idEtudiant, int idGroupe) {
        this.idEtudiant = idEtudiant;
        this.idGroupe = idGroupe;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }

   
    
}
