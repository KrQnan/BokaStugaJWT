package org.acme.quarkus.controller;

import io.quarkus.elytron.security.common.BcryptUtil;
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
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

@APIResponses(value = {@APIResponse(responseCode = "200", description = "Sucess")})
@Path("/Anvandare")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController
{

    @Inject
    KundService kundService;

    @Inject
    MapperAnvandare mapperAnvandare;

    @Inject
    AnvandareRepository anvandareRepository;

    @Inject
    TokenService tokenService;

    @Context
    SecurityContext securityContext;

    @APIResponses(value = {@APIResponse(responseCode = "201", description = "Kund skapad")})
    @POST
    @Path("/register")
    @Transactional
    @Transient
    public Response register(AnvandareSkapa kund)
    {
        return Response.status(201).entity(kundService.skapaKund(kund)).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/login")
    public Response login(@QueryParam("email") String email, @QueryParam("password") String password) throws InvalidKeySpecException
    {
        Anvandare existerandeAnvandare = anvandareRepository.findByEmail(email);
        if (existerandeAnvandare == null || !BcryptUtil.matches(password, existerandeAnvandare.getPassword()))
        {
            throw new WebApplicationException(Response.status(404).entity(("Ingen användare på denna mail")).build());
        }
        if (existerandeAnvandare.getGrupp().equals("Admin")){
            return Response.ok(tokenService.generateAdminToken(existerandeAnvandare.getEmail(),password)).build();
        }
        if(existerandeAnvandare.getGrupp().equals("User")){
            return Response.ok(tokenService.generateUserToken(existerandeAnvandare.getEmail(), password)).build();
        }
        return Response.noContent().build();
    }
    @GET
    @Path("/admin")
    public Response loginAdmin(@QueryParam("email") String email, @QueryParam("password") String password){
        Anvandare existerandeAnvandare = anvandareRepository.findByEmailAndPassword(email,password);
        if(existerandeAnvandare == null || !existerandeAnvandare.getPassword().equals(password)) {
            throw new WebApplicationException(Response.status(404).entity(("Ingen användare på denna mail")).build());
        }
        if(!Objects.equals(existerandeAnvandare.getGrupp(), "Admin")){
            throw new WebApplicationException(Response.status(404).entity("Ej behörig").build());
        }
        return Response.ok(tokenService.generateAdminToken(existerandeAnvandare.getEmail(),password), existerandeAnvandare.getEmail()).build();
    }

    @GET
    @Path("/user")
    @RolesAllowed({"User","Admin"})
    public Response getUser()
    {
        AnvandareHamta hamta = mapperAnvandare.SvarkundSkapa(anvandareRepository.findByEmail(securityContext.getUserPrincipal().getName()));
        if(hamta == null){
            return Response.status(404).build();
        }
        else{
            return Response.ok(hamta).build();
        }
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
}


