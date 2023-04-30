package Controllers;

import Models.AvoirGroupModel;
import Models.EtudiantModel;
import Models.GroupModel;
import Models.View;
import Utils.AvoirGroupe;
import Utils.Etudiant;
import Utils.Groupe;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.octicons.OctIcon;
import de.jensd.fx.glyphs.octicons.OctIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * @author Hanaa
 */
public class TableController implements Initializable {

    @FXML
    private TextField txt_filteredData;
    /**
     * *********** TableView ******************************
     */
    @FXML
    private TableView<Etudiant> table_Etudiants;

    @FXML
    private TableColumn<Etudiant, Etudiant> Numero;

    @FXML
    private TableColumn<Etudiant, Integer> IdEtudiant;

    @FXML
    private TableColumn<Etudiant, String> Nom, Groupe, UniteAv, DureeAV, Operations;
    /**
     * *********** ObservableList ************************
     */
    private ObservableList<Etudiant> listEtudiants;
    private ObservableList<Groupe> listGroupes;
    private ObservableList<AvoirGroupe> listAGroupes;
    /**
     * *********** Utils *********************************
     */
    private Etudiant etudiant;
    /**
     * *********** Models ********************************
     */
    private EtudiantModel MEtudiant;
    private GroupModel MGroup;
    private AvoirGroupModel MAvoirG;

    /**
     * **********************************
     */
    private int index;

    /**
     * Initializes the controller class.
     *
     *
     */
    public TableController() {
        MEtudiant = new EtudiantModel();
        MGroup = new GroupModel();
        MAvoirG = new AvoirGroupModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prepareList();
        iniTable();
        AddCellEdit();
        AddCellNumberRows();
        table_Etudiants.setItems(listEtudiants);
        FiltereList();
    }

