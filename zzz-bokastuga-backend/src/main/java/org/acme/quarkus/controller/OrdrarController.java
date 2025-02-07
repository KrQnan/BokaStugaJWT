package org.acme.quarkus.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.DTO.OrdrarSkapa;
import org.acme.DTO.OrdrarHamta;
import org.acme.quarkus.repository.OrdrarRepository;
import org.acme.quarkus.service.OrdrarService;
import org.acme.utils.MapperOrdrar;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import java.util.List;

@APIResponses(value = {@APIResponse(responseCode = "200", description = "Sucess")})
@Path("/Ordrar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT"
)
public class OrdrarController
{
    @Inject
    OrdrarRepository ordrarRepository;
    @Inject
    OrdrarService ordrarService;
    @Inject
    MapperOrdrar mapperOrdrar;

    @GET
    @RolesAllowed({"Admin","User"})
    public List<OrdrarHamta> getOrdrar(){
        return ordrarRepository.findAll().stream().map(mapperOrdrar::SvarOrdrarSkapa).toList();
    }

    @APIResponses(value = {@APIResponse(responseCode = "204", description = "Not found on ID")})
    @GET
    @Path("{ID}")
    @RolesAllowed({"Admin","User"})
    public Response getOrdrarById(@PathParam("ID") Integer ID)
    {
        OrdrarHamta hamta = mapperOrdrar.SvarOrdrarSkapa(ordrarRepository.findById(ID).orElse(null));
        if(hamta == null)
        {
            return Response.status(204).build();
        }
        else {
            return Response.ok(hamta).build();
        }
    }

    @APIResponses(value = {@APIResponse(responseCode = "201", description = "Order skapad")})
    @POST
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Response createOrder(OrdrarSkapa order)
    {
        return Response.status(201).entity(ordrarService.skapa(order)).build();
    }

    @APIResponses(value = {@APIResponse(responseCode = "201", description = "Order uppdaterad")})
    @Transactional
    @PUT
    @Path("{ID}")
    @RolesAllowed("Admin")
    public Response updateOrder(@PathParam("ID") Integer ID, OrdrarSkapa order)
    {
        ordrarService.uppdateraOrder(ID,order);
        return Response.status(201).entity(order).build();
    }

    @APIResponses(value = {@APIResponse(responseCode = "204", description = "Order borttagen")})
    @DELETE
    @Path("{ID}")
    @Transactional
    @RolesAllowed("Admin")
    public Response deleteOrder(@PathParam("ID") Integer ID){
        ordrarRepository.deleteById(ID);
        return Response.status(204).build();
    }
}
