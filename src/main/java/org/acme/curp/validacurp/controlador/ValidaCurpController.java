package org.acme.curp.validacurp.controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.curp.validacurp.modelo.ValidaCurpRequest;
import org.acme.curp.validacurp.modelo.ValidaCurpResponse;

import org.acme.curp.validacurp.servicio.NubariumApiClient;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Base64;

@Path("/curp")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ValidaCurpController {
    String username = "axa-guillermocamachodelapaz";
    String password = "kb_#7Y.9D";
    String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    ValidaCurpResponse validaCurpResponse;
    @Inject
    ValidaCurpController(ValidaCurpResponse validaCurpResponse){
        this.validaCurpResponse=validaCurpResponse;
    }

    @Inject
    @RestClient
    NubariumApiClient nubariumApiClient;

    @POST
    @Path("/valida-curp")
    public String validarCurp(ValidaCurpRequest data){
        try {
            Response response = nubariumApiClient.validaCurp(authorizationHeader,data);
            return "CURP: "+response.readEntity(ValidaCurpResponse.class).curp+"\nEstatus de Curp: "+ response.readEntity(ValidaCurpResponse.class).estatus;
        }catch (WebApplicationException e){
            switch (e.getResponse().getStatus()) {
                case 400:
                    return "Bad request 400 ayuda"+ e.getResponse().getStatus();
                case 401:
                    return "Bad request 401";
                case 403:
                    return "Problema de credenciales";
                default:
                    return "Bad request";
            }
        }catch (Exception e){
            return "Error desconocido: "+e.getMessage();
        }
    }
}