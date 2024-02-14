package org.acme.curp.validarfc.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.acme.curp.validarfc.servicio.NubariumApiClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Base64;

@ApplicationScoped
public class ValidaRfcResponse {
    @JsonProperty("claveMensaje")
    public String claveMensaje;
    @JsonProperty("codigoValidacion")
    public String codigoValidacion;
    @JsonProperty("estatus")
    public String estatus;
    @JsonProperty("informacionAdicional")
    public String informacionAdicional;
    @JsonProperty("mensaje")
    public String mensaje;
    @JsonProperty("tipoPersona")
    public String tipoPersona;
}
