package org.acme.quarkus.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.acme.DTO.AnvandareHamta;
import org.acme.DTO.AnvandareSkapa;
import org.acme.entities.Anvandare;
import org.acme.quarkus.repository.AnvandareRepository;
import org.acme.quarkus.service.KundService;
import org.acme.quarkus.service.TokenService;
import org.acme.utils.MapperAnvandare;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import java.util.List;

@APIResponses(value = {@APIResponse(responseCode = "200", description = "Sucess")})
@Path("/Kund")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KundController
{
    @Inject
    AnvandareRepository anvandareRepository;
    @Inject
    KundService kundService;
    @Inject
    MapperAnvandare mapperAnvandare;
    @Inject
    TokenService tokenService;

    @GET
    public List<AnvandareHamta> getAllKund(@Context SecurityContext sc)
    {
        return anvandareRepository.findAll().stream().map(mapperAnvandare::SvarkundSkapa).toList();
    }

    @APIResponses(value = {@APIResponse(responseCode = "204", description = "Not found on UUID")})
    @GET
    @Path("{kundId}")
    @RolesAllowed("Admin")
    public Response getKundById(@PathParam("kundId") Integer kundId)
    {
        AnvandareHamta hamta = mapperAnvandare.SvarkundSkapa(anvandareRepository.findById(kundId).orElse(null));
        if (hamta == null)
        {
            return Response.status(204).build();
        }
        else
        {
            return Response.ok(hamta).build();
        }
    }
    @APIResponses(value = {@APIResponse(responseCode = "201", description = "Kund skapad")})
    @POST
    @Transactional
    @Transient
    @RolesAllowed("Admin")
    public Response createKund(AnvandareSkapa kund)
    {
        return Response.status(201).entity(kundService.skapaKund(kund)).build();
    }

    @RolesAllowed({"User","Admin"})
    @APIResponses(value = {@APIResponse(responseCode = "201", description = "Kund ändrad")})
    @Transactional
    @PUT
    @Path("{kundId}")
    public Response updateKund(@PathParam("kundId") Integer kundId, AnvandareSkapa kundToSave)
    {
        kundService.uppdateraKund(kundId, kundToSave);
        return Response.status(200).entity(kundToSave).build();
    }

    @APIResponses(value = {@APIResponse(responseCode = "204", description = "Kund borttagen")})
    @DELETE
    @Path("{kundId}")
    @Transactional
    @RolesAllowed("Admin")
    public Response deleteKund(@PathParam("kundId") Integer kundId){
        anvandareRepository.deleteById(kundId);
        return Response.status(204).build();
    }
}
