package org.acme.curp.validaCamposCurp.controlador;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.curp.validacurp.modelo.ValidaCurpRequest;
import org.acme.curp.validacurp.modelo.ValidaCurpResponse;
import org.acme.curp.validacurp.servicio.NubariumApiClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Base64;

@Path("/campos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ValidaCamposCurpController {
    //Encontrar una manera de tener una lista de Strings constantes
    String username = "axa-guillermocamachodelapaz";
    String password = "kb_#7Y.9D";
    String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    ValidaCurpResponse validaCurpResponse;
    @Inject
    ValidaCamposCurpController(ValidaCurpResponse validaCurpResponse){
        this.validaCurpResponse=validaCurpResponse;
    }

    @Inject
    @RestClient
    NubariumApiClient nubariumApiClient;

    @POST
    @Path("/valida-campos")
    public Response validarCurp(ValidaCurpRequest data){
                try {
                    Response response = nubariumApiClient.validaCurp(authorizationHeader,data);
                    ValidaCurpResponse responseBody= response.readEntity(ValidaCurpResponse.class);
                    boolean nombreCoincide = responseBody.nombre.equals(data.getNombre());
                    boolean apellidoPaternoCoincide = responseBody.apellidoPaterno.equals(data.getApellidoPaterno());
                    boolean apellidoMaternoCoincide = responseBody.apellidoMaterno.equals(data.getApellidoMaterno());
                    boolean fechaNacimientoCoincide = responseBody.fechaNacimiento.equals(data.getFechaNacimiento());
                    boolean curpCoincide = responseBody.curp.equals(data.getCurp());

                    if (nombreCoincide && apellidoPaternoCoincide && apellidoMaternoCoincide && fechaNacimientoCoincide && curpCoincide) {
                        return Response.ok("Nombre Numbarium: "+responseBody.nombre+
                                "\t\t\tNombre Brindado: "+ data.getNombre() +
                                "\nApellido Paterno Nubarium: "+responseBody.apellidoPaterno +
                                "\t\t\t\tApellido Paterno Brindado: "+ data.getApellidoPaterno() +
                                "\nApellido Materno Nubarium: "+responseBody.apellidoMaterno +
                                "\t\t\tApellido Materno Brindado: "+ data.getApellidoMaterno() +
                                "\nFecha Nacimiento Nubarium: "+responseBody.fechaNacimiento +
                                "\t\tFecha Nacimiento Brindado: "+ data.getFechaNacimiento() +
                                "\nCURP Nubarium: "+responseBody.curp +
                                "\t\t\tCURP Brindado: "+ data.getCurp()
                        ).build();
                    } else {
                        return Response.ok("ALGUN DATO NO COINCIDE REVISE LA INFORMACION"+
                                "\nNombre Numbarium: "+responseBody.nombre+
                                "\t\t\tNombre Brindado: "+ data.getNombre() +
                                "\nApellido Paterno Nubarium: "+responseBody.apellidoPaterno +
                                "\t\t\t\tApellido Paterno Brindado: "+ data.getApellidoPaterno() +
                                "\nApellido Materno Nubarium: "+responseBody.apellidoMaterno +
                                "\t\t\tApellido Materno Brindado: "+ data.getApellidoMaterno() +
                                "\nFecha Nacimiento Nubarium: "+responseBody.fechaNacimiento +
                                "\t\tFecha Nacimiento Brindado: "+ data.getFechaNacimiento() +
                                "\nCURP Nubarium: "+responseBody.curp +
                                "\t\t\tCURP Brindado: "+ data.getCurp()
                        ).status(400).build();
                    }
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