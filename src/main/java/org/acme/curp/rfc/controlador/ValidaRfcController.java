package org.acme.curp.rfc.controlador;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import org.acme.curp.rfc.modelo.ValidaRfcRequest;
import org.acme.curp.rfc.modelo.ValidaRfcResponse;

@Path("/rfc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ValidaRfcController {
    ValidaRfcResponse validaRfcResponse;

    @Inject
    ValidaRfcController(ValidaRfcResponse validaRfcResponse){
        this.validaRfcResponse=validaRfcResponse;
    }

    @POST
    @Path("/validar-rfc")
    public String obtieneRfc(ValidaRfcRequest rfc){
        Response response = validaRfcResponse.nubariumApi(rfc);
        if (response.getStatus() != 200){
            return "Servicio de Nubarium abajo";
        }else{
            return validaRfcResponse.nubariumApi(rfc).readEntity(String.class);
        }
    }
}
