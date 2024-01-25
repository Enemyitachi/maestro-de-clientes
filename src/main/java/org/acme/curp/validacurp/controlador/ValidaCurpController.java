package org.acme.curp.validacurp.controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.curp.validacurp.modelo.ValidaCurpResponse;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/curp")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ValidaCurpController {
    ValidaCurpResponse validaCurpResponse;

    @Inject
    ValidaCurpController(ValidaCurpResponse validaCurpResponse){
        this.validaCurpResponse=validaCurpResponse;
    }

    @POST
    @Path("/valida-curp")
    public ValidaCurpResponse validarCurp(String data) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(data);
            //Obtener la CURP dentro de toda la data
            String curp = jsonNode.get("curp").asText();
            String JSONCURP ="{\"curp\":\""+curp+"\"}";

        return validaCurpResponse.nubariumApi(JSONCURP);
    }
}
