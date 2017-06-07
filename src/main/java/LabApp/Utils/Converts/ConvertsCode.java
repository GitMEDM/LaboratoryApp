package LabApp.Utils.Converts;

import LabApp.Database.Models.Code;
import LabApp.ModelFX.CodeFX;

/**
 * Created by MEDM on 2017-06-07.
 */
public class ConvertsCode {
    public static Code convertToCode(CodeFX codeFX){
        Code code = new Code();
        code.setId(codeFX.getId());
        code.setNameFile(codeFX.getNameFile());
        code.setDescription(codeFX.getDescription());
        code.setCode(codeFX.getCode());
        return code;
    }

    public static CodeFX convertToCodeFX(Code code){
        CodeFX codeFX = new CodeFX();
        codeFX.setId(code.getId());
        codeFX.setNameFile(code.getNameFile());
        codeFX.setDescription(code.getDescription());
        codeFX.setCode(code.getCode());
        return codeFX;
    }
}
