/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.AvoirGroupModel;
import Models.EtudiantModel;
import Models.GroupModel;
import Models.View;
import Utils.AvoirGroupe;
import Utils.Etudiant;
import Utils.Groupe;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AjouterEtudiantController implements Initializable {

    @FXML
    private JFXTextField txt_id, txt_nom, txt_age, txt_niveau, txt_ancienMemorisation, txt_niveauDisposition;

    @FXML
    private JFXCheckBox check_autreEns;
  
    @FXML
    private JFXComboBox<String> combo_group;

    @FXML
    private JFXButton btn_vider, btn_save;
    EtudiantModel etudiantModel;
    GroupModel Mgroup;
    Etudiant e;
    /**
     * ********************
     */
    private ObservableList<Groupe> groupList;
    private ObservableList<String> groupListS;
    /**
     * ************************************
     */
    private String operation;

    public AjouterEtudiantController() {

        operation = "";
        etudiantModel = new EtudiantModel();
        Mgroup = new GroupModel();
        /**
         * ******************************
         */
        groupList = FXCollections.observableArrayList();
        groupListS = FXCollections.observableArrayList();

    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniGroup();

    }

    private void iniGroup() {
        groupList = Mgroup.Afficher_Liste_Groupes();
        for (Groupe g : groupList) {
            groupListS.add(g.getGroupe());
        }
        combo_group.setItems(groupListS);
    }

    private void SwitcheTo() {
        FXMLLoader loader = ViewSwitcher.getLoader(View.Table);
        TableController tableController = loader.getController();
        tableController.AddLine(e);
    }

    @FXML
    private void Save() {

        if (combo_group.getValue().isEmpty()) {
            return;
        }
        if (txt_nom.getText().isEmpty()) {//matches("[a-zA-Z_]{InARABIC}")) {// "\p{InArabic}+"
            return;
        }
        if (txt_age.getText().isEmpty()) {
            return;
        }
        if (txt_niveau.getText().isEmpty()) {
            return;
        }
        if (txt_ancienMemorisation.getText().isEmpty()) {
            return;
        }
        if (txt_niveauDisposition.getText().isEmpty()) {
            return;
        }
       
        e = new Etudiant(txt_nom.getText(), combo_group.getValue(), Integer.parseInt(txt_age.getText()), txt_niveau.getText(), txt_ancienMemorisation.getText(), txt_niveauDisposition.getText(), check_autreEns.isSelected());

        boolean b = etudiantModel.ajouter_etudiant(e);
        if (b) {

            e = etudiantModel.getLastEtudiants();
            e.setGroupe(combo_group.getValue());
            int id = combo_group.getSelectionModel().selectedIndexProperty().get();
            AvoirGroupModel avoirGroupModel = new AvoirGroupModel();
            avoirGroupModel.ajouter_AvoirGroupe(new AvoirGroupe(e.getIdEtudiant(), groupList.get(id).getIdGroupe()));
            JOptionPane.showMessageDialog(null, "تم التسجيل");

         /*   int resultat;
            resultat = JOptionPane.showConfirmDialog(null, "Vous devez remplir tous les champs !", "titre", JOptionPane.YES_NO_OPTION);
            
            if (resultat == JOptionPane.YES_OPTION) {
                System.out.println("yes option :"+resultat);
            }*/
            if (operation.equals("multi")) {
                Clear();
            } else {
                SwitcheTo();
                Exit();
            }

        } else {
            JOptionPane.showMessageDialog(null, "خطأ في التسجيل");
        }
    }

    @FXML
    private void Clear() {

        txt_id.clear();
        txt_nom.clear();
        txt_age.clear();
        txt_niveau.clear();
        txt_ancienMemorisation.clear();
        txt_niveauDisposition.clear();
        check_autreEns.setSelected(false);
    }

    private void Exit() {
        //Platform.exit();
        Stage stage = (Stage) txt_nom.getScene().getWindow();
        stage.close();
    }

}
