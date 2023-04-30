/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utils.Emploi;
import Utils.Emploi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Hanaa
 */
public class EmploiModel {

    private Connection con;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    public boolean ajouter_Emploi(Emploi e) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "INSERT INTO emploi ( idGroupe, jour, heure, isValid) VALUES( ?,?,?,? )";
            System.out.println("query " + query);
            ps = con.prepareStatement(query);
            ps.setInt(1, e.getIdGroupe());
            ps.setString(2, e.getJour());
            ps.setTime(3, Time.valueOf(e.getHeure()));
            ps.setBoolean(4, e.getIsValid());
            ps.executeUpdate();

            System.out.println("Emploi bien ajoutee");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Emploi pas ajoutee");
            return false;
        }
    }

    public ObservableList<Emploi> Afficher_Liste_Emploi() {
        ObservableList<Emploi> listEmploi = FXCollections.observableArrayList();
        Emploi e;
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }

            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM emploi ORDER BY idEmploi");//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            while (rs.next()) {
                e = new Emploi(rs.getInt("idEmploi"), rs.getInt("idGroupe"), rs.getString("jour"), rs.getTime("heure").toLocalTime(), rs.getBoolean("isValid"));
                listEmploi.add(e);
                /**
                 * *****************teste affichage*******************
                 */
                System.out.println("affichage des emploi ");
                System.out.print(rs.getInt("idEmploi") + "\t");
                System.out.print(rs.getString("jour") + "\t");
                System.out.println();
            }
            return listEmploi;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Emploi getLastEmploi() {
        Emploi e = new Emploi();
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM emploi ORDER BY idEmploi DESC LIMIT 0,1");//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            if (rs.next()) {
                e = new Emploi(rs.getInt("idEmploi"), rs.getInt("idGroupe"), rs.getString("jour"), rs.getTime("heure").toLocalTime(), rs.getBoolean("isValid"));
                //**************************teste affichage***************************
                System.out.println("affichage des emploi ");
                System.out.print(rs.getInt("idEmploi") + "\t");
                System.out.print(rs.getString("jour") + "\t");
                System.out.println();
            }
            return e;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Emploi Rechercher_Emploi(int IdEmploi) {

        Emploi e = new Emploi();
        if (IdEmploi != 0) {
            try {
                if (con == null) {
                    con = new DBConnection().Connect();
                }

                String query = "SELECT * FROM emploi where idEmploi = '" + IdEmploi + "'";

                st = con.createStatement();
                rs = st.executeQuery(query);
                // une boucle while pour recupérer tous les lignes    
                while (rs.next()) {
                e = new Emploi(rs.getInt("idEmploi"), rs.getInt("idGroupe"), rs.getString("jour"), rs.getTime("heure").toLocalTime(), rs.getBoolean("isValid"));
                }
                return e;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean Modifier_Emploi(Emploi e) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "UPDATE emploi SET   idGroupe = ? , jour= ? , heure= ? , isValid= ?  where idEmploi = ? ";
            // String q="(idEmploi, idGroupe, jour, heure, isValid)";

            System.out.println("query " + query);
            ps = con.prepareStatement(query);
            ps.setInt(1, e.getIdGroupe());
            ps.setString(2, e.getJour());
            ps.setTime(3, Time.valueOf(e.getHeure()));
            ps.setBoolean(4, e.getIsValid());
            ps.setInt(5, e.getIdEmploi());
            ps.executeUpdate();
            System.out.println("Emploi bien modifier ");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Emploi pas modifier");
            return false;

        }
    }

    public boolean Supprimer_Emploi(int IdEmploi) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "DELETE FROM emploi where idEmploi = " + IdEmploi + "";
            System.out.println("query " + query);
            st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Emploi bien supprimer");
            return true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Emploi pas supprimer");
            return false;

        }
    }

    public void Archiver_Emploi() {
    }
}
