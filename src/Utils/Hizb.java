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

public class Hizb   {


    private Integer idHizbHuitieme;

    private int hizb, huitieme;
    private String idSourah;

    public Hizb() {
    }

    public Hizb(Integer idHizbHuitieme) {
        this.idHizbHuitieme = idHizbHuitieme;
    }

    public Hizb(Integer idHizbHuitieme, int hizb, int huitieme, String idSourah) {
        this.idHizbHuitieme = idHizbHuitieme;
        this.hizb = hizb;
        this.huitieme = huitieme;
        this.idSourah = idSourah;
    }

    public Integer getIdHizbHuitieme() {
        return idHizbHuitieme;
    }

    public void setIdHizbHuitieme(Integer idHizbHuitieme) {
        this.idHizbHuitieme = idHizbHuitieme;
    }

    public int getHizb() {
        return hizb;
    }

    public void setHizb(int hizb) {
        this.hizb = hizb;
    }

    public int getHuitieme() {
        return huitieme;
    }

    public void setHuitieme(int huitieme) {
        this.huitieme = huitieme;
    }

    public String getIdSourah() {
        return idSourah;
    }

    public void setIdSourah(String idSourah) {
        this.idSourah = idSourah;
    }



}
