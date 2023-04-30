/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.View;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class ViewSwitcher {

    private static Map<View, ObservableList> cache = new HashMap<>();// ObservableList 1st item stage  0 ,2nd item Scene 1 ,3rd item Parent 2 , 4th item FXMLLoader 3 ;

    public static Map<View, ObservableList> getCache() {
        return cache;
    }
    public static ObservableList  getCache(View view) {
        return cache.get(view);
    }

    public static FXMLLoader getLoader(View view) {
        return (FXMLLoader) cache.get(view).get(3);
    }
  public static Stage getStage(View view) {
        return (Stage) cache.get(view).get(0);
    }
    public static ObservableList SwitcheTo(View view) {
        Stage stage;
        Scene scene;
        FXMLLoader loader;
        Parent root;
        ObservableList list = null;
        try {
            if (cache.containsKey(view)) {
                System.out.println("Loading from Cache ");
                loader = (FXMLLoader) cache.get(view).get(3);
                root = (Parent) cache.get(view).get(2);
                scene = (Scene) cache.get(view).get(1);
                stage = (Stage) cache.get(view).get(0);
                scene.setRoot(root);
                stage.setScene(scene);
                if (!stage.isShowing()) {
                    stage.show();
                }
                list = FXCollections.observableArrayList(stage, scene, root, loader);

            } else {
                System.out.println("Loading from FXML ");
                loader = new FXMLLoader(
                        ViewSwitcher.class.getResource(view.getFileName()));
                root = loader.load();
                scene = new Scene(root);
                stage = new Stage();
                stage.setScene(scene);
                stage.show();
                list = FXCollections.observableArrayList(stage, scene, root, loader);
                cache.put(view, list);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
