/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.jfoenix.controls.JFXCheckBox;
import java.time.LocalDate;

/**
 *
 * @author Hanaa
 */
public class Memorisation {

    private Integer idMemorisation;
    private int idEtudiant, idSurah, idHizbHuitieme;
    private String Surah, Huitieme/*,AMemorise*/, Remarque, RemarqueM;
    private boolean Memoriser;
    private LocalDate date, dateM;
    private JFXCheckBox Memorise;

    public Memorisation() {
    }

    public Memorisation(Integer idMemorisation) {
        this.idMemorisation = idMemorisation;
    }

    public Memorisation(Integer idMemorisation, int idEtudiant, int idSurah, String Remarque) {
        this.idMemorisation = idMemorisation;
        this.idEtudiant = idEtudiant;
        this.idSurah = idSurah;
        this.Remarque = Remarque;
    }

    public Memorisation(Integer idMemorisation, int idEtudiant, int idSurah, int idHizbHuitieme, String Remarque, String RemarqueM, boolean Memoriser, LocalDate date, LocalDate dateM) {
        this.idMemorisation = idMemorisation;
        this.idEtudiant = idEtudiant;
        this.idSurah = idSurah;
        this.idHizbHuitieme = idHizbHuitieme;
        this.Remarque = Remarque;
        this.RemarqueM = RemarqueM;
        this.Memoriser = Memoriser;
        this.date = date;
        this.dateM = dateM;
    }

    /**
     *
     * @param Surah
     * @param Huitieme
     * @param Remarque
     * @param Memorise
     * @param RemarqueM
     */
    public Memorisation(/*String AMemorise,*/String Surah, String Huitieme, String Remarque, JFXCheckBox Memorise, String RemarqueM) {
      //  this.AMemorise = AMemorise;
        this.Surah = Surah;
        this.Huitieme = Huitieme;
        this.Remarque = Remarque;
        this.Memorise = new JFXCheckBox();
        this.RemarqueM = RemarqueM;

    }

    public Integer getIdMemorisation() {
        return idMemorisation;
    }

    public void setIdMemorisation(Integer idMemorisation) {
        this.idMemorisation = idMemorisation;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public int getIdSurah() {
        return idSurah;
    }

    public void setIdSurah(int idSurah) {
        this.idSurah = idSurah;
    }

    public int getIdHizbHuitieme() {
        return idHizbHuitieme;
    }

    public void setIdHizbHuitieme(int idHizbHuitieme) {
        this.idHizbHuitieme = idHizbHuitieme;
    }

    public String getRemarque() {
        return Remarque;
    }

    public void setRemarque(String Remarque) {
        this.Remarque = Remarque;
    }

    public String getRemarqueM() {
        return RemarqueM;
    }

    public void setRemarqueM(String RemarqueM) {
        this.RemarqueM = RemarqueM;
    }

    public boolean getMemoriser() {
        return Memoriser;
    }

    public void setMemoriser(boolean Memoriser) {
        this.Memoriser = Memoriser;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDateM() {
        return dateM;
    }

    public void setDateM(LocalDate dateM) {
        this.dateM = dateM;
    }

    public String getSurah() {
        return Surah;
    }

    public void setSurah(String Surah) {
        this.Surah = Surah;
    }

    public String getHuitieme() {
        return Huitieme;
    }

    public void setHuitieme(String Huitieme) {
        this.Huitieme = Huitieme;
    }

    public JFXCheckBox getMemorise() {
        return Memorise;
    }

    public void setMemorise(JFXCheckBox Memorise) {
        this.Memorise = Memorise;
    }

}
