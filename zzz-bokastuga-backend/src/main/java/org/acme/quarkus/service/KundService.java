package org.acme.quarkus.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.acme.DTO.AnvandareLogin;
import org.acme.DTO.AnvandareSkapa;
import org.acme.entities.Anvandare;
import org.acme.quarkus.repository.AnvandareRepository;
import org.acme.utils.MapperAnvandare;

@ApplicationScoped
public class KundService
{
    @Inject
    AnvandareRepository anvandareRepository;
    @Inject
    MapperAnvandare mapperAnvandare;


    public AnvandareSkapa skapaKund(AnvandareSkapa kund)
    {
        anvandareRepository.save(mapperAnvandare.tillkundEntities(kund));
        return kund;
    }

    public AnvandareSkapa uppdateraKund(Integer kundId, AnvandareSkapa kundToSave)
    {
        Anvandare anvandare = anvandareRepository.findById(kundId).orElse(null);
        if(anvandare == null){
            throw new WebApplicationException(Response.status(500).entity("Kunde inte hitta en kund").build());
        }
        anvandare.setNamn(kundToSave.namn);
        anvandare.setEfternamn(kundToSave.efternamn);
        anvandare.setEmail(kundToSave.email);
        anvandare.setTelefonnummer(kundToSave.telefonnummer);
        anvandare.setAdress(kundToSave.adress);
        anvandare.setPostort(kundToSave.postort);
        anvandare.setPostnummer(kundToSave.postnummer);
        anvandareRepository.save(anvandare);
        return kundToSave;
    }
}
