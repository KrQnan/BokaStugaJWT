package org.acme.quarkus.controller;

import com.google.errorprone.annotations.InlineMe;
import io.smallrye.graphql.api.Scalar;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.DTO.StugaHamta;
import org.acme.DTO.StugaSkapa;
import org.acme.entities.Stuga;
import org.acme.quarkus.repository.StugRepository;
import org.acme.quarkus.service.StugService;
import org.acme.utils.MapperStuga;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@APIResponses(value = {@APIResponse(responseCode = "200", description = "Sucess")})
@Path("/Stugor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT"
)
public class StugController
{
    @Inject
    StugRepository stugRepository;
    @Inject
    StugService stugService;
    @Inject
    MapperStuga mapperStuga;

    @GET
    @Path("{Id},{from},{tom}")
    public Response getAllAvailableStuga(@PathParam("Id") Integer id, @PathParam("from") Date from, @PathParam("tom") Date tom)
    {
        return stugService.AvailableStuga(from,tom,id);
    }

    @GET
    public Response getAllStuga()
    {
        return Response.status(200).entity(stugRepository.findAll().stream().map(mapperStuga::svarStugaSkapa).toList()).build();
    }

    @APIResponses(value = {@APIResponse(responseCode = "204", description = "Hitta kund med id")})
    @GET
    @Path("{id}")
    public Response getStugaById(@PathParam("id") Integer id)
    {
        StugaHamta hamta = mapperStuga.svarStugaSkapa(stugRepository.findById(id).orElse(null));
        if (hamta == null)
        {
            return Response.status(204).build();
        }
        else
        {
            return Response.ok(hamta).build();
        }
    }

    @APIResponses(value = {@APIResponse(responseCode = "201", description = "Stuga skapad")})
    @POST
    @Transactional
    @Transient
    @RolesAllowed("Admin")
    public Response createStuga(StugaSkapa stuga)
    {
        return Response.status(201).entity(stugService.skapaStuga(stuga)).build();
    }

    @APIResponses(value = {@APIResponse(responseCode = "201", description = "Stuga uppdaterad")})
    @Transactional
    @PUT
    @Path("{id}")
    @RolesAllowed("Admin")
    public Response updateStuga(@PathParam("id") Integer id, StugaSkapa stuga)
    {
        stugService.uppdateraStuga(id, stuga);
        return Response.status(200).entity(stuga).build();
    }

    @APIResponses(value = {@APIResponse(responseCode = "204", description = "Stuga borttagen")})
    @DELETE
    @Path("{id}")
    @Transactional
    @RolesAllowed("Admin")
    public Response deleteStuga(@PathParam("id") Integer id)
    {
        stugRepository.deleteById(id);
        return Response.status(204).build();
    }
}
