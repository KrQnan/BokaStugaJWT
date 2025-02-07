package org.acme.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acme.entities.Anvandare;
import org.acme.entities.Stuga;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrdrarHamta
{
    @Schema(example = "", description = "Id", format = "Integer")
    public Integer id;
    @Schema(example = "", description = "KundId", format = "Anvandare")
    public Anvandare kundId;
    @Schema(example = "", description = "StugId", format = "Stuga")
    public Stuga stugId;
    @Schema(example = "", description = "BokningFrom", format = "string")
    public Date bokningFrom;
    @Schema(example = "", description = "BokningTom", format = "string")
    public Date bokningTom;

}

