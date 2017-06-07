package LabApp.Controllers;

import LabApp.ModelFX.CodeModel;
import LabApp.Utils.DialogsUtils;
import LabApp.Utils.Exceptions.ApplicationExeptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Created by MEDM on 2017-06-05.
 */
public class CodeController {

    @FXML
    private ComboBox subjectComboBox;
    @FXML
    private ComboBox issueComboBox;
    @FXML
    private TextField nameFileTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextArea codeTextArea;


    private CodeModel codeModel;

    public void initialize(){
        this.codeModel = new CodeModel();
        try {
            this.codeModel.init();
        } catch (ApplicationExeptions e) {
            DialogsUtils.errorDialog(e.getMessage());
        }

        bildings();
    }

    private void bildings() {
        this.subjectComboBox.setItems(this.codeModel.getSubjectFXObservableList());
        this.issueComboBox.setItems(this.codeModel.getIssueFXObservableList());

        this.codeModel.codeFXObjectPropertyProperty().get().nameFileProperty().bind(this.nameFileTextField.textProperty());
        this.codeModel.codeFXObjectPropertyProperty().get().descriptionProperty().bind(this.descriptionTextArea.textProperty());
        this.codeModel.codeFXObjectPropertyProperty().get().codeProperty().bind(this.codeTextArea.textProperty());
    }

    @FXML
    public void addOnActionButton() {
        try {
            this.codeModel.saveCodeInDatabase();
        } catch (ApplicationExeptions e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }
}
