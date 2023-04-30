/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.HizbModel;
import Models.SurahModel;
import Utils.Surah;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.CheckComboBox;

/**
 *
 * @author pc
 */
public class AjouterMemoController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Label lbl_nom;

    @FXML
    private JFXRadioButton radio_hifd, radio_tatbit;

    @FXML
    private JFXComboBox<String> combo_hizb, combo_surah;

    @FXML
    private CheckComboBox<String> checkCombo_huitieme;
    @FXML
    private JFXTextArea textArea_remarques;

    @FXML
    private JFXButton btn_plusMemo, btn_clear, btn_save;
    /**
     * ****************************************************
     */
    SurahModel surahModel;
    HizbModel hizbModel;
    /**
     * ****************************************************
     */

    private ObservableList<Surah> surahList;
    private ObservableList<String> surahNList;
    private ObservableList<String> hizbList;
    private ObservableList<String> huitiemeList;

    /**
     * ****************************************************
     */
    static int index =50; 
    /**
     * ****************************************************
     */
    public AjouterMemoController() {
        surahModel = new SurahModel();
        hizbModel = new HizbModel();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        surahList = surahModel.Afficher_Liste_Surah();

        surahNList = surahModel.Afficher_Liste_NomSurah();

        hizbList = FXCollections.observableArrayList();
        for (int i = 1; i <= 60; i++) {
            hizbList.add("الحزب " + i);
        }
        huitiemeList = FXCollections.observableArrayList("الثمن", "الربع", "ثلاثة اثمان", "النصف", "خمسة أثمان", "ثلاثة ارباع", "سبعة أثمان", "حزب");

        combo_surah.getItems().addAll(surahNList);
        combo_hizb.getItems().addAll(hizbList);
        checkCombo_huitieme.getItems().addAll(huitiemeList);
    }

    @FXML
    void AddNewMemo(ActionEvent event) {
      /*  JFXComboBox<String> combo_surah2 = new JFXComboBox<>(surahNList), combo_hizb2 = new JFXComboBox<>(hizbList);
        CheckComboBox<String> checkCombo_huitieme2 = new CheckComboBox<>(huitiemeList);
        JFXRadioButton radio_tatbit2 = new JFXRadioButton("تثبيت"), radio_hifd2 = new JFXRadioButton("حفظ");

        VBox vBox = new VBox(20, combo_surah2, combo_hizb2, checkCombo_huitieme2, radio_hifd2, radio_tatbit2);
        //  root.setTopAnchor(vBox, Double.NaN);
        root.getChildren().add(index,vBox);*/
    }

    @FXML
    void Clear(ActionEvent event) {

    }

    @FXML
    void Save(ActionEvent event) {

    }
}
