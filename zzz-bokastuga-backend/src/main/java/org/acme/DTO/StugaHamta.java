package org.acme.DTO;

import jakarta.inject.Inject;
import org.acme.entities.Ordrar;
import org.acme.quarkus.repository.OrdrarRepository;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

public class StugaHamta
{
    @Schema(example = "", description = "Id", format = "Integer")
    public Integer id;
    @Schema(example = "", description = "namn", format = "string")
    public String namn;
    @Schema(example = "",description = "Beskrivning", format = "string")
    public String beskrivning;
    @Schema(example = "",description = "Pris", format = "int")
    public int pris;
    @Schema(example = "",description = "Bild", format = "string")
    public String bild;
    @Schema(example = "", description = "Ordrar", format = "Ordrar")
    public List<OrdrarHamtaStuga> ordrar;
}
