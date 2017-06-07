package LabApp.Utils;


import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by MEDM on 2017-06-02.
 */
public class DialogsUtils {

    static ResourceBundle bundle = FXMLUtils.getResourceBundle();

    public static void dialogAboutApplication(){
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle(bundle.getString("about.tittle"));
        informationAlert.setHeaderText(bundle.getString("about.header"));
        informationAlert.setContentText(bundle.getString("about.content"));
        informationAlert.showAndWait();
    }

    public static Optional<ButtonType> confirmationDialog(){
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(bundle.getString("exit.tittle"));
        confirmationDialog.setHeaderText(bundle.getString("exit.header"));
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result;
    }

    public static void errorDialog(String error){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error.tittle"));
        errorAlert.setHeaderText(bundle.getString("error.header"));
        Label label = new Label(error);
        //TextArea textArea = new TextArea(error);
        errorAlert.getDialogPane().setContent(label);
        errorAlert.showAndWait();
    }

    public static String editDialog(String value){
        TextInputDialog editDialog = new TextInputDialog(value);
        editDialog.setTitle(bundle.getString("edit.tittle"));
        editDialog.setHeaderText(bundle.getString("edit.header"));
        editDialog.setContentText(bundle.getString("edit.subject"));
        Optional<String> result = editDialog.showAndWait();
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }
}
