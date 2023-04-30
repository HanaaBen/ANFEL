/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utils.Hizb;
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
public class HizbModel {

    private Connection con;
    private Statement st = null;
    private ResultSet rs = null;

    public ObservableList<Hizb> Afficher_Liste_Hizb() {
        ObservableList<Hizb> listHizb = FXCollections.observableArrayList();
        Hizb h;
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM hizb ORDER BY idHizbHuitieme");//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            while (rs.next()) {
                h = new Hizb(rs.getInt("idHizbHuitieme"), rs.getInt("Hizb"), rs.getInt("huitieme"), rs.getString("idSurah"));
                listHizb.add(h);
                /**
                 * *****************teste affichage*******************
                 */
                System.out.println("affichage des Hizb ");
                System.out.print(rs.getInt("Hizb") + "\t");
                System.out.print(rs.getString("idSurah") + "\t");
                System.out.println();
            }
            return listHizb;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Hizb getSurah(Hizb h) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM hizb where ( Hizb = " + h.getHizb() + " And huitieme = " + h.getHuitieme() + " )");//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            if (rs.next()) {

                h = new Hizb(rs.getInt("idHizbHuitieme"), rs.getInt("Hizb"), rs.getInt("huitieme"), rs.getString("idSurah"));
            }
            return h;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Hizb getHizbHuitieme(int idHizbHuitieme) {
        Hizb h = new Hizb();
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM hizb where idHizbHuitieme = " + idHizbHuitieme);
            if (rs.next()) {
                h = new Hizb(rs.getInt("idHizbHuitieme"), rs.getInt("Hizb"), rs.getInt("huitieme"), rs.getString("idSurah"));

            }
            return h;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ObservableList<Hizb> getHizb(int Hizb) {

        ObservableList<Hizb> h = FXCollections.observableArrayList();
        if (Hizb != 0) {
            try {
                if (con == null) {
                    con = new DBConnection().Connect();
                }
                String query = "SELECT * FROM hizb where Hizb = '" + Hizb + "'";
                st = con.createStatement();
                rs = st.executeQuery(query);
                // une boucle while pour recupérer tous les lignes    
                while (rs.next()) {
                    h.add(new Hizb(rs.getInt("idHizbHuitieme"), rs.getInt("Hizb"), rs.getInt("huitieme"), rs.getString("idSurah")));
                }
                return h;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public void Archiver_Hizb() {
    }
}
