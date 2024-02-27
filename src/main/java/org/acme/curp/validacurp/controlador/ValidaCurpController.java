package org.acme.curp.validacurp.controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.curp.validacurp.modelo.ValidaCurpRequest;
import org.acme.curp.validacurp.modelo.ValidaCurpResponse;

import org.acme.curp.validacurp.servicio.NubariumApiClient;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Base64;

@Path("/curp")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ValidaCurpController {
    //Encontrar una manera de tener una lista de Strings constantes
    String username = "axa-guillermocamachodelapaz";
    String password = "kb_#7Y.9D";
    String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    ValidaCurpResponse validaCurpResponse;
    @Inject
    ValidaCurpController(ValidaCurpResponse validaCurpResponse){
        this.validaCurpResponse=validaCurpResponse;
    }

    @Inject
    @RestClient
    NubariumApiClient nubariumApiClient;

    @POST
    @Path("/valida-curp")
    public Response validarCurp(ValidaCurpRequest data){
        //Comienza Flujo de validacion
        //Se revisa la estructura de la curp recibida
        // Regex para validar la CURP
        String regex = "^[A-Z]{4}[0-9]{6}[H,M][A-Z]{5}[A-Z0-9]{1}[0-9]{1}$";
        // Comprobar si la CURP coincide con el patrón del regex
        if (!data.getCurp().matches(regex)){
            return Response.ok("Curp no valida - revisar la estructura").build();
        }else {
            //Se revisa si la CURP ya esta dentro de nuestra base de datos
            if (false){
                //Este es el caso de que si se encuentre en la base
                return Response.ok("Curp Valida - Almacenada en base de datos").build();
            }else{
                //En cAso de que no, comienza flujo de Validacion en Data Power
                try {
                    Response response = nubariumApiClient.validaCurp(authorizationHeader,data);
                    if (response.getStatus() ==200){
                        //proceso de guardar en base de datos
                    }
                    return Response.ok("CURP valida - Con nubarium: "+response.readEntity(ValidaCurpResponse.class).curp+"\nEstatus de Curp: "+ response.readEntity(ValidaCurpResponse.class).estatus).build();
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
    }
}