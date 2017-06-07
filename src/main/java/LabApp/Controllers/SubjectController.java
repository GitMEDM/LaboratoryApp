package LabApp.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.MenuItem;

import LabApp.ModelFX.SubjectFX;
import LabApp.ModelFX.SubjectModel;
import LabApp.Utils.DialogsUtils;
import LabApp.Utils.Exceptions.ApplicationExeptions;

import javafx.scene.control.cell.TextFieldTableCell;

/**
 * Created by MEDM on 2017-06-05.
 */
public class SubjectController {

    @FXML
    private TextField numberTextField;
    @FXML
    private TextField subjectTextField;
    @FXML
    private Button addButton;
    @FXML
    private TableView<SubjectFX> subjectTableView;
    @FXML
    private TableColumn<SubjectFX, String> numberColumn;
    @FXML
    private TableColumn<SubjectFX, String> subjectColumn;
    @FXML
    private MenuItem deleteButton;

    private SubjectModel subjectModel;

    public void initialize(){
        this.subjectModel = new SubjectModel();

        try {
            this.subjectModel.init();
        } catch (ApplicationExeptions e) {
            DialogsUtils.errorDialog(e.getMessage());
        }


        this.subjectModel.subjectFXObjectPropertyProperty().get().numberProperty().bind(this.numberTextField.textProperty());
        this.subjectModel.subjectFXObjectPropertyProperty().get().subjectProperty().bind(this.subjectTextField.textProperty());

        this.addButton.disableProperty().bind(this.numberTextField.textProperty().isEmpty().or(this.subjectTextField.textProperty().isEmpty()));
        this.deleteButton.disableProperty().bind(this.subjectTableView.getSelectionModel().selectedItemProperty().isNull());

        //Autor bindowany z lisą wszystkich autorów i kolumny bindowane z propertkami w IssueMFX
        this.subjectTableView.setItems(this.subjectModel.getSubjectFXObservableList());
        this.numberColumn.setCellValueFactory(cellData-> cellData.getValue().numberProperty());
        this.subjectColumn.setCellValueFactory(cellData-> cellData.getValue().subjectProperty());


        //Przechwycenie zaznaczenia
        this.numberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.subjectColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //Przechwycenie zaznaczenia
        this.subjectTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.subjectModel.setSubjectFXObjectPropertyEdit(newValue);
        });
    }

    @FXML
    public void addOnActionSubject() {
        try {
            this.subjectModel.saveSubjectInDatabase();
        } catch (ApplicationExeptions e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        this.numberTextField.clear();
        this.subjectTextField.clear();
    }

    @FXML
    public void onEditCommitNumber(TableColumn.CellEditEvent<SubjectFX, String> subjectFXStringCellEditEvent) {
        this.subjectModel.getSubjectFXObjectPropertyEdit().setNumber(subjectFXStringCellEditEvent.getNewValue());
        updateInDatabase();
    }

    @FXML
    public void onEditCommitSubject(TableColumn.CellEditEvent<SubjectFX, String> subjectFXStringCellEditEvent) {
        this.subjectModel.getSubjectFXObjectPropertyEdit().setSubject(subjectFXStringCellEditEvent.getNewValue());
        updateInDatabase();
    }

    @FXML
    public void deleteOnAcionSubject() {
        try {
            this.subjectModel.deleteSubjectInDatabase();
        } catch (ApplicationExeptions e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    private void updateInDatabase() {
        try {
            this.subjectModel.saveSubjectEditInDatabase();
        } catch (ApplicationExeptions e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }
}
