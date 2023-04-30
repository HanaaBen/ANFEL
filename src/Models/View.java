/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author pc
 */
public enum View {
    AjouEtudiant("/Views/AjouterEtudiant.fxml"),
    AjouMemo("/Views/AjouterMemorisation.fxml"),
    Login("/Views/Login.fxml"), 
    Main("/Views/Main.fxml"),
    ProfilEt("/Views/ProfilEtudiant.fxml"),
    Table("/Views/Table.fxml"),
    AjouGroup("/Views/AjouterGroupe.fxml");
    
    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
