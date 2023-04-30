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
public class Enseignante {

    private Integer idEnseignante;
    private String nom;
    private String pseudo;
    private String motdepass;

    public Enseignante() {
    }

    public Enseignante(Integer idEnseignante) {
        this.idEnseignante = idEnseignante;
    }

    public Enseignante(Integer idEnseignante, String nom, String pseudo, String motdepass) {
        this.idEnseignante = idEnseignante;
        this.nom = nom;
        this.pseudo = pseudo;
        this.motdepass = motdepass;
    }

    public Integer getIdEnseignante() {
        return idEnseignante;
    }

    public void setIdEnseignante(Integer idEnseignante) {
        this.idEnseignante = idEnseignante;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotdepass() {
        return motdepass;
    }

    public void setMotdepass(String motdepass) {
        this.motdepass = motdepass;
    }

}
