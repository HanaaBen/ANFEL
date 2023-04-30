/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utils.Surah;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;

/**
 *
 * @author Hanaa
 */
public class SurahModel {

    private Connection con;
    private Statement st = null;
    private ResultSet rs = null;


    public ObservableList<Surah> Afficher_Liste_Surah() {
        ObservableList<Surah> listSurah = FXCollections.observableArrayList();
        Surah h;
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM surah ORDER BY id");//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            while (rs.next()) {
                h = new Surah(rs.getInt("id"), rs.getString("arabic"), rs.getInt("ayah"));
                listSurah.add(h);
                /**
                 * *****************teste affichage*******************
                 */
                System.out.println("affichage des Surah ");
                System.out.print(rs.getString("arabic") + "\t");
                System.out.print(rs.getInt("ayah") + "\t");
                System.out.println();
            }
            return listSurah;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public ObservableList<String> Afficher_Liste_NomSurah() {
        ObservableList<String> listSurah = FXCollections.observableArrayList();
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT arabic FROM surah ORDER BY id");//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            while (rs.next()) {
                listSurah.add(rs.getString("arabic"));
            }
            return listSurah;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Surah getSurah(int id) {
        Surah h = new Surah();
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }

            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM surah where id = "+id);//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            if (rs.next()) {
                h = new Surah(rs.getInt("id"), rs.getString("arabic"), rs.getInt("ayah"));
                
            }
            return h;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

  
}
