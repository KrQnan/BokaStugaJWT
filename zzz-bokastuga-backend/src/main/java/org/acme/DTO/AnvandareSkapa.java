package org.acme.DTO;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class AnvandareSkapa
{
    @Schema(example = "", description = "Namn",format = "string")
    public String namn;
    @Schema(example = "", description = "Efternamn",format = "string")
    public String efternamn;
    @Schema(example = "", description = "Email",format = "string")
    public String email;
    @Schema(example = "", description = "Telefonnummer",format = "string")
    public String telefonnummer;
    @Schema(example = "", description = "Adress",format = "string")
    public String adress;
    @Schema(example = "", description = "Postort",format = "string")
    public String postort;
    @Schema(example = "", description = "Postnummer",format = "string")
    public String postnummer;
    @Schema(example = "", description = "Password", format = "string")
    public String password;
    @Schema(example = "", description = "Grupp", format = "string")
    public String grupp;

}
