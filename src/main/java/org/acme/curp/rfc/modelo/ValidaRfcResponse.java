package org.acme.curp.rfc.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.curp.rfc.servicio.NubariumApiClient;
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

    @Inject
    @RestClient
    NubariumApiClient nubariumApiClient;

    public  ValidaRfcResponse nubariumApi(ValidaRfcRequest datos){
        String username = "axa-guillermocamachodelapaz";
        String password = "kb_#7Y.9D";

        String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        return nubariumApiClient.validaRfc(authorizationHeader,datos);
    }
}
