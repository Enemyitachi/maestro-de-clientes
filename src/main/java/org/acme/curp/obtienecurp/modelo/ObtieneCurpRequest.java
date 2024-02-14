package org.acme.curp.obtienecurp.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ObtieneCurpRequest {
    @JsonProperty("nombre")
    String nombre;
    @JsonProperty("fechaNacimiento")
    String fechaNacimiento;
    @JsonProperty("primerApellido")
    String primerApellido;
    @JsonProperty("segundoApellido")
    String segundoApellido;
    @JsonProperty("sexo")
    String sexo;
    @JsonProperty("documento")
    String documento;
    @JsonProperty("entidad")
    String entidad;
}
