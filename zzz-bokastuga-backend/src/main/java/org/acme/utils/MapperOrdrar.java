package org.acme.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.DTO.OrdrarSkapa;
import org.acme.entities.Ordrar;
import org.acme.DTO.OrdrarHamta;
import org.acme.quarkus.repository.AnvandareRepository;
import org.acme.quarkus.repository.OrdrarRepository;
import org.acme.quarkus.repository.StugRepository;

@ApplicationScoped
public class MapperOrdrar
{
    @Inject
    AnvandareRepository anvandareRepository;
    @Inject
    StugRepository stugRepository;

    public Ordrar tillOrdrarEntities(OrdrarSkapa ordrarSkapa)
    {
        Ordrar ordrar = new Ordrar();
        ordrar.setKundId(anvandareRepository.findById(ordrarSkapa.kundId).orElse(null));
        ordrar.setStugId(stugRepository.findById(ordrarSkapa.stugId).orElse(null));
        ordrar.setBokningFrom(ordrarSkapa.bokningFrom);
        ordrar.setBokningTom(ordrarSkapa.bokningTom);
        return ordrar;
    }

    public OrdrarHamta SvarOrdrarSkapa(Ordrar entity)
    {
        if (entity == null)
        {
            return null;
        }
        else
        {
            OrdrarHamta hamta = new OrdrarHamta();
            hamta.id = entity.getId();
            hamta.kundId = entity.getKundId();
            hamta.stugId = entity.getStugId();
            hamta.bokningFrom = entity.getBokningFrom();
            hamta.bokningTom = entity.getBokningTom();
            return hamta;
        }
    }
}
