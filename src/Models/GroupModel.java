/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utils.Groupe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author pc
 */
public class GroupModel {

    private Connection con;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    public boolean ajouter_Groupe(Groupe g) {

        try {

            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "INSERT INTO groupe ( idEnseignant, groupe, lien, description, isActive) VALUES( " + g.getIdEnseignant() + ",'" + g.getGroupe() + "','" + g.getLien() + "','" + g.getDescription() + "'," + g.getIsActive() + ")";
            System.out.println("query " + query);

            st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Groupe bien ajoutee");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Groupe pas ajoutee");
            return false;

        }
    }

    public ObservableList<Groupe> Afficher_Liste_Groupes() {
        ObservableList<Groupe> listGroupes = FXCollections.observableArrayList();
        Groupe g;
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM groupe");//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            System.out.println("affichage des Groupes ");

            while (rs.next()) {

                g = new Groupe(rs.getInt("idGroupe"), rs.getInt("idEnseignant"), rs.getString("groupe"), rs.getString("lien"), rs.getString("description"), rs.getBoolean("isActive"), rs.getDate("dateCreation").toLocalDate());
                listGroupes.add(g);
                /**
                 * *****************teste affichage*******************
                 */

                System.out.print(rs.getInt("idGroupe") + "\t");
                System.out.print(rs.getString("groupe") + "\t");

                System.out.println();
            }
            return listGroupes;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public boolean Modifier_Groupe(Groupe g) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "UPDATE groupe SET idEnseignant = ?, groupe = ? ,lien = ? ,description= ?, isActive=? WHERE idGroupe = ? ";
            System.out.println("query " + query);

            ps = con.prepareStatement(query);
            ps.setInt(1, g.getIdEnseignant());
            ps.setString(2, g.getGroupe());
            ps.setString(3, g.getLien());
            ps.setString(4, g.getDescription());
            ps.setBoolean(5, g.getIsActive());
            ps.setInt(6, g.getIdGroupe());
            ps.executeUpdate();
            System.out.println("Groupe bien modifier");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Groupe pas modifier");
            return false;
        }

    }

    public boolean Supprimer_Groupe(int idGroupe) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "DELETE FROM groupe where idGroupe = " + idGroupe + "";
            System.out.println("query " + query);
            st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Groupe bien supprimer");
            return true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Groupe pas supprimer");
            return false;
        }
    }

    public Groupe getGroupe(int idGroupe) {
        Groupe g;
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM groupe WHERE idGroupe = " + idGroupe + "");//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            System.out.println("affichage des Groupes ");

            if (rs.next()) {

                g = new Groupe(rs.getInt("idGroupe"), rs.getInt("idEnseignant"), rs.getString("groupe"), rs.getString("lien"), rs.getString("description"), rs.getBoolean("isActive"), rs.getDate("dateCreation").toLocalDate());
                /**
                 * *****************teste affichage*******************
                 */

                System.out.println("GroupeM getGroupe");
                System.out.print(rs.getInt("idGroupe") + "\t");
                System.out.print(rs.getString("groupe") + "\t");

                System.out.println();
                return g;
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public boolean getGroupe(Groupe g) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM groupe WHERE  (groupe = '" + g.getGroupe() + "' AND lien = '" + g.getLien() + "' AND description= '" + g.getDescription() + "')");//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst

            if (rs.next()) {

                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }
    
}
