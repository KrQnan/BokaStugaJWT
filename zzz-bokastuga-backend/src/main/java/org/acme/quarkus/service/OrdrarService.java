package org.acme.quarkus.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.acme.DTO.OrdrarHamtaStuga;
import org.acme.DTO.OrdrarSkapa;
import org.acme.entities.Ordrar;
import org.acme.quarkus.repository.AnvandareRepository;
import org.acme.quarkus.repository.OrdrarRepository;
import org.acme.quarkus.repository.StugRepository;
import org.acme.utils.MapperOrdrar;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrdrarService
{
    @Inject
    AnvandareRepository anvandareRepository;
    @Inject
    StugRepository stugRepository;
    @Inject
    OrdrarRepository ordrarRepository;
    @Inject
    MapperOrdrar mapperOrdrar;

    public OrdrarSkapa skapa(OrdrarSkapa ordrar){
        ordrarRepository.save(mapperOrdrar.tillOrdrarEntities(ordrar));
        return ordrar;
    }

    public OrdrarSkapa uppdateraOrder(Integer ID, OrdrarSkapa ordrarToSave)
    {
        Ordrar ordrar = ordrarRepository.findById(ID).orElse(null);
        if(ordrar == null){
            throw new WebApplicationException(Response.status(500).entity("Kunde inte hitta en order").build());
        }
        ordrar.setKundId(anvandareRepository.findById(ordrarToSave.kundId).orElse(null));
        ordrar.setStugId(stugRepository.findById(ordrarToSave.stugId).orElse(null));
        ordrar.setBokningFrom(ordrarToSave.bokningFrom);
        ordrar.setBokningTom(ordrarToSave.bokningTom);
        ordrarRepository.save(ordrar);
        return ordrarToSave;
    }

    public List<OrdrarHamtaStuga> getOrdrarStuga(Integer ID)
    {
        List<Ordrar> ordrar = ordrarRepository.findAllByStugId_Id(ID);
        return ordrar.stream()
                .map(order -> new OrdrarHamtaStuga(
                        order.id,
                        order.kundId,
                        order.getStugId().getId(),
                        order.getBokningFrom(),
                        order.getBokningTom()
                        ))
                .collect(Collectors.toList());
    }
}
