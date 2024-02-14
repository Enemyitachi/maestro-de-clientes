package org.acme.curp.obtienecurp.controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.curp.obtienecurp.modelo.ObtieneCurpRequest;
import org.acme.curp.obtienecurp.modelo.ObtieneCurpResponse;
import org.acme.curp.obtienecurp.servicio.NubariumApiClient;
import org.acme.curp.validacurp.modelo.ValidaCurpResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Base64;

@Path("/curp")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObtieneCurpController {
    String username = "axa-guillermocamachodelapaz";
    String password = "kb_#7Y.9D";
    String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    ObtieneCurpResponse obtieneCurpResponse;

    @Inject
    ObtieneCurpController(ObtieneCurpResponse obtieneCurpResponse){
        this.obtieneCurpResponse=obtieneCurpResponse;
    }

    @Inject
    @RestClient
    NubariumApiClient nubariumApiClient;

    @POST
    @Path("/obtiene-curp")
    public String obtieneCurp(ObtieneCurpRequest data){
        try {
            Response response = nubariumApiClient.obtieneCurp(authorizationHeader,data);
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
