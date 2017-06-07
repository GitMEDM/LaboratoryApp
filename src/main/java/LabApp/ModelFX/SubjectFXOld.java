package LabApp.ModelFX;

import javafx.beans.property.*;

/**
 * Created by MEDM on 2017-06-04.
 */
public class SubjectFXOld {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty number = new SimpleStringProperty();
    private StringProperty subject = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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

    public String getSubject() {
        return subject.get();
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    @Override
    public String toString() {
        return subject.getValue();
    }
}
