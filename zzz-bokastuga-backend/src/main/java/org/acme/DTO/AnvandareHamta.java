package org.acme.DTO;

import org.acme.entities.Ordrar;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

public class AnvandareHamta
{
    @Schema(example = "", description = "Id",format = "Integer")
    public Integer id;
    @Schema(example = "", description = "Namn",format = "string")
    public String namn;
    @Schema(example = "", description = "Efternamn",format = "string")
    public String efternamn;
    @Schema(example = "", description = "email",format = "string")
    public String email;
    @Schema(example = "", description = "Telefonnummer",format = "string")
    public String telefonnummer;
    @Schema(example = "", description = "Password", format = "Hash")
    public String password;
    @Schema(example = "", description = "Adress",format = "string")
    public String adress;
    @Schema(example = "", description = "Postort",format = "string")
    public String postort;
    @Schema(example = "", description = "Postnummer",format = "string")
    public String postnummer;
    @Schema(example = "", description = "Grupp", format = "string")
    public String grupp;
    @Schema(example = "", description = "Ordrar", format = "Ordrar")
    public List<Ordrar> ordrar;
}
