package org.acme.curp.obtienerazonsocial.controlador;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.curp.obtienerazonsocial.modelo.ObtieneRazonSocialRequest;
import org.acme.curp.obtienerazonsocial.modelo.ObtieneRazonSocialResponse;
import org.acme.curp.obtienerazonsocial.servicio.NubariumApiClient;
import org.acme.curp.validarfc.modelo.ValidaRfcResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Base64;

@Path("/rfc")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObtieneRazonSocialController {
    String username = "axa-guillermocamachodelapaz";
    String password = "kb_#7Y.9D";
    String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

    ObtieneRazonSocialResponse obtieneRazonSocialResponse;

    @Inject
    ObtieneRazonSocialController(ObtieneRazonSocialResponse obtieneRazonSocialResponse){
        this.obtieneRazonSocialResponse=obtieneRazonSocialResponse;
    }

    @Inject
    @RestClient
    NubariumApiClient nubariumApiClient;

    @POST
    @Path("/obtener-razon-social")
    public String obtieneRazonSocial(ObtieneRazonSocialRequest rfc){
        try {
            Response response = nubariumApiClient.obtieneRazonSocial(authorizationHeader, rfc);
            return "Nombre de razon social: "+response.readEntity(ObtieneRazonSocialResponse.class).nombre;
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
