package LabApp.Database.Models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by MEDM on 2017-06-04.
 */

@DatabaseTable(tableName = "LABORATORY_CODE")
public class Code implements BaseModel {

    @DatabaseField(columnName = "ID", generatedId = true)
    private int id;

    @DatabaseField(columnName = "SUBJECT_ID", foreign = true)
    private Subject subject;

    @DatabaseField(columnName = "ISSUE_ID", foreign = true)
    private Issue isue;

    @DatabaseField(columnName = "NAME_FILE"/*, canBeNull = false*/,unique = true)
    private String nameFile;

    @DatabaseField(columnName = "DESCRIPTION"/*, canBeNull = false*/)
    private String description;

    @DatabaseField(columnName = "CODE"/*, canBeNull = false*/, dataType = DataType.LONG_STRING)
    private String code;

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

    public Issue getIsue() {
        return isue;
    }

    public void setIssue(Issue isue) {
        this.isue = isue;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
