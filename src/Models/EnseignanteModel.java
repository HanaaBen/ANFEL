/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utils.Enseignante;
import Utils.Etudiant;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Hanaa
 */
public class EnseignanteModel {

    private Connection con;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    public Enseignante getConnection(String pseudo, String motdepass) {
        Enseignante enseignante;

        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM enseignante where pseudo = '" + pseudo + "' AND motdepass = '" + motdepass + "'");//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            if (rs.next()) {
                enseignante = new Enseignante(rs.getInt("id_enseignante"), rs.getString("nom"), rs.getString("pseudo"), rs.getString("motdepass"));
                //**************************teste affichage***************************
                /*System.out.println("affichage des etudiants ");
                System.out.print(rs.getInt("id_enseignante") + "\t");
                System.out.print(rs.getString("nom") + "\t");
                System.out.print(rs.getString("pseudo") + "\t");
                System.out.print(rs.getString("motdepass") + "\t");
                System.out.print(enseignante.isEmpty());
                System.out.println();*/
                return enseignante;

            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean ajouterEnseignante(Enseignante e) {

        try {

            if (con == null) {
                con = new DBConnection().Connect();
            }
/////////////////////////////////////hachage////////////////////////////
            // MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            //byte[] encodedhash = messageDigest.digest(e.getMotdepass().getBytes(StandardCharsets.UTF_8));
            String password = e.getMotdepass();

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());

            byte byteData[] = messageDigest.digest();

            //convertir le tableau de bits en une format hexadécimal - méthode 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
/////////////////////////////////////hachage////////////////////////////

            String query = "INSERT INTO enseignante nom, pseudo, motdepass VALUES( ?,?,?)";
            System.out.println("query " + query);
            ps = con.prepareStatement(query);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getPseudo());
            ps.setString(3, e.getMotdepass());

            int p = ps.executeUpdate();

            if (p != 0) {
                System.out.println("enseignante bien ajoutee");
                return true;
            } else {
                System.out.println("enseignante pas ajoutee");
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("enseignante pas ajoutee");
            return false;

        }
    }

    public boolean ModifierEnseignante(Enseignante e) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }

            String query = "UPDATE enseignante SET   nom = ? , pseudo = ? , motdepass = ? where id_enseignante = ? ";
            System.out.println("query " + query);
            ps = con.prepareStatement(query);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getPseudo());
            ps.setString(3, e.getMotdepass());
            ps.setInt(4, e.getIdEnseignante());
            int p = ps.executeUpdate();
            if (p != 0) {
                System.out.println("enseignante bien modifier ");
                return true;
            } else {
                System.out.println("enseignante pas modifier");
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("enseignante pas modifier");
            return false;

        }
    }

    public boolean SupprimerEnseignante(int id_enseignante) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "DELETE FROM enseignante where id_enseignante = " + id_enseignante + "";
            System.out.println("query " + query);
            st = con.createStatement();
            int p = st.executeUpdate(query);
            if (p != 0) {
                System.out.println("enseignante bien supprimer ");
                return true;
            } else {
                System.out.println("enseignante pas supprimer");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("enseignante pas supprimer");
            return false;

        }
    }

    public ObservableList<Enseignante> Afficher_Liste_enseignants() {
        ObservableList<Enseignante> listEnseignantes = FXCollections.observableArrayList();
        Enseignante e;
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM enseignante ORDER BY id_enseignante");
            while (rs.next()) {
                e = new Enseignante(rs.getInt("id_enseignante"), rs.getString("nom"), rs.getString("pseudo"), rs.getString("motdepass"));
                listEnseignantes.add(e);
                /**
                 * *****************teste affichage*******************
                 */
                System.out.println("affichage des enseignantes ");
                System.out.print(rs.getInt("id_enseignante") + "\t");
                System.out.print(rs.getString("nom") + "\t");
                System.out.println();
            }
            return listEnseignantes;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
