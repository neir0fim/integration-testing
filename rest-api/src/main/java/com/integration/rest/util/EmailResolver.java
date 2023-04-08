package com.integration.rest.util;

import com.integration.util.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class EmailResolver {
    public String getUserEmail(Authentication authentication) {
        var principal = authentication.getPrincipal();
        if (principal instanceof Jwt jwt) {
            var email = (String) jwt.getClaims().get("email");

            if (isNull(email) || email.isBlank()) {
                throw new UnauthorizedException("User not authorized");
            }

            return email;
        }

        throw new UnauthorizedException("Unable to resolve authentication as user object");
    }
}
