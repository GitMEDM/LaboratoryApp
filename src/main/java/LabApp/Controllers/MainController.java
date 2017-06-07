package LabApp.Controllers;

import LabApp.Utils.DialogsUtils;
import LabApp.Utils.FXMLUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.STYLESHEET_MODENA;

/**
 * Created by MEDM on 2017-06-02.
 */
public class MainController {

    public static final String LANGUAGE_POLISH = "pl";
    public static final String LANGUAGE_ENGLISH = "en";
    public static final String LANGUAGE_GERMAN = "de";

    @FXML
    private BorderPane mainBorderPain;

    @FXML
    private TopMenuButtonController topMenuButtonHBoxController;

    @FXML
    private void initialize(){
        topMenuButtonHBoxController.setMainController(this);
    }

    public void setCenter(String fxmlPath){
         mainBorderPain.setCenter(FXMLUtils.fxmlLoader(fxmlPath));
    }

    public void closeApplication() {
        Optional<ButtonType> result = DialogsUtils.confirmationDialog();
        if(result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    public void setPolish(ActionEvent actionEvent) {
        Locale.setDefault(new Locale(LANGUAGE_POLISH));
    }

    public void setEnglish(ActionEvent actionEvent) {
        Locale.setDefault(new Locale(LANGUAGE_ENGLISH));
    }

    public void setGerman(ActionEvent actionEvent) {
        Locale.setDefault(new Locale(LANGUAGE_GERMAN));
    }

    public void setCaspian() {
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
    }

    public void setModena() {
        Application.setUserAgentStylesheet(STYLESHEET_MODENA);
    }

    public void setAlwaysOnTop(ActionEvent actionEvent) {
        Stage stage = (Stage) mainBorderPain.getScene().getWindow();
        boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(value);
    }

    public void openAbout() {
        DialogsUtils.dialogAboutApplication();
    }


}
