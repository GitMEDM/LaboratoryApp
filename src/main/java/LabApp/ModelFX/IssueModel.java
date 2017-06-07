package LabApp.ModelFX;

import LabApp.Database.DBUtils.DBMenager;
import LabApp.Database.Dao.IssueDao;
import LabApp.Database.Dao.SubjectDao;
import LabApp.Database.Models.Issue;
import LabApp.Database.Models.Subject;
import LabApp.Utils.Converts.ConvertsIssue;
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
public class IssueModel {

    private ObjectProperty<IssueFX> IssueFXObjectProperty = new SimpleObjectProperty<>(new IssueFX());
    private ObjectProperty<IssueFX> IssueFXObjectPropertyEdit = new SimpleObjectProperty<>(new IssueFX());

    private ObservableList<IssueFX> issueFXObservableList = FXCollections.observableArrayList();

    private ObservableList<SubjectFX> subjectFXObservableList = FXCollections.observableArrayList();
    //private ObservableList<SubjectFX> subject = FXCollections.observableArrayList();

    public void init() throws ApplicationExeptions {
        initIssueFX();
        initSubjectComboBox();
    }

    private void initIssueFX() throws ApplicationExeptions {
        IssueDao issueDao = new IssueDao();
        List<Issue> issueList = issueDao.queryForAll(Issue.class);
        this.issueFXObservableList.clear();
        issueList.forEach(issue -> {
            IssueFX issueFX = ConvertsIssue.convrtToIssueFX(issue);
            this.issueFXObservableList.add(issueFX);
        });

    }

    public void initSubjectComboBox() throws ApplicationExeptions {
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.queryForAll(Subject.class);
        subjectFXObservableList.clear();
        subjectList.forEach(subject->{
            SubjectFX subjectFX = ConvertsSubject.converToSubjectFX(subject);
            this.subjectFXObservableList.add(subjectFX);
        });
    }



    public void saveIssueInDatabase() throws ApplicationExeptions {
        saveOrUpdate(this.getIssueFXObjectProperty());
    }

    public void saveIssueEditInDatabase() throws ApplicationExeptions {
        saveOrUpdate(this.getIssueFXObjectPropertyEdit());
    }

    public void deleteIssueInDatabase() throws ApplicationExeptions {
        IssueDao issueDao = new IssueDao();
        issueDao.deleteById(Issue.class, this.getIssueFXObjectPropertyEdit().getId());
        this.init();
    }

    private void saveOrUpdate(IssueFX issueFXObjectProperty) throws ApplicationExeptions {
        IssueDao issueDao = new IssueDao();
        Issue issue = ConvertsIssue.convertToIssues(issueFXObjectProperty);

        /*SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.findById(Subject.class, this.getIssueFXObjectProperty().getSubjectFX().getId());*/
        //System.out.println(subjectDao.findById(Subject.class, this.getIssueFXObjectProperty().getSubject_id().getId()));
        //issue.setSubject(subject);


        issueDao.creatOrUpdate(issue);

        this.init();
    }



    public IssueFX getIssueFXObjectProperty() {
        return IssueFXObjectProperty.get();
    }

    public ObjectProperty<IssueFX> issueFXObjectPropertyProperty() {
        return IssueFXObjectProperty;
    }

    public void setIssueFXObjectProperty(IssueFX issueFXObjectProperty) {
        this.IssueFXObjectProperty.set(issueFXObjectProperty);
    }

    public ObservableList<IssueFX> getIssueFXObservableList() {
        return issueFXObservableList;
    }

    public void setIssueFXObservableList(ObservableList<IssueFX> issueFXObservableList) {
        this.issueFXObservableList = issueFXObservableList;
    }

    public IssueFX getIssueFXObjectPropertyEdit() {
        return IssueFXObjectPropertyEdit.get();
    }

    public ObjectProperty<IssueFX> issueFXObjectPropertyEditProperty() {
        return IssueFXObjectPropertyEdit;
    }

    public void setIssueFXObjectPropertyEdit(IssueFX issueFXObjectPropertyEdit) {
        this.IssueFXObjectPropertyEdit.set(issueFXObjectPropertyEdit);
    }

    public ObservableList<SubjectFX> getSubjectFXObservableList() {
        return subjectFXObservableList;
    }

    public void setSubjectFXObservableList(ObservableList<SubjectFX> subjectFXObservableList) {
        this.subjectFXObservableList = subjectFXObservableList;
    }


}
