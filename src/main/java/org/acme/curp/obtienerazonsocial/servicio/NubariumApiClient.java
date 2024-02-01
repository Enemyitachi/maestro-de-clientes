package org.acme.curp.obtienerazonsocial.servicio;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.curp.obtienerazonsocial.modelo.ObtieneRazonSocialRequest;
import org.acme.curp.validarfc.modelo.ValidaRfcRequest;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://curp.nubarium.com")
public interface NubariumApiClient {
    @POST
    @Path("/sat/v1/obtener-razonsocial")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response obtieneRazonSocial(
            @HeaderParam("Authorization") String authorizationHeader,
            ObtieneRazonSocialRequest datos
    );
}
