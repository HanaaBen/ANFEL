/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utils.Memorisation;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Hanaa
 */
public class MemorisationModel {

    private Connection con;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    public boolean ajouter_Memorisation(Memorisation m) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "INSERT INTO memorisation ( idEtudiant, idSurah, idHizbHuitieme, remarque, souratMemoriser) VALUES( ?,?,?,?,? )";
            System.out.println("query " + query);
            ps = con.prepareStatement(query);
            ps.setInt(1, m.getIdEtudiant());
            ps.setInt(2, m.getIdSurah());
            ps.setInt(3, m.getIdHizbHuitieme());
            ps.setString(4, m.getRemarque());
            ps.setBoolean(5, false);
            ps.executeUpdate();

            System.out.println("Memorisation bien ajoutee");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Memorisation pas ajoutee");
            return false;

        }
    }

    public ObservableList<Memorisation> Afficher_Liste_Memorisations() {
        ObservableList<Memorisation> listMemorisations = FXCollections.observableArrayList();
        Memorisation e;
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM memorisation ORDER BY idMemorisation");//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            while (rs.next()) {
                e = new Memorisation(rs.getInt("idMemorisation"), rs.getInt("idEtudiant"), rs.getInt("idSurah"), rs.getInt("idHizbHuitieme"), rs.getString("remarque"), rs.getString("remarqueM"), rs.getBoolean("souratMemoriser"), rs.getDate("date").toLocalDate(), rs.getDate("dateM").toLocalDate());
                listMemorisations.add(e);
                /**
                 * *****************teste affichage*******************
                 */
                System.out.println("affichage des Memorisations ");
                System.out.print(rs.getInt("idMemorisation") + "\t");
                System.out.print(rs.getInt("idEtudiant") + "\t");
                System.out.println();
            }
            return listMemorisations;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ObservableList<Memorisation> Afficher_Liste_Memorisations(int idEtudiant) {
        ObservableList<Memorisation> listMemorisations = FXCollections.observableArrayList();
        Memorisation e;
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM memorisation WHERE idEtudiant = " + idEtudiant);//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            while (rs.next()) {
                e = new Memorisation(rs.getInt("idMemorisation"), rs.getInt("idEtudiant"), rs.getInt("idSurah"), rs.getInt("idHizbHuitieme"), rs.getString("remarque"), rs.getString("remarqueM"), rs.getBoolean("souratMemoriser"), rs.getDate("date").toLocalDate(), rs.getDate("dateM").toLocalDate());
                listMemorisations.add(e);
                /**
                 * *****************teste affichage*******************
                 */
                System.out.println("affichage des Memorisations ");
                System.out.print(rs.getInt("idMemorisation") + "\t");
                System.out.println();
            }
            return listMemorisations;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /* public static void main(String args[]){
        Afficher_Liste_Memorisations(3);
    }*/
    public Memorisation getLastMemorisations() {
        Memorisation e = new Memorisation();
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM memorisation ORDER BY idMemorisation DESC LIMIT 0,1");//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            if (rs.next()) {
                e = new Memorisation(rs.getInt("idMemorisation"), rs.getInt("idEtudiant"), rs.getInt("idSurah"), rs.getInt("idHizbHuitieme"), rs.getString("remarque"), rs.getString("remarqueM"), rs.getBoolean("souratMemoriser"), rs.getDate("date").toLocalDate(), rs.getDate("dateM").toLocalDate());
                //**************************teste affichage***************************
                System.out.println("affichage des Memorisations ");
                System.out.print(rs.getInt("idMemorisation") + "\t");
                System.out.print(rs.getString("nom") + "\t");
                System.out.println();
            }
            return e;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Memorisation Rechercher_Memorisation(int IdMemorisation) {

        Memorisation e = new Memorisation();
        if (IdMemorisation != 0) {
            try {
                if (con == null) {
                    con = new DBConnection().Connect();
                }

                String query = "SELECT * FROM memorisation where idMemorisation = '" + IdMemorisation + "'";

                st = con.createStatement();
                rs = st.executeQuery(query);
                // une boucle while pour recupérer tous les lignes    
                while (rs.next()) {
                    e = new Memorisation(rs.getInt("idMemorisation"), rs.getInt("idEtudiant"), rs.getInt("idSurah"), rs.getInt("idHizbHuitieme"), rs.getString("remarque"), rs.getString("remarqueM"), rs.getBoolean("souratMemoriser"), rs.getDate("date").toLocalDate(), rs.getDate("dateM").toLocalDate());
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

    public boolean Modifier_Memorisation(Memorisation e) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "UPDATE memorisation SET   idEtudiant = ? , idSurah= ? , idHizbHuitieme= ? , remarque= ? , souratMemoriser= ? , remarqueM = ? , dateM = ?  where idMemorisation = ? ";
            // String q="(idMemorisation, idEtudiant, idSurah, idHizbHuitieme, remarque, date, souratMemoriser, remarqueM, dateM)";

            System.out.println("query " + query);
            ps = con.prepareStatement(query);
            ps.setInt(1, e.getIdEtudiant());
            ps.setInt(2, e.getIdSurah());
            ps.setInt(3, e.getIdHizbHuitieme());
            ps.setString(4, e.getRemarque());
            ps.setBoolean(5, e.getMemoriser());
            ps.setString(6, e.getRemarqueM());
            ps.setDate(7, Date.valueOf(e.getDateM()));
            ps.setInt(8, e.getIdMemorisation());
            ps.executeUpdate();
            System.out.println("Memorisation bien modifier ");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Memorisation pas modifier");
            return false;

        }
    }

    public boolean Supprimer_Memorisation(int IdMemorisation) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "DELETE FROM memorisation where idMemorisation = " + IdMemorisation + "";
            System.out.println("query " + query);
            st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Memorisation bien supprimer");
            return true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Memorisation pas supprimer");
            return false;

        }
    }

    public void Archiver_Memorisation() {
    }
}
