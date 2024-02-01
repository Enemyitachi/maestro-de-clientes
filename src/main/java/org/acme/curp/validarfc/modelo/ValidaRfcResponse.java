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

    @Inject
    @RestClient
    NubariumApiClient nubariumApiClient;

    public Response nubariumApi(ValidaRfcRequest datos){
        String username = "axa-guillermocamachodelapaz";
        String password = "kb_#7Y.9D";

        String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    try{
        Response response = nubariumApiClient.validaRfc(authorizationHeader,datos);

        int status = response.getStatus();
        switch (status) {
            case 200: // OK
                return response;
            case 400: // Bad Request
                System.out.println("Bad request 400");
                break;
            case 401: // Unauthorized
                System.out.println("Bad request");
                break;
            case 403:
                System.out.println("Problema de credenciales");
                break;
            default:
                System.out.println("Bad request");
                break;
        }
        response.close();
    }catch (WebApplicationException e) {
        Response response = e.getResponse();
        int status = response.getStatus();
        System.out.println(status);
        return response;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
        return null;
    }
}
