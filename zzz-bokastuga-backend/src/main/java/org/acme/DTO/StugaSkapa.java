package org.acme.DTO;

import org.acme.entities.Ordrar;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class StugaSkapa
{
    @Schema(example = "", description = "namn", format = "string")
    public String namn;
    @Schema(example = "",description = "Beskrivning", format = "string")
    public String beskrivning;
    @Schema(example = "",description = "Bild", format = "string")
    public String bild;
    @Schema(example = "",description = "Pris", format = "int")
    public int pris;
}
