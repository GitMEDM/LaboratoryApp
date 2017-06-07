package LabApp.ModelFX;

import javafx.beans.property.*;

import javax.naming.directory.Attribute;

/**
 * Created by MEDM on 2017-06-05.
 */
public class IssueFX {

    private IntegerProperty id = new SimpleIntegerProperty();
    private ObjectProperty<SubjectFX> subjectFX = new SimpleObjectProperty<>();
    private StringProperty number = new SimpleStringProperty();
    private StringProperty issue = new SimpleStringProperty();

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

    public String getNumber() {
        return number.get();
    }

    public StringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getIssue() {
        return issue.get();
    }

    public StringProperty issueProperty() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue.set(issue);
    }

    @Override
    public String toString() {
        return issue.getValue();
    }
}