    private void iniTable() {
        Numero.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        IdEtudiant.setCellValueFactory(new PropertyValueFactory<>("IdEtudiant"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Groupe.setCellValueFactory(new PropertyValueFactory<>("Groupe"));
        UniteAv.setCellValueFactory(new PropertyValueFactory<>("UniteAv"));
        DureeAV.setCellValueFactory(new PropertyValueFactory<>("DureeAV"));
    }

    private void AddCellEdit() {
        //add cell of button edit 
        Callback<TableColumn<Etudiant, String>, TableCell<Etudiant, String>> cellFoctory = (TableColumn<Etudiant, String> param) -> {
            // make cell containing buttons
            final TableCell<Etudiant, String> cell = new TableCell<Etudiant, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {

//*****************************************Memorisation******************************************************
                        //OctIconView AddMemoIcon = new OctIconView(OctIcon.REPO_PULL);
                        FontAwesomeIconView AddMemoIcon = new FontAwesomeIconView(FontAwesomeIcon.MAP);

                        AddMemoIcon.setOnMouseClicked((event) -> {

                            getAjouterMemorisation();
                        });
                        AddMemoIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
//*****************************************Profil******************************************************
                        FontAwesomeIconView userIcon = new FontAwesomeIconView(FontAwesomeIcon.USER);
                        userIcon.setOnMouseClicked((event) -> {

                            getProfileEtudiant();
                        });
                        userIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        //****************************************DELETE*******************************************************
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        deleteIcon.setOnMouseClicked((event) -> {
                            Delete();
                        });
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        //*****************************************************************************************************
                        HBox managebtn = new HBox(userIcon, AddMemoIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(userIcon, new Insets(2, 3, 0, 2));
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

    private void prepareList() {
        listEtudiants = MEtudiant.Afficher_Liste_etudiants();
        for (Etudiant e : listEtudiants) {
            listAGroupes = MAvoirG.getGroupes(e.getIdEtudiant());
            //  ObservableList<String> g = FXCollections.observableArrayList();
            String gr = "";
            for (int i = 0; i < listAGroupes.size(); i++) {
                gr = MGroup.getGroupe(listAGroupes.get(i).getIdGroupe()).getGroupe();
                if (i != listAGroupes.size() - 1) {
                    gr = gr + " , ";
                }
            }
            e.setGroupe(gr);
        }

    }

    private void AddCellNumberRows() {
        Numero.setMinWidth(20);
        Numero.setCellValueFactory((TableColumn.CellDataFeatures<Etudiant, Etudiant> p) -> new ReadOnlyObjectWrapper(p.getValue()));

        Numero.setCellFactory((TableColumn<Etudiant, Etudiant> param) -> new TableCell<Etudiant, Etudiant>() {
            @Override
            protected void updateItem(Etudiant item, boolean empty) {
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

    private void FiltereList() {
        // 1. wrap ObservableList in a FilteredList (initially display all data).
        FilteredList<Etudiant> filteredData = new FilteredList<>(listEtudiants, b -> true);
        // 2. set the filter Predicate whethever the filter changes.
        txt_filteredData.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(etudian -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (etudian.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (etudian.getGroupe().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        // 3. wrap the filteredList in a SortedList
        SortedList<Etudiant> sortedData = new SortedList<>(filteredData);
        // 4. bind the sortedlist comparator to the tableView Comparator  
        // otherwise , sorting the tableView would have no effect.
        sortedData.comparatorProperty().bind(table_Etudiants.comparatorProperty());
        // 5. add Sorted (and filtered) data in the table
        table_Etudiants.setItems(sortedData);
    }

    // ObservableList 1st item stage  0 ,2nd item Scene 1 ,3rd item Parent 2 root , 4th item FXMLLoader 3 ;
    @FXML
    private void getAjouterEtudiant() {
        ObservableList li = ViewSwitcher.SwitcheTo(View.AjouEtudiant);
        ((Stage) li.get(0)).setTitle("اضافة طالبة");
    }

    @FXML
    private void getAjouterEtudiantM() {
        ObservableList li = ViewSwitcher.SwitcheTo(View.AjouEtudiant);
        ((Stage) li.get(0)).setTitle("اضافة عدة طالبات");
        FXMLLoader loader = (FXMLLoader) li.get(3);
        AjouterEtudiantController ajouterEtudiantController = loader.getController();
        ajouterEtudiantController.setOperation("multi");
    }

    // ObservableList 1st item stage  0 ,2nd item Scene 1 ,3rd item Parent 2 root , 4th item FXMLLoader 3 ;
    @FXML
    private void getAjouterGroup() {
        ObservableList li = ViewSwitcher.SwitcheTo(View.AjouGroup);
        ((Stage) li.get(0)).setTitle("اضافة حلقة");
    }

    private void getAjouterMemorisation() {
        ObservableList li = ViewSwitcher.SwitcheTo(View.AjouMemo);
        ((Stage) li.get(0)).setTitle("اضافة حفظ للطالبة");
    }

    private void getProfileEtudiant() {
        ObservableList li = ViewSwitcher.SwitcheTo(View.ProfilEt);
        ((Stage) li.get(0)).setTitle("معلومات الطالبة");
    }

    private void Delete() {

        // index = table_Etudiants.getSelectionModel().getSelectedIndex();
        // etudiant = table_Etudiants.getSelectionModel().getSelectedItem();
        boolean b = MEtudiant.Supprimer_Etudiant(etudiant.getIdEtudiant());
        if (b) {
            // table_Etudiants.getItems().remove(etudiant);
            table_Etudiants.setItems(MEtudiant.Afficher_Liste_etudiants());
            JOptionPane.showMessageDialog(null, "تم مسح الطالبة");
        } else {
            JOptionPane.showMessageDialog(null, " انتبه لم يتم مسح الطالبة");
        }

    }

    protected void AddLine(Etudiant e) {
        // add new Etudiant 
        listEtudiants.add(e);
        table_Etudiants.refresh();
    }

//soit ajib tt  la liste soit recheerche par id w njib ghir li tbadel
    protected void updateLine(Etudiant e) {
        // listEtudiants = table_Etudiants.getItems();
        for (Etudiant et : listEtudiants) {
            if (et.getIdEtudiant() == e.getIdEtudiant()) {
                et.setNom(e.getNom());
                et.setGroupe(e.getGroupe());
                et.setUniteAv(e.getUniteAv());
                et.setDureeAv(e.getDureeAv());
            }
        }
        table_Etudiants.setItems(listEtudiants);
        table_Etudiants.refresh();

    }

}
