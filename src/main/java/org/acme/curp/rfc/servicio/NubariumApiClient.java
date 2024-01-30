package org.acme.curp.rfc.servicio;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.curp.rfc.modelo.ValidaRfcRequest;
import org.acme.curp.rfc.modelo.ValidaRfcResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://curp.nubarium.com")
public interface NubariumApiClient {
    @POST
    @Path("/sat/valida_rfc")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response validaRfc(
            @HeaderParam("Authorization") String authorizationHeader,
            ValidaRfcRequest datos
    );
}
