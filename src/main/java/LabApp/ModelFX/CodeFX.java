package LabApp.ModelFX;

import javafx.beans.property.*;

/**
 * Created by MEDM on 2017-06-07.
 */
public class CodeFX {

    private IntegerProperty id = new SimpleIntegerProperty();
    private ObjectProperty<SubjectFX> subjectFX = new SimpleObjectProperty<>();
    private ObjectProperty<IssueFX> issueFX = new SimpleObjectProperty<>();
    private StringProperty nameFile = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty code = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SubjectFX getSubjectFX() {
        return subjectFX.get();
    }

    public ObjectProperty<SubjectFX> subjectFXProperty() {
        return subjectFX;
    }

    public void setSubjectFX(SubjectFX subjectFX) {
        this.subjectFX.set(subjectFX);
    }

    public IssueFX getIssueFX() {
        return issueFX.get();
    }

    public ObjectProperty<IssueFX> issueFXProperty() {
        return issueFX;
    }

    public void setIssueFX(IssueFX issueFX) {
        this.issueFX.set(issueFX);
    }

    public String getNameFile() {
        return nameFile.get();
    }

    public StringProperty nameFileProperty() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile.set(nameFile);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getCode() {
        return code.get();
    }

    public StringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }
}
