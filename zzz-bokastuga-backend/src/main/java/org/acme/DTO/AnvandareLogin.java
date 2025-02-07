package org.acme.DTO;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class AnvandareLogin
{
    @Schema(example = "", description = "Email",format = "string")
    public String email;
    @Schema(example = "", description = "Password", format = "string")
    public String password;

}
