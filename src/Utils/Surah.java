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

public class Surah  {


    private Integer id;
    private String arabic;
    private int ayah;

    public Surah() {
    }

    public Surah(Integer id) {
        this.id = id;
    }

    public Surah(Integer id, String arabic, int ayah) {
        this.id = id;
        this.arabic = arabic;
        this.ayah = ayah;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public int getAyah() {
        return ayah;
    }

    public void setAyah(int ayah) {
        this.ayah = ayah;
    }

   
    
}
