package org.acme.quarkus.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.acme.DTO.OrdrarHamta;
import org.acme.DTO.StugaHamta;
import org.acme.DTO.StugaSkapa;
import org.acme.entities.Ordrar;
import org.acme.entities.Stuga;
import org.acme.quarkus.repository.AnvandareRepository;
import org.acme.quarkus.repository.StugRepository;
import org.acme.utils.MapperStuga;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class StugService
{
    @Inject
    AnvandareRepository anvandareRepository;
    @Inject
    StugRepository stugRepository;
    @Inject
    MapperStuga mapperStuga;

    public StugaSkapa skapaStuga(StugaSkapa stuga){
        stugRepository.save(mapperStuga.tillStugaEntities(stuga));
        return stuga;
    }

    public StugaSkapa uppdateraStuga(Integer stugaId, StugaSkapa stugaToSave)
    {
        Stuga stuga = stugRepository.findById(stugaId).orElse(null);
        if(stuga == null){
            throw new WebApplicationException(Response.status(500).entity("Kunde inte hitta en stuga på detta id").build());
        }
        stuga.setName(stugaToSave.namn);
        stuga.setBeskrivning(stugaToSave.beskrivning);
        stuga.setPris(stugaToSave.pris);
        stuga.setBild(stugaToSave.bild);
        stugRepository.save(stuga);
        return stugaToSave;
    }

    public Response AvailableStuga(Date from, Date tom,Integer Id){

        if(stugRepository.findById(Id).get().getStugId() != null){
            for (Ordrar ordrar : stugRepository.findById(Id).get().getStugId())
            {
                if (!ordrar.getBokningFrom().before(tom) && from.before(ordrar.getBokningTom()))
                {
                    return Response.status(200).entity("Stugan är ledig").build();
                }
                if (!ordrar.getBokningFrom().after(tom) && from.after(ordrar.getBokningTom()))
                {
                    return Response.status(200).entity("Stugan är ledig").build();
                }
                else{
                    throw new WebApplicationException(Response.status(400).entity("Stugan är bokad").build());
                }
            }
        }
        else{
            throw new WebApplicationException(Response.status(400).entity("Finns ingen stuga på detta id").build());
        }
        return Response.status(200).entity("Stugan är ledig").build();
    }
}

