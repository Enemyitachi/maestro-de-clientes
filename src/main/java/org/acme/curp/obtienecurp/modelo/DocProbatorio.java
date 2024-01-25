package org.acme.curp.obtienecurp.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocProbatorio {
    @JsonProperty
    public String entidadRegistro;
    @JsonProperty
    public String tomo;
    @JsonProperty
    public String claveMunicipioRegistro;
    @JsonProperty
    public String anioReg;
    @JsonProperty
    public String claveEntidadRegistro;
    @JsonProperty
    public String foja;
    @JsonProperty
    public String numActa;
    @JsonProperty
    public String libro;
    @JsonProperty("municipioRegistro")
    public String municipioRegistro;
//    @JsonProperty
//    public String numRegExtranjeros;
//    @JsonProperty
//    public String folio;
    
}