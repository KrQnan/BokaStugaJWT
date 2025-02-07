package org.acme.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acme.entities.Anvandare;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;
import java.sql.Date;

@Getter
@Setter
public class OrdrarHamtaStuga
{
    @Schema(example = "", description = "Id", format = "Integer")
    public Integer id;
    @Schema(example = "", description = "KundId", format = "Anvandare")
    public Anvandare kundId;
    @Schema(example = "", description = "StugId", format = "Stuga")
    public Integer stugId;
    @Schema(example = "", description = "BokningFrom", format = "string")
    public Date bokningFrom;
    @Schema(example = "", description = "BokningTom", format = "string")
    public Date bokningTom;

    public OrdrarHamtaStuga() {
    }

    public OrdrarHamtaStuga(Integer id, Anvandare kundId, Integer stugId, Date bokningFrom, Date bokningTom) {
        this.id = id;
        this.kundId = kundId;
        this.stugId = stugId;
        this.bokningFrom = bokningFrom;
        this.bokningTom = bokningTom;
    }
}
