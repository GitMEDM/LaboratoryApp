package LabApp.Controllers;

import LabApp.ModelFX.IssueFX;
import LabApp.ModelFX.IssueModel;
import LabApp.ModelFX.SubjectFX;
import LabApp.Utils.DialogsUtils;
import LabApp.Utils.Exceptions.ApplicationExeptions;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * Created by MEDM on 2017-06-05.
 */
public class IssueController {

    @FXML
    private TextField numberTextField;
    @FXML
    private TextField issueTextField;
    @FXML
    private Button addIssuesButton;
    @FXML
    private ComboBox<SubjectFX> subjectComboBox;
    @FXML
    private TableView<IssueFX> issueTableView;
    @FXML
    private TableColumn<IssueFX, String> numberColumn;
    @FXML
    private TableColumn<IssueFX, String> subjectColumn;
    @FXML
    private TableColumn<IssueFX, String> issueColumn;
    @FXML
    private MenuItem deleteButton;

    private IssueModel issueModel;

    public void initialize(){
        this.issueModel = new IssueModel();

        try {
            this.issueModel.init();
        } catch (ApplicationExeptions e) {
            DialogsUtils.errorDialog(e.getMessage());
        }


        bindings();
        bindingTableView();


    }

    private void bindingTableView() {
        //Autor bindowany z lisą wszystkich autorów i kolumny bindowane z propertkami w IssueMFX
        this.issueTableView.setItems(this.issueModel.getIssueFXObservableList());
        this.numberColumn.setCellValueFactory(cellData-> cellData.getValue().numberProperty());
        this.issueColumn.setCellValueFactory(cellData-> cellData.getValue().issueProperty());

        this.subjectColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.numberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.issueColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //Przechwycenie zaznaczenia
        this.issueTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.issueModel.setIssueFXObjectPropertyEdit(newValue);
        });
    }

    private void bindings() {
        this.subjectComboBox.setItems(this.issueModel.getSubjectFXObservableList());
        this.issueModel.issueFXObjectPropertyProperty().get().numberProperty().bind(this.numberTextField.textProperty());
        this.issueModel.issueFXObjectPropertyProperty().get().issueProperty().bind(this.issueTextField.textProperty());
        this.addIssuesButton.disableProperty().bind(this.issueTextField.textProperty().isEmpty().or(this.numberTextField.textProperty().isEmpty()));
        this.deleteButton.disableProperty().bind(this.issueTableView.getSelectionModel().selectedItemProperty().isNull());
    }


    @FXML
    public void addOnActionButton() {
        try {
            this.issueModel.saveIssueInDatabase();
        } catch (ApplicationExeptions e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        this.numberTextField.clear();
        this.issueTextField.clear();
    }

    @FXML
    public void onEditCommitSubject(TableColumn.CellEditEvent<IssueFX, String> issueFXStringCellEditEvent) {
        //this.issueModel.getIssueFXObjectPropertyEdit().setSubject_id(issueFXStringCellEditEvent.getNewValue());

    }

    @FXML
    public void onEditCommitNumber(TableColumn.CellEditEvent<IssueFX, String> issueFXStringCellEditEvent) {
        this.issueModel.getIssueFXObjectPropertyEdit().setNumber(issueFXStringCellEditEvent.getNewValue());
        updateInDatabase();
    }

    @FXML
    public void onEditCommitIssue(TableColumn.CellEditEvent<IssueFX, String> issueFXStringCellEditEvent) {
        this.issueModel.getIssueFXObjectPropertyEdit().setIssue(issueFXStringCellEditEvent.getNewValue());
        updateInDatabase();
    }

    private void updateInDatabase() {
        try {
            this.issueModel.saveIssueEditInDatabase();
        } catch (ApplicationExeptions e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    @FXML
    public void deleteOnAcionIsseu() {
        try {
            this.issueModel.deleteIssueInDatabase();
        } catch (ApplicationExeptions e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }


}
