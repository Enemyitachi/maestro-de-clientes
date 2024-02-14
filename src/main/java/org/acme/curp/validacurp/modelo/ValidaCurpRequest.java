package org.acme.curp.validacurp.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ValidaCurpRequest {
    @JsonProperty("curp")
    String curp;
    @JsonProperty("apellidoPaterno")
    String apellidoPaterno;
    @JsonProperty("nomrbe")
    String nombre;
    @JsonProperty("fechaNacimiento")
    String fechaNacimiento;
    @JsonProperty("apellidoMaterno")
    String apellidoMaterno;
}
