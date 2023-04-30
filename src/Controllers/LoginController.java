/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.EnseignanteModel;
import Models.View;
import Utils.Enseignante;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField txt_pseudo;

    @FXML
    private JFXPasswordField txt_pass;

 
    EnseignanteModel ensModel = new EnseignanteModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txt_pseudo.setText("zizi");
        txt_pass.setText("zouza");
    }

    @FXML
    private void Connect() {
        String pseu = txt_pseudo.getText(),
                pass = txt_pass.getText();

        if (!pseu.matches("[a-zA-Z0-9_]{4,}")) {
            return;
        }
        if (pass.isEmpty()) {
            return;
        }
        Enseignante ens = ensModel.getConnection(pseu, pass);
        if (ens != null) {

            SwitcheTo();
            Exit();
        } else {
            JOptionPane.showMessageDialog(null, "خطأ في اسم المستخدم او الرقم السري");
        }
    }

    @FXML
    private void Exit() {
        //Platform.exit();
        Stage stage = (Stage) txt_pseudo.getScene().getWindow();
        stage.close();
    }

    private void SwitcheTo() {

        ObservableList li = ViewSwitcher.SwitcheTo(View.Table);
        ((Stage) li.get(0)).setTitle("جدول الطالبات");

    }
}
