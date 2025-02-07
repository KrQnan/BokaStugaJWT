package org.acme.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.DTO.StugaHamta;
import org.acme.DTO.StugaSkapa;
import org.acme.entities.Stuga;
import org.acme.quarkus.repository.OrdrarRepository;
import org.acme.quarkus.service.OrdrarService;

@ApplicationScoped
public class MapperStuga
{
    @Inject
    OrdrarRepository ordarRepository;
    @Inject
    OrdrarService ordarService;



    public Stuga tillStugaEntities(StugaSkapa stugaSkapa){
        Stuga stuga = new Stuga();
        stuga.setName(stugaSkapa.namn);
        stuga.setBeskrivning(stugaSkapa.beskrivning);
        stuga.setPris(stugaSkapa.pris);
        stuga.setBild(stugaSkapa.bild);
        return stuga;
    }

    public StugaHamta svarStugaSkapa(Stuga entity)
    {
        if(entity == null){
            return null;
        }
        else{
            StugaHamta hamta = new StugaHamta();
            hamta.id = entity.getId();
            hamta.namn = entity.getName();
            hamta.beskrivning = entity.getBeskrivning();
            hamta.pris = entity.getPris();
            hamta.bild = entity.getBild();
            hamta.ordrar = ordarService.getOrdrarStuga(entity.getId());
            return hamta;
        }
    }


}
