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
import Utils.Etudiant;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Hanaa
 */
public class EtudiantModel {

    private Connection con;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    public boolean ajouter_etudiant(Etudiant e) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "INSERT INTO etudiant ( nom, age, niveau, ancienMemorisation, dispositionRecitation, autreEnseignant ) VALUES( ?,?,?,?,?,? )";
            System.out.println("query " + query);
            ps = con.prepareStatement(query);
            ps.setString(1, e.getNom());
            ps.setInt(2, e.getAge());
            ps.setString(3, e.getNiveau());
            ps.setString(4, e.getAncienMemorisation());
            ps.setString(5, e.getDispositionRecitation());
            ps.setBoolean(6, e.getAutreEnseignant());
            ps.executeUpdate();

            System.out.println("etudiant bien ajoutee");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("etudiant pas ajoutee");
            return false;

        }
    }

    public ObservableList<Etudiant> Afficher_Liste_etudiants() {
        ObservableList<Etudiant> listEtudiants = FXCollections.observableArrayList();
        Etudiant e;
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM etudiant ORDER BY idEtudiant");//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            while (rs.next()) {
                e = new Etudiant(rs.getInt("idEtudiant"), rs.getString("nom"), rs.getInt("age"), rs.getString("niveau"), rs.getString("ancienMemorisation"), rs.getString("dispositionRecitation"), rs.getString("uniteAv"), rs.getString("dureeAv"), rs.getBoolean("autreEnseignant"), rs.getDate("dateInscription").toLocalDate());
                listEtudiants.add(e);
                /**
                 * *****************teste affichage*******************
                 */
                System.out.println("affichage des etudiants ");
                System.out.print(rs.getInt("idEtudiant") + "\t");
                System.out.print(rs.getString("nom") + "\t");
                System.out.println();
            }
            return listEtudiants;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Etudiant getLastEtudiants() {
        Etudiant e = new Etudiant();
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM etudiant ORDER BY idEtudiant DESC LIMIT 0,1");//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            if (rs.next()) {
                e = new Etudiant(rs.getInt("idEtudiant"), rs.getString("nom"), rs.getInt("age"), rs.getString("niveau"), rs.getString("ancienMemorisation"), rs.getString("dispositionRecitation"), rs.getString("uniteAv"), rs.getString("dureeAv"), rs.getBoolean("autreEnseignant"), rs.getDate("dateInscription").toLocalDate());
                //**************************teste affichage***************************
                System.out.println("affichage des etudiants ");
                System.out.print(rs.getInt("idEtudiant") + "\t");
                System.out.print(rs.getString("nom") + "\t");
                System.out.println();
            }
            return e;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Etudiant getEtudiant(int IdEtudiant) {

        Etudiant e = new Etudiant();
        if (IdEtudiant != 0) {
            try {
                if (con == null) {
                    con = new DBConnection().Connect();
                }

                String query = "SELECT * FROM etudiant where idEtudiant = '" + IdEtudiant + "'";

                st = con.createStatement();
                rs = st.executeQuery(query);
                // une boucle while pour recupérer tous les lignes    
                if (rs.next()) {
                    e = new Etudiant(rs.getInt("idEtudiant"), rs.getString("nom"), rs.getInt("age"), rs.getString("niveau"), rs.getString("ancienMemorisation"), rs.getString("dispositionRecitation"), rs.getString("uniteAv"), rs.getString("dureeAv"), rs.getBoolean("autreEnseignant"), rs.getDate("dateInscription").toLocalDate());
                }
                System.out.println("getEtudiant");
                System.out.print(rs.getInt("idEtudiant")+"\t");
                System.out.print(rs.getString("nom")+"\t");
                return e;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean Modifier_Etudiant(Etudiant e) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "UPDATE etudiant SET   nom = ? , age= ? ,niveau= ? ,ancienMemorisation= ? ,dispositionRecitation= ? ,autreEnseignant= ? , uniteAv = ? , dureeAv = ?  where idEtudiant = ? ";

            System.out.println("query " + query);
            ps = con.prepareStatement(query);
            ps.setString(1, e.getNom());
            ps.setInt(2, e.getAge());
            ps.setString(3, e.getNiveau());
            ps.setString(4, e.getAncienMemorisation());
            ps.setString(5, e.getDispositionRecitation());
            ps.setBoolean(6, e.getAutreEnseignant());
            ps.setBoolean(7, e.getAutreEnseignant());
            ps.setBoolean(8, e.getAutreEnseignant());
            ps.setInt(9, e.getIdEtudiant());

            ps.executeUpdate();
            System.out.println("etudiant bien modifier ");
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("etudiant pas modifier");
            return false;

        }
    }

    public boolean Supprimer_Etudiant(int IdEtudiant) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "DELETE FROM etudiant where idEtudiant = " + IdEtudiant + "";
            System.out.println("query " + query);
            st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("etudiant bien supprimer");
            return true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("etudiant pas supprimer");
            return false;

        }
    }

    public void Archiver_Etudiant() {
    }
}
