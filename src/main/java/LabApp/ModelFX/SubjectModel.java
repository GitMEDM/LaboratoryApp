package LabApp.ModelFX;

import LabApp.Database.DBUtils.DBMenager;
import LabApp.Database.Dao.SubjectDao;
import LabApp.Database.Models.Subject;
import LabApp.Utils.Converts.ConvertsSubject;
import LabApp.Utils.Exceptions.ApplicationExeptions;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by MEDM on 2017-06-05.
 */
public class SubjectModel {

    private ObjectProperty<SubjectFX> SubjectFXObjectProperty = new SimpleObjectProperty<>(new SubjectFX());
    private ObjectProperty<SubjectFX> SubjectFXObjectPropertyEdit = new SimpleObjectProperty<>(new SubjectFX());

    private ObservableList<SubjectFX> subjectFXObservableList = FXCollections.observableArrayList();
    //
    public void init() throws ApplicationExeptions {
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.queryForAll(Subject.class);
        this.subjectFXObservableList.clear();
        subjectList.forEach(subject -> {
            SubjectFX subjectFX = ConvertsSubject.converToSubjectFX(subject);
            this.subjectFXObservableList.add(subjectFX);
        });
    }


    public void saveSubjectInDatabase() throws ApplicationExeptions {
        saveOrUpdate(this.getSubjectFXObjectProperty());
    }

    public void saveSubjectEditInDatabase() throws ApplicationExeptions {
        saveOrUpdate(this.getSubjectFXObjectPropertyEdit());
    }

    public void deleteSubjectInDatabase() throws ApplicationExeptions {
        SubjectDao issueDao = new SubjectDao();
        issueDao.deleteById(Subject.class, this.getSubjectFXObjectPropertyEdit().getId());
        this.init();
    }

    private void saveOrUpdate(SubjectFX subjectFXObjectProperty) throws ApplicationExeptions {
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = ConvertsSubject.convertToSubject(subjectFXObjectProperty);
        subjectDao.creatOrUpdate(subject);
        this.init();
    }

    public SubjectFX getSubjectFXObjectProperty() {
        return SubjectFXObjectProperty.get();
    }

    public ObjectProperty<SubjectFX> subjectFXObjectPropertyProperty() {
        return SubjectFXObjectProperty;
    }

    public void setSubjectFXObjectProperty(SubjectFX subjectFXObjectProperty) {
        this.SubjectFXObjectProperty.set(subjectFXObjectProperty);
    }

    public SubjectFX getSubjectFXObjectPropertyEdit() {
        return SubjectFXObjectPropertyEdit.get();
    }

    public ObjectProperty<SubjectFX> subjectFXObjectPropertyEditProperty() {
        return SubjectFXObjectPropertyEdit;
    }

    public void setSubjectFXObjectPropertyEdit(SubjectFX subjectFXObjectPropertyEdit) {
        this.SubjectFXObjectPropertyEdit.set(subjectFXObjectPropertyEdit);
    }

    public ObservableList<SubjectFX> getSubjectFXObservableList() {
        return subjectFXObservableList;
    }

    public void setSubjectFXObservableList(ObservableList<SubjectFX> subjectFXObservableList) {
        this.subjectFXObservableList = subjectFXObservableList;
    }
}

