package LabApp.ModelFX;

import LabApp.Database.Dao.CodeDao;
import LabApp.Database.Dao.IssueDao;
import LabApp.Database.Dao.SubjectDao;
import LabApp.Database.Models.Code;
import LabApp.Database.Models.Issue;
import LabApp.Database.Models.Subject;
import LabApp.Utils.Converts.ConvertsCode;
import LabApp.Utils.Converts.ConvertsIssue;
import LabApp.Utils.Converts.ConvertsSubject;
import LabApp.Utils.Exceptions.ApplicationExeptions;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by MEDM on 2017-06-07.
 */
public class CodeModel {

    private ObjectProperty<CodeFX> codeFXObjectProperty = new SimpleObjectProperty<>(new CodeFX());

    private ObservableList<SubjectFX> subjectFXObservableList = FXCollections.observableArrayList();
    private ObservableList<IssueFX> issueFXObservableList = FXCollections.observableArrayList();
    private ObservableList<CodeFX> codeFXObservableList = FXCollections.observableArrayList();


    public void init() throws ApplicationExeptions {
        //initCodeFX();
        initSubjectComboBox();
        initIssueComboBox();
    }

    private void initCodeFX() throws ApplicationExeptions {
        CodeDao codeDao = new CodeDao();
        List<Code> codeList = codeDao.queryForAll(Code.class);
        this.codeFXObservableList.clear();
        codeList.forEach(code -> {
            CodeFX codeFX = ConvertsCode.convertToCodeFX(code);
            this.codeFXObservableList.add(codeFX);
        });
    }

    private void initSubjectComboBox() throws ApplicationExeptions {
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.queryForAll(Subject.class);
        this.subjectFXObservableList.clear();
        subjectList.forEach(subject -> {
            SubjectFX subjectFX = ConvertsSubject.converToSubjectFX(subject);
            this.subjectFXObservableList.add(subjectFX);
        });
    }

    private void initIssueComboBox() throws ApplicationExeptions {
        IssueDao issueDao = new IssueDao();
        List<Issue> issueList = issueDao.queryForAll(Issue.class);
        this.issueFXObservableList.clear();
        issueList.forEach(issue -> {
            IssueFX issueFX = ConvertsIssue.convrtToIssueFX(issue);
            this.issueFXObservableList.add(issueFX);
        });
    }

    public void saveCodeInDatabase() throws ApplicationExeptions {
        CodeDao codeDao = new CodeDao();
        Code code = ConvertsCode.convertToCode(this.getCodeFXObjectProperty());

        /*SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.findById(Subject.class, this.getCodeFXObjectProperty().getSubjectFX().getId());
        code.setSubject(subject);

        IssueDao issueDao = new IssueDao();
        Issue issue = issueDao.findById(Issue.class, this.getCodeFXObjectProperty().getIssueFX().getId());
        code.setIssue(issue);*/

        codeDao.creatOrUpdate(code);
        this.init();
    }

    /*public void saveOrUpdate(CodeFX codeFXObjectProperty) throws ApplicationExeptions {
        CodeDao codeDao = new CodeDao();
        Code code = ConvertsCode.convertToCode(this.getCodeFXObjectProperty());

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.findById(Subject.class, this.getCodeFXObjectProperty().getSubjectFX().getId());
        code.setSubject(subject);

        IssueDao issueDao = new IssueDao();
        Issue issue = issueDao.findById(Issue.class, this.getCodeFXObjectProperty().getIssueFX().getId());
        code.setIssue(issue);

        codeDao.creatOrUpdate(code);
        this.init();
    }*/

    //GETTER AND SETTER

    public CodeFX getCodeFXObjectProperty() {
        return codeFXObjectProperty.get();
    }

    public ObjectProperty<CodeFX> codeFXObjectPropertyProperty() {
        return codeFXObjectProperty;
    }

    public void setCodeFXObjectProperty(CodeFX codeFXObjectProperty) {
        this.codeFXObjectProperty.set(codeFXObjectProperty);
    }

    public ObservableList<SubjectFX> getSubjectFXObservableList() {
        return subjectFXObservableList;
    }

    public void setSubjectFXObservableList(ObservableList<SubjectFX> subjectFXObservableList) {
        this.subjectFXObservableList = subjectFXObservableList;
    }

    public ObservableList<IssueFX> getIssueFXObservableList() {
        return issueFXObservableList;
    }

    public void setIssueFXObservableList(ObservableList<IssueFX> issueFXObservableList) {
        this.issueFXObservableList = issueFXObservableList;
    }

    public ObservableList<CodeFX> getCodeFXObservableList() {
        return codeFXObservableList;
    }

    public void setCodeFXObservableList(ObservableList<CodeFX> codeFXObservableList) {
        this.codeFXObservableList = codeFXObservableList;
    }
}
