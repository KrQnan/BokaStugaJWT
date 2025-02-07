package org.acme.utils;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.DTO.AnvandareHamta;
import org.acme.DTO.AnvandareSkapa;
import org.acme.entities.Anvandare;
import org.acme.quarkus.repository.AnvandareRepository;
import org.acme.quarkus.repository.OrdrarRepository;

import java.util.Collections;

@ApplicationScoped
public class MapperAnvandare
{
    @Inject
    AnvandareRepository anvandareRepository;
    @Inject
    OrdrarRepository ordrarRepository;

    public Anvandare tillkundEntities(AnvandareSkapa anvandareSkapa)
    {
        Anvandare anvandare = new Anvandare();
        anvandare.setNamn(anvandareSkapa.namn);
        anvandare.setEfternamn(anvandareSkapa.efternamn);
        anvandare.setEmail(anvandareSkapa.email);
        anvandare.setTelefonnummer(anvandareSkapa.telefonnummer);
        anvandare.setAdress(anvandareSkapa.adress);
        anvandare.setPostort(anvandareSkapa.postort);
        anvandare.setPostnummer(anvandareSkapa.postnummer);
        anvandare.setGrupp(anvandareSkapa.grupp);
        anvandare.setPassword(BcryptUtil.bcryptHash((anvandareSkapa.password)));
        return anvandare;
    }

    public AnvandareHamta SvarkundSkapa(Anvandare entity)
    {
        if(entity == null){
            return null;
        }
        else {
            AnvandareHamta anvandareHamta = new AnvandareHamta();
            anvandareHamta.id = entity.getId();
            anvandareHamta.namn = entity.getNamn();
            anvandareHamta.efternamn = entity.getEfternamn();
            anvandareHamta.email = entity.getEmail();
            anvandareHamta.telefonnummer = entity.getTelefonnummer();
            anvandareHamta.adress = entity.getAdress();
            anvandareHamta.postort = entity.getPostort();
            anvandareHamta.postnummer = entity.getPostnummer();
            anvandareHamta.grupp = entity.getGrupp();
            anvandareHamta.password = entity.getPassword();
            anvandareHamta.ordrar = ordrarRepository.findAllByKundId_Id(entity.getId());
            return anvandareHamta;
        }

    }
}
