package LabApp.Database.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by MEDM on 2017-06-04.
 */

@DatabaseTable(tableName = "LABORATORY_SUBJECT")
public class Subject implements BaseModel{

    public Subject() {
    }

    @DatabaseField(columnName = "ID", generatedId = true)
    private int id;

    @DatabaseField(columnName = "NUMBER", canBeNull = false, unique = true)
    private String number;

    @DatabaseField(columnName = "SUBJECT", canBeNull = false, unique = true)
    private String subject;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
