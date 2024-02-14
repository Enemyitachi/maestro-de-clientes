package org.acme.curp.obtienecurp.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.curp.obtienecurp.servicio.NubariumApiClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Base64;

@ApplicationScoped
public class ObtieneCurpResponse {
    @JsonProperty("estatus")
    public String estatus;
    @JsonProperty("codigoValidacion")
    public String codigoValidacion;
    @JsonProperty("curp")
    public String curp;
    @JsonProperty("nombre")
    public String nombre;
    @JsonProperty("apellidoPaterno")
    public String apellidoPaterno;
    @JsonProperty("apellidoMaterno")
    public String apellidoMaterno;
    @JsonProperty("sexo")
    public String sexo;
    @JsonProperty("fechaNacimiento")
    public String fechaNacimiento;
    @JsonProperty("paisNacimiento")
    public String paisNacimiento;
    @JsonProperty("estadoNacimiento")
    public String estadoNacimiento;
    @JsonProperty("docProbatorio")
    public int docProbatorio;
    @JsonProperty("datosDocProbatorio")
    public DocProbatorio datosDocProbatorio;
    @JsonProperty("estatusCurp")
    public String estatusCurp;
    @JsonProperty("codigoMensaje")
    public String codigoMensaje;
}
