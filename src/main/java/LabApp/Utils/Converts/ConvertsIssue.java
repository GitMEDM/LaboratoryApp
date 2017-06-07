package LabApp.Utils.Converts;

import LabApp.Database.Models.Issue;
import LabApp.ModelFX.IssueFX;

/**
 * Created by MEDM on 2017-06-05.
 */
public class ConvertsIssue {

    public static Issue convertToIssues(IssueFX issueFX){
        Issue issue = new Issue();
        issue.setId(issueFX.getId());
        //issue.setSubject();
        issue.setNumber(issueFX.getNumber());
        issue.setIssue(issueFX.getIssue());
        return issue;
    }

    public static IssueFX convrtToIssueFX(Issue issue){
        IssueFX issueFX = new IssueFX();
        issueFX.setId(issue.getId());
        issueFX.setNumber(issue.getNumber());
        issueFX.setIssue(issue.getIssue());
        return issueFX;
    }


}
