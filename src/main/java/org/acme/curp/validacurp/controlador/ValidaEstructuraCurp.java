package org.acme.curp.validacurp.controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaEstructuraCurp {
    public boolean validarCurp(String curp){
        String regex = "[A-Z]{4}[0-9]{6}[H,M][A-Z]{5}[A-Z,0-9][0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(curp);
        return matcher.find();
    }
}
