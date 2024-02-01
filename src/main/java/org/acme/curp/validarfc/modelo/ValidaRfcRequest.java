package org.acme.curp.validarfc.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ValidaRfcRequest {
    @JsonProperty("rfc")
    String rfc;
}
