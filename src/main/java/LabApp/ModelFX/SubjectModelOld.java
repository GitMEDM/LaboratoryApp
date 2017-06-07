package LabApp.ModelFX;

import LabApp.Database.DBUtils.DBMenager;
import LabApp.Database.Dao.SubjectDao;
import LabApp.Database.Models.Subject;
import LabApp.Utils.Exceptions.ApplicationExeptions;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.List;

import static LabApp.Utils.Converts.ConvertsSubject.convertToLaboratorySubjectFX;

/**
 * Created by MEDM on 2017-06-04.
 */
public class SubjectModelOld {

    private ObservableList<SubjectFXOld> subjectList = FXCollections.observableArrayList();
    private ObjectProperty<SubjectFXOld> subject = new SimpleObjectProperty<>();
    private TreeItem<String> root = new TreeItem<>();

        public void init() throws ApplicationExeptions {
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjects = subjectDao.queryForAll(Subject.class);
        initCategoryList(subjects);
        initRoot(subjects);
    }

    private void initRoot(List<Subject> subjects) {
        this.root.getChildren().clear();
        subjects.forEach(c->{
            TreeItem<String> laboratorySubjectItem = new TreeItem<>(c.getSubject());
            root.getChildren().add(laboratorySubjectItem);
        });
    }

    private void initCategoryList(List<Subject> subjects) {
        this.subjectList.clear();
        subjects.forEach(c->{
            SubjectFXOld subjectFXOld = convertToLaboratorySubjectFX(c);
            this.subjectList.add(subjectFXOld);
        });
    }

    public void saveSubjectInDatabase(String number) throws ApplicationExeptions {
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = new Subject();
        subject.setSubject(number);
        subjectDao.creatOrUpdate(subject);
        init();
    }

    public void deleteSubjectById() throws ApplicationExeptions{
        SubjectDao subjectDao = new SubjectDao();
        subjectDao.deleteById(Subject.class, subject.getValue().getId());
        init();
    }

    public void updateSubjectInDatabase() throws ApplicationExeptions {
        SubjectDao subjectDao = new SubjectDao();
        Subject tempSubject = subjectDao.findById(Subject.class, getSubject().getId());
        //tempSubject.setNumber(getSubject().getNumber());
        tempSubject.setSubject(getSubject().getSubject());
        subjectDao.creatOrUpdate(tempSubject);
        init();
    }

    public ObservableList<SubjectFXOld> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(ObservableList<SubjectFXOld> subjectList) {
        this.subjectList = subjectList;
    }

    public SubjectFXOld getSubject() {
        return subject.get();
    }

    public ObjectProperty<SubjectFXOld> subjectProperty() {
        return subject;
    }

    public void setSubject(SubjectFXOld subject) {
        this.subject.set(subject);
    }

    public TreeItem<String> getRoot() {
        return root;
    }

    public void setRoot(TreeItem<String> root) {
        this.root = root;
    }
}
