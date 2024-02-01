package org.acme.curp.obtienerazonsocial.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ObtieneRazonSocialResponse {
    @JsonProperty("claveMensaje")
    public String claveMensaje;
    @JsonProperty("codigoValidacion")
    public String codigoValidacion;
    @JsonProperty("estatus")
    public String estatus;
    @JsonProperty("nombre")
    public String nombre;
    @JsonProperty("rfc")
    public String rfc;
}
