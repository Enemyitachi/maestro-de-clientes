package org.acme.curp.obtienecurp.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.curp.obtienecurp.modelo.ObtieneCurpRequest;
import org.acme.curp.obtienecurp.modelo.ObtieneCurpResponse;
import org.acme.curp.validacurp.modelo.ValidaCurpResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://curp.nubarium.com")
public interface NubariumApiClient {

    @POST
    @Path("/renapo/obtener_curp")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response obtieneCurp(
            @HeaderParam("Authorization") String authorizationHeader,
            ObtieneCurpRequest datos
    );
}
