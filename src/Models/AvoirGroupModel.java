
package Models;

import Utils.AvoirGroupe;
import java.sql.Connection;
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
public class AvoirGroupModel {

    private Connection con;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    public boolean ajouter_AvoirGroupe(AvoirGroupe g) {

        try {

            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "INSERT INTO avoirgroupe(idEtudiant, idGroupe) VALUES (?,?)";
            System.out.println("query " + query);
            ps = con.prepareStatement(query);
            ps.setInt(1, g.getIdEtudiant());
            ps.setInt(2, g.getIdGroupe());

            int b = ps.executeUpdate();

            if (b != 0) {
                System.out.println("AvoirGroupe bien ajoutee");
                return true;
            } else {
                System.out.println("AvoirGroupe pas ajoutee");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("AvoirGroupe pas ajoutee");
            return false;

        }
    }

    public boolean Modifier_AvoirGroupe(AvoirGroupe g, AvoirGroupe newG) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "UPDATE avoirgroupe SET idEtudiant = ? ,idGroupe = ?  WHERE (idEtudiant = ? AND  idGroupe = ? )";
            System.out.println("query " + query);
            ps = con.prepareStatement(query);
            ps.setInt(1, newG.getIdEtudiant());
            ps.setInt(2, newG.getIdGroupe());
            ps.setInt(3, g.getIdEtudiant());
            ps.setInt(4, g.getIdGroupe());

            int b = ps.executeUpdate();

            if (b != 0) {
                System.out.println("AvoirGroupe bien modifier");
                return true;
            } else {
                System.out.println("AvoirGroupe pas modifier");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("AvoirGroupe pas modifier");
            return false;
        }

    }

    public boolean Supprimer_AvoirGroupe(int idEtudiant, int idGroupe) {
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            String query = "DELETE FROM avoirgroupe where ( idEtudiant = " + idEtudiant + " AND idGroupe = " + idGroupe + ")";
            System.out.println("query " + query);
            st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("AvoirGroupe bien supprimer");
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("AvoirGroupe pas supprimer");
            return false;
        }
    }

    public ObservableList<AvoirGroupe> Afficher_Liste_AvoirGroupes() {
        ObservableList<AvoirGroupe> listAvoirGroupes = FXCollections.observableArrayList();
        AvoirGroupe f;
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }
            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM avoirgroupe");//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            System.out.println("affichage des AvoirGroupes ");

            while (rs.next()) {

                f = new AvoirGroupe(rs.getInt("idEtudiant"), rs.getInt("idGroupe"));
                listAvoirGroupes.add(f);
                /**
                 * *****************teste affichage*******************
                 */

                System.out.print(rs.getInt("idEtudiant") + "\t");
                System.out.print(rs.getInt("idGroupe") + "\t");

                System.out.println();
            }
            return listAvoirGroupes;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public ObservableList<AvoirGroupe> getEtudiants(int idGroupe) {

        ObservableList<AvoirGroupe> g = FXCollections.observableArrayList();
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }

            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM avoirgroupe WHERE  idGroupe = " + idGroupe);//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            System.out.println("affichage des AvoirGroupes ");

            while (rs.next()) {

                g.add(new AvoirGroupe(rs.getInt("idEtudiant"), rs.getInt("idGroupe")));
                /**
                 * *****************teste affichage*******************
                 */

                System.out.println(rs.getInt("AvoirGroup GetEtudiant") + "\t");
                System.out.print(rs.getInt("idEtudiant") + "\t");
                System.out.print(rs.getInt("idGroupe") + "\t");

                System.out.println();

            }
            return g;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public ObservableList<AvoirGroupe> getGroupes(int idEtudiant) {

        ObservableList<AvoirGroupe> g = FXCollections.observableArrayList();
        try {
            if (con == null) {
                con = new DBConnection().Connect();
            }

            st = con.createStatement();//creer statment grace a linstruction creatstatment qui fourni par la variable cnx
            rs = st.executeQuery("SELECT * FROM avoirgroupe WHERE  idEtudiant = " + idEtudiant);//executer une requette SQL grace a executeQuery et le resultat sera stockes dans rst
            // une boucle while pour recupérer tous les lignes    
            System.out.println("affichage des AvoirGroupes GetEtudiant");

            while (rs.next()) {

                g.add(new AvoirGroupe(rs.getInt("idEtudiant"), rs.getInt("idGroupe")));
                /**
                 * *****************teste affichage*******************
                 */

             
                System.out.print(rs.getInt("idEtudiant") + "\t");
                System.out.print(rs.getInt("idGroupe") + "\t");

                System.out.println();

            }
            return g;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

}
