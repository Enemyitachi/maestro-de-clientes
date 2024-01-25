package org.acme.curp.obtienecurp.controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.curp.obtienecurp.modelo.ObtieneCurpResponse;

@Path("/curp")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObtieneCurpController {

    ObtieneCurpResponse obtieneCurpResponse;

    @Inject
    ObtieneCurpController(ObtieneCurpResponse obtieneCurpResponse){
        this.obtieneCurpResponse=obtieneCurpResponse;
    }

    @POST
    @Path("/obtiene-curp")
    public ObtieneCurpResponse obtieneCurp(String data) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);
        //Obtener la CURP dentro de toda la data

        String nombre = jsonNode.get("nombre").asText();
        String fechaNacimiento = jsonNode.get("fechaNacimiento").asText();
        String primerApellido = jsonNode.get("primerApellido").asText();
        String segundoApellido = jsonNode.get("segundoApellido").asText();
        String sexo = jsonNode.get("sexo").asText();
        String entidad = jsonNode.get("entidad").asText();

        String datos = "{" +
                "\"nombre\" : \""+nombre+"\"," +
                "\"primerApellido\" : \""+primerApellido+"\"," +
                "\"segundoApellido\" : \""+segundoApellido+"\"," +
                "\"fechaNacimiento\" : \""+fechaNacimiento+"\"," +
                "\"entidad\" : \""+entidad+"\"," +
                "\"sexo\" : \""+sexo+"\"" +
                "}";

        return obtieneCurpResponse.nubariumApi(datos);
    }
}
