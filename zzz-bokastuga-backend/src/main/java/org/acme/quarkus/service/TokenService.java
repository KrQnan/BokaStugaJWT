package org.acme.quarkus.service;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenService
{
    public String generateAdminToken(String email, String password)
    {
        return Jwt.issuer("jwt-token").preferredUserName(email).subject("jwt-token").groups("Admin").expiresAt(System.currentTimeMillis() + 3600).sign();
    }

    public String generateModeratorToken(String email, String password)
    {
        return Jwt.issuer("jwt-token").preferredUserName(email).subject("jwt-token").groups("Moderator").expiresAt(System.currentTimeMillis() + 3600).sign();
    }

    public String generateUserToken(String email, String password)
    {
        return Jwt.issuer("jwt-token").preferredUserName(email).subject("jwt-token").groups("User").expiresAt(System.currentTimeMillis() + 3600).sign();
    }

    public String generateVisitorToken(String email, String password)
    {
        return Jwt.issuer("jwt-token").preferredUserName(email).subject("jwt-token").groups("Visitor").expiresAt(System.currentTimeMillis() + 3600).sign();
    }
}
