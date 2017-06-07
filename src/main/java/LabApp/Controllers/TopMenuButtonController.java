package LabApp.Controllers;

import javafx.fxml.FXML;

/**
 * Created by MEDM on 2017-06-02.
 */
public class TopMenuButtonController {

    //STAŁE Z ADRESAMI DO PLIKÓW FXML
    public static final String LABORATORY_SUBJECT_SCREEN_FXML = "/FXML/SubjectScreen.fxml";
    public static final String LABORATORY_ISSUES_SCREEN_FXML = "/FXML/IssueScreen.fxml";
    public static final String LABORATORY_CODE_SCREEN_FXML = "/FXML/CodeScreen.fxml";

    @FXML
    private MainController mainController;

    @FXML
    public void LaboratorySubject() {
        System.out.println("jestem");
        mainController.setCenter(LABORATORY_SUBJECT_SCREEN_FXML);
    }

    @FXML
    public void LaboratoryIssues() {
        System.out.println("jestem");
        mainController.setCenter(LABORATORY_ISSUES_SCREEN_FXML);
    }

    @FXML
    public void LaboratoryCode() {
        System.out.println("jestem");
        mainController.setCenter(LABORATORY_CODE_SCREEN_FXML);
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


}
