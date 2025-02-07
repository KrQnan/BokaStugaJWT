package org.acme.DTO;

import org.acme.entities.Anvandare;
import org.acme.entities.Stuga;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;
import java.sql.Date;

public class OrdrarSkapa
{
    @Schema(example = "",description = "KundId", format = "Anvandare")
    public Integer kundId;
    @Schema(example = "",description = "StugId", format = "Stuga")
    public Integer stugId;
    @Schema(example = "", description = "BokningFrom", format = "string")
    public Date bokningFrom;
    @Schema(example = "",description = "BokningTom", format = "string")
    public Date bokningTom;
}
