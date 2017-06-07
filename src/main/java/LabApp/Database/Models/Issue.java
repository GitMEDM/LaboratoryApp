package LabApp.Database.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by MEDM on 2017-06-04.
 */

@DatabaseTable(tableName = "LABORATORY_ISSUES")
public class Issue implements BaseModel {

    public Issue() {
    }

    @DatabaseField(columnName = "ID", generatedId = true)
    private int id;

    @DatabaseField(columnName = "SUBJECT_ID", foreign = true)/*, , foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)*/
    private Subject subject;

    @DatabaseField(columnName = "NUMBER", canBeNull = false)
    private String number;

    @DatabaseField(columnName = "ISSUES", canBeNull = false, unique = true)
    private String issue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
}
