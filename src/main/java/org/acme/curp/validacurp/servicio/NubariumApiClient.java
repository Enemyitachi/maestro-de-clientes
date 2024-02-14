package org.acme.curp.validacurp.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.curp.validacurp.modelo.ValidaCurpRequest;
import org.acme.curp.validacurp.modelo.ValidaCurpResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://curp.nubarium.com")
public interface NubariumApiClient {

    @POST
    @Path("/renapo/v3/valida_curp")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response validaCurp(
            @HeaderParam("Authorization") String authorizationHeader,
            ValidaCurpRequest datos
    );
}
