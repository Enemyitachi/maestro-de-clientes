package org.acme.curp.obtienerazonsocial.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ObtieneRazonSocialRequest {
    @JsonProperty("rfc")
    String rfc;
}
