/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anfel;

import Controllers.ViewSwitcher;
import Models.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class ANFEL extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        /*    Parent root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();*/
        ViewSwitcher.SwitcheTo(View.ProfilEt);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
