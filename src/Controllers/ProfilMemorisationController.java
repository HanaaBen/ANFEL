/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.AvoirGroupModel;
import Models.EtudiantModel;
import Models.GroupModel;
import Models.HizbModel;
import Models.MemorisationModel;
import Models.SurahModel;
import Utils.AvoirGroupe;
import Utils.Etudiant;
import Utils.Groupe;
import Utils.Hizb;
import Utils.Memorisation;
import Utils.Surah;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ProfilMemorisationController implements Initializable {

    // @FXML
    //  private TextField txt_filteredData;lbl_etudiant;
    @FXML
    private Label lbl_etudiant;
    @FXML
    private JFXTextField txt_nom, txt_age, txt_niveau, txt_ancienMemo, txt_niveauDispoRecitation, txt_remarque, txt_remarqueM;

    @FXML
    private JFXComboBox combo_groupe, combo_hifd, combo_coul, combo_hizb, combo_surah, combo_huitieme;

    @FXML
    private JFXCheckBox check_autreEnsei, check_memoS, check_memoH;

    @FXML
    private JFXButton btn_delete, btn_update, btn_addS, btn_addH;

    @FXML
    private HBox hb_info;
    @FXML
    private VBox vb_table;
//private Panel
    /**
     * ***********************************************
     */

    @FXML
    private TableView<Memorisation> table_Memorisations;
    @FXML
    private TableColumn<Memorisation, Memorisation> Numero;
    @FXML
    private TableColumn<Memorisation, String> AMemorise;
    @FXML
    private TableColumn<Memorisation, String> Surah;
    @FXML
    private TableColumn<Memorisation, String> Huitieme;
    @FXML
    private TableColumn<Memorisation, String> Remarque;
    @FXML
    private TableColumn<Memorisation, String> Memorise;
    @FXML
    private TableColumn<Memorisation, String> RemarqueM;
    @FXML
    private TableColumn<Memorisation, String> Operations;
    /**
     * ****************************************************
     */
    SurahModel surahModel;
    HizbModel hizbModel;
    GroupModel groupModel;
    AvoirGroupModel avoirGroupModel;
    EtudiantModel etudiantModel;
    MemorisationModel memorisationModel;

    /**
     * ********************Observable*******************
     */
    private ObservableList<Memorisation> listMemorisations;
    // private ObservableList<Surah> surahList;
    private ObservableList<String> surahNList;
    private ObservableList<String> hizbList;
    private ObservableList<String> huitiemeList;
    private ObservableList<String> dureeAv;
    private ObservableList<AvoirGroupe> avoirGList;
    private ObservableList<String> groupe;

    /**
     * *****************************************
     */
    private Etudiant etudiant;

    /**
     * ************************************************
     */
    public ProfilMemorisationController() {
        hizbModel = new HizbModel();
        surahModel = new SurahModel();
        groupModel = new GroupModel();
        etudiantModel = new EtudiantModel();
        avoirGroupModel = new AvoirGroupModel();
        memorisationModel = new MemorisationModel();

        /**
         * *******************************************
         */
        etudiant = etudiantModel.getEtudiant(3);

        /**
         * *******************************************
         */
        //surahList = surahModel.Afficher_Liste_Surah();
        surahNList = surahModel.Afficher_Liste_NomSurah();
        hizbList = FXCollections.observableArrayList();

        for (int i = 1; i <= 60; i++) {
            hizbList.add("الحزب " + i);
        }

        huitiemeList = FXCollections.observableArrayList("الثمن", "الربع", "ثلاثة اثمان", "النصف", "خمسة أثمان", "ثلاثة ارباع", "سبعة أثمان", "حزب", "جزء");
        dureeAv = FXCollections.observableArrayList("يوم", "يومين", "ثلاثة ايام", "اسبوع", " اسبوعين", "ثلاثة اسابيع", "شهر");
        avoirGList = FXCollections.observableArrayList();
        groupe = FXCollections.observableArrayList();
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        combo_surah.getItems().addAll(surahNList);
        combo_hizb.getItems().addAll(hizbList);
        combo_huitieme.getItems().addAll(huitiemeList);
        combo_hifd.getItems().addAll(huitiemeList);
        combo_coul.getItems().addAll(dureeAv);
        lbl_etudiant.setOnMouseClicked((MouseEvent event) -> {
            if (hb_info.isVisible() && !hb_info.isDisable()) {
                hb_info.setVisible(false);
                hb_info.setDisable(true);
                vb_table.setTranslateY(-270);

            } else {
                hb_info.setVisible(true);
                hb_info.setDisable(false);
                vb_table.setTranslateY(0);
                //  vb_table.setMargin(hb_info, new Insets(370, 0, 10, 0));
            }
        });
        IniInfoEtudiante();
        iniTable();
        AddCellEdit();
        AddCellNumberRows();
        iniListTable();
        table_Memorisations.getItems().addAll(listMemorisations);

    }

    private void iniTable() {
        Numero.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        // AMemorise.setCellValueFactory(new PropertyValueFactory<>("AMemorise"));
        Surah.setCellValueFactory(new PropertyValueFactory<>("Surah"));
        Huitieme.setCellValueFactory(new PropertyValueFactory<>("Huitieme"));
        Remarque.setCellValueFactory(new PropertyValueFactory<>("Remarque"));
        Memorise.setCellValueFactory(new PropertyValueFactory<>("Memorise"));
        RemarqueM.setCellValueFactory(new PropertyValueFactory<>("RemarqueM"));
        //  AMemorise.getColumns().addAll(Surah, Huitieme);
    }

    private void AddCellEdit() {
        //add cell of button edit 
        Callback<TableColumn<Memorisation, String>, TableCell<Memorisation, String>> cellFoctory = (TableColumn<Memorisation, String> param) -> {
            // make cell containing buttons
            final TableCell<Memorisation, String> cell = new TableCell<Memorisation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {

//*****************************************update******************************************************
                        //OctIconView AddMemoIcon = new OctIconView(OctIcon.REPO_PULL);PENCIL_SQUAREBOOKMARK_ALT
                        FontAwesomeIconView AddMemoIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        AddMemoIcon.setOnMouseClicked((event) -> {

                            getUpdate();
                        });
                        AddMemoIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );

                        //****************************************DELETE*******************************************************
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        deleteIcon.setOnMouseClicked((event) -> {
                            getDelete();
                        });
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        //*****************************************************************************************************
                        HBox managebtn = new HBox(AddMemoIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(AddMemoIcon, new Insets(2, 3, 0, 3));
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        setGraphic(managebtn);
                        setText(null);
                    }
                }

            };
            return cell;
        };
        //if (cellFoctory != null) {
        Operations.setCellFactory(cellFoctory);
        //}
    }

    private void AddCellNumberRows() {
        Numero.setMinWidth(20);
        Numero.setCellValueFactory((TableColumn.CellDataFeatures<Memorisation, Memorisation> p) -> new ReadOnlyObjectWrapper(p.getValue()));

        Numero.setCellFactory((TableColumn<Memorisation, Memorisation> param) -> new TableCell<Memorisation, Memorisation>() {
            @Override
            protected void updateItem(Memorisation item, boolean empty) {
                super.updateItem(item, empty);

                if (this.getTableRow() != null && item != null) {
                    setText(this.getTableRow().getIndex() + 1 + "");
                } else {
                    setText("");
                }
            }
        });
        Numero.setSortable(false);
    }

    private void IniInfoEtudiante() {
        lbl_etudiant.setText(lbl_etudiant.getText() + "  " + etudiant.getNom());
        txt_nom.setText(etudiant.getNom());
        txt_age.setText(String.valueOf(etudiant.getAge()));
        txt_niveau.setText(etudiant.getNiveau());
        txt_niveauDispoRecitation.setText(etudiant.getDispositionRecitation());
        txt_ancienMemo.setText(etudiant.getAncienMemorisation());
        avoirGList = avoirGroupModel.getGroupes(3);
        System.out.println(avoirGList.isEmpty());
        if (!avoirGList.isEmpty()) {
            for (AvoirGroupe avoirGroupe : avoirGList) {
                Groupe g = groupModel.getGroupe(avoirGroupe.getIdGroupe());
                groupe.add(g.getGroupe());
            }
        }
        combo_groupe.getItems().setAll(groupe);
        combo_groupe.getSelectionModel().select(0);

        check_autreEnsei.setSelected(etudiant.getAutreEnseignant());
        combo_hifd.getSelectionModel().select(etudiant.getUniteAv());
        combo_coul.getSelectionModel().select(etudiant.getDureeAv());

    }

    private void iniListTable() {
        listMemorisations = memorisationModel.Afficher_Liste_Memorisations(etudiant.getIdEtudiant());
        for (Memorisation memo : listMemorisations) {
            if (memo.getIdSurah() != 0) {
                memo.setSurah(surahNList.get(memo.getIdSurah() - 1));
            } else if (memo.getIdHizbHuitieme() != 0) {
                Hizb hzb = hizbModel.getHizbHuitieme(memo.getIdHizbHuitieme());
                memo.setHuitieme("الحزب " + hzb.getHizb() + "الثمن " + hzb.getHuitieme());
            }
            JFXCheckBox jfxcb = new JFXCheckBox();
            jfxcb.setSelected(memo.getMemoriser());
            memo.setMemorise(jfxcb);
        }

    }

    @FXML
    private void Update(ActionEvent event) {

    }

    @FXML
    private void Delete(ActionEvent event) {

    }

    private void getUpdate() {
    }

    private void getDelete() {
    }

    protected void AddLine(Memorisation m) {
        JFXCheckBox ch = new JFXCheckBox();
        ch.setSelected(m.getMemoriser());
        m.setMemorise(ch);
        // add new Etudiant 
        listMemorisations.add(m);
        table_Memorisations.refresh();//setItems(listEtudiants);
    }

//soit ajib tt  la liste soit recheerche par id w njib ghir li tbadel
    protected void updateLine(Memorisation me) {
        // listEtudiants = table_Etudiants.getItems();
        for (Memorisation m : listMemorisations) {
            if (m.getIdMemorisation().equals(me.getIdMemorisation())) {
                m.setDate(me.getDate());
                m.setHuitieme(me.getHuitieme());
                m.setSurah(me.getSurah());
                m.setIdEtudiant(me.getIdEtudiant());
                m.setIdHizbHuitieme(me.getIdHizbHuitieme());
                m.setIdMemorisation(me.getIdMemorisation());
                m.setIdSurah(me.getIdSurah());
                m.setMemorise(me.getMemorise());
                m.setMemoriser(me.getMemoriser());
                m.setRemarque(me.getRemarque());
                m.setRemarqueM(me.getRemarqueM());
            }
        }
        //  listEtudiants.set(index, etudiant);
        //  listEtudiants = MEtudiant.Afficher_Liste_etudiants();
        table_Memorisations.setItems(listMemorisations);
        table_Memorisations.refresh();

    }
}
