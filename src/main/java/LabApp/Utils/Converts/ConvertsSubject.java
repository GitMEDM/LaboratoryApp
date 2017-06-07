package LabApp.Utils.Converts;

import LabApp.Database.Models.Subject;
import LabApp.ModelFX.SubjectFX;
import LabApp.ModelFX.SubjectFXOld;

/**
 * Created by MEDM on 2017-06-04.
 */
public class ConvertsSubject {

    public static SubjectFXOld convertToLaboratorySubjectFX(Subject subject){
        SubjectFXOld subjectFXOld = new SubjectFXOld();
        subjectFXOld.setId(subject.getId());
        subjectFXOld.setSubject(subject.getSubject());
        return subjectFXOld;
    }

    public static Subject convertToSubject(SubjectFX subjectFX){
        Subject subject = new Subject();
        subject.setId(subjectFX.getId());
        subject.setNumber(subjectFX.getNumber());
        subject.setSubject(subjectFX.getSubject());
        return subject;

    }

    public static SubjectFX converToSubjectFX(Subject subject){
        SubjectFX subjectFX = new SubjectFX();
        subjectFX.setId(subject.getId());
        subjectFX.setNumber(subject.getNumber());
        subjectFX.setSubject(subject.getSubject());
        return subjectFX;
    }
}
