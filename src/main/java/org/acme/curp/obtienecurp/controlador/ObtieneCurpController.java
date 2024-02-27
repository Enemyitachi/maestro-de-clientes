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
import org.acme.curp.validacurp.controlador.ValidaCurpController;
import org.acme.curp.validacurp.modelo.ValidaCurpRequest;
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
    ValidaCurpController validaCurpController;

    @Inject
    @RestClient
    NubariumApiClient nubariumApiClient;

    @POST
    @Path("/obtiene-curp")
    public Response obtieneCurp(ObtieneCurpRequest data){
        try {
            Response response = nubariumApiClient.obtieneCurp(authorizationHeader,data);
            ValidaCurpRequest validaCurpRequest = new ValidaCurpRequest();
            validaCurpRequest.setCurp(response.readEntity(ObtieneCurpResponse.class).curp);
            return validaCurpController.validarCurp(validaCurpRequest);
        }catch (WebApplicationException e){
            switch (e.getResponse().getStatus()) {
                case 400:
                    return Response.ok("Bad request 400 ayuda"+ e.getResponse().getStatus()).status(400).build();
                case 401:
                    return Response.ok("Bad request 401").status(401).build();
                case 403:
                    return Response.ok("Problema de credenciales").status(403).build();
                default:
                    return Response.ok("Bad request").status(500).build();
            }
        }catch (Exception e){
            return Response.ok("Error desconocido: "+e.getMessage()).status(500).build();
        }
    }
}
