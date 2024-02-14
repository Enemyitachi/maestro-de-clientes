package org.acme.curp.validarfc.controlador;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.curp.validarfc.modelo.ValidaRfcRequest;
import org.acme.curp.validarfc.modelo.ValidaRfcResponse;
import org.acme.curp.validarfc.servicio.NubariumApiClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Base64;

@Path("/rfc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ValidaRfcController {
    String username = "axa-guillermocamachodelapaz";
    String password = "kb_#7Y.9D";
    String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    ValidaRfcResponse validaRfcResponse;
    @Inject
    ValidaRfcController(ValidaRfcResponse validaRfcResponse){
        this.validaRfcResponse=validaRfcResponse;
    }

    @Inject
    @RestClient
    NubariumApiClient nubariumApiClient;

    @POST
    @Path("/validar-rfc")
    public String validaRfc(ValidaRfcRequest data){
        try{
            Response response = nubariumApiClient.validaRfc(authorizationHeader,data);
            ValidaRfcResponse validaRfcResponse = response.readEntity(ValidaRfcResponse.class);
            return "Estatus del RFC: "+validaRfcResponse.estatus+
                    "\nInformación adicional: "+validaRfcResponse.informacionAdicional;
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
