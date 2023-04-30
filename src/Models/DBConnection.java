package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DBConnection {

    public DBConnection() {
    }

    public Connection Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver oki");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coran", "root", "");

            return con;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "connection no established");

            return null;
        }

    }

}
