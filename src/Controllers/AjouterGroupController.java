/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.EnseignanteModel;
import Models.GroupModel;
import Utils.Enseignante;
import Utils.Groupe;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
public class AjouterGroupController implements Initializable {

    @FXML
    private JFXTextField txt_id, txt_groupe, txt_lien, txt_description;

    @FXML
    private JFXComboBox<String> combo_ens;

    /**
     * **************************************
     */
    EnseignanteModel ensModel;
    GroupModel Mgroup;
    /**
     * ********************
     */
    private ObservableList<Enseignante> ensList;
    private ObservableList<String> ensListS;

    /**
     * ************************************
     */
    Groupe g;

    public AjouterGroupController() {

        ensModel = new EnseignanteModel();
        Mgroup = new GroupModel();
        /**
         * ******************************
         */
        ensList = FXCollections.observableArrayList();
        ensListS = FXCollections.observableArrayList();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniEns();
    }

    private void iniEns() {
        ensList = ensModel.Afficher_Liste_enseignants();
        if(!ensList.isEmpty()){
            for (Enseignante e : ensList) {
            ensListS.add(e.getNom());
        }
        combo_ens.setItems(ensListS);
    }
    }

    @FXML
    private void Save() {

        if (combo_ens.getValue().isEmpty()) {
            return;
        }
        if (txt_groupe.getText().isEmpty()) {
            return;
        }
        if (txt_lien.getText().isEmpty()) {
            return;
        }
        if (txt_description.getText().isEmpty()) {
            return;
        }
        int id = combo_ens.getSelectionModel().selectedIndexProperty().get();
        g = new Groupe(ensList.get(id).getIdEnseignante(), txt_groupe.getText(), txt_lien.getText(), txt_description.getText());
        boolean b = Mgroup.getGroupe(g);
        if(!b){
         b = Mgroup.ajouter_Groupe(g);
        if (b) {

            JOptionPane.showMessageDialog(null, "تم التسجيل");
            Exit();
        } else {
            JOptionPane.showMessageDialog(null, "خطأ في التسجيل");
        }
        }else {
            JOptionPane.showMessageDialog(null, "حلقة موجودة");
        }
    }

    @FXML
    private void Clear() {
        txt_groupe.clear();
        txt_lien.clear();
        txt_description.clear();
        combo_ens.getSelectionModel().clearSelection();
    }

    private void Exit() {

        Stage stage = (Stage) txt_groupe.getScene().getWindow();
        stage.close();
    }

}
