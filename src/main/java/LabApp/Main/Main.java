package LabApp.Main;

import LabApp.Database.DBUtils.DBMenager;
import LabApp.Utils.FXMLUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by MEDM on 2017-06-02.
 */
public class Main extends Application {

    //STAŁE Z ADRESAMI DO PLIKÓW FXML
    public static final String MAIN_SCREEN_FXML = "/FXML/MainScreen.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    public void start (Stage primaryStage) throws Exception {
        Pane borderPane = FXMLUtils.fxmlLoader(MAIN_SCREEN_FXML);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FXMLUtils.getResourceBundle().getString("tittle.application"));
        primaryStage.show();

        DBMenager.initDatabase();
    }
}
