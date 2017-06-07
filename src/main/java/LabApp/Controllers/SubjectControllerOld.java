package LabApp.Controllers;

import LabApp.ModelFX.SubjectFXOld;
import LabApp.ModelFX.SubjectModelOld;
import LabApp.Utils.DialogsUtils;
import LabApp.Utils.Exceptions.ApplicationExeptions;
import javafx.fxml.FXML;
import javafx.scene.control.*;


/**
 * Created by MEDM on 2017-06-04.
 */
public class SubjectControllerOld {

    @FXML
    private TextField subjectTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private ComboBox<SubjectFXOld> subjectComboBox;
    @FXML
    private TreeView<String> treeSubjectTreeView;

    private SubjectModelOld subjectModelOld;

    @FXML
    public void initialize(){
        this.subjectModelOld = new SubjectModelOld();
        try {
            this.subjectModelOld.init();
        } catch (ApplicationExeptions e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        this.subjectComboBox.setItems(this.subjectModelOld.getSubjectList());
        this.treeSubjectTreeView.setRoot(this.subjectModelOld.getRoot());
        initBindings();
    }

    private void initBindings() {
        addButton.disableProperty().bind(subjectTextField.textProperty().isEmpty());;
        //JEŚLI NIC NIE WYBRANE W COMBOBOX TO WYŁĄCZ PRZYCISK EDYCJA I USUŃ
        this.editButton.disableProperty().bind(this.subjectModelOld.subjectProperty().isNull());
        this.deleteButton.disableProperty().bind(this.subjectModelOld.subjectProperty().isNull());
    }

    @FXML
    public void addSubject() {
        try {
            subjectModelOld.saveSubjectInDatabase(subjectTextField.getText());
        } catch (ApplicationExeptions e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        subjectTextField.clear();
    }

    @FXML
    public void editSubject() {
        String newLaboratorySubject = DialogsUtils.editDialog(this.subjectModelOld.getSubject().getSubject());
        if(newLaboratorySubject != null){
            this.subjectModelOld.getSubject().setSubject(newLaboratorySubject);
            try {
                this.subjectModelOld.updateSubjectInDatabase();
            }catch (ApplicationExeptions e) {
                DialogsUtils.errorDialog(e.getMessage());
            }
        }
    }

    public void deleteSubject() {
        try {
            this.subjectModelOld.deleteSubjectById();
        } catch (ApplicationExeptions e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public void chooseSubjectComboBox() {
        this.subjectModelOld.setSubject(this.subjectComboBox.getSelectionModel().getSelectedItem());
    }
}
