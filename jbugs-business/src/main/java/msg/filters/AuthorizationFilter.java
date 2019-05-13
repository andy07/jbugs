package msg.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import sun.misc.InvalidJarIndexException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class AuthorizationFilter implements ContainerRequestFilter {

    private class Authorization implements SecurityContext{
        private List<String> roles;
        private String username;

        public Authorization(List<String> roles, String username) {
            this.roles = roles;
            this.username = username;
        }

        @Override
        public Principal getUserPrincipal() {
            return new Principal() {
                @Override
                public String getName() {
                    return username;
                }
            };
        }

        @Override
        public boolean isUserInRole(String s) {
            return roles.contains(s);
        }

        @Override
        public boolean isSecure() {
            return true;
        }

        @Override
        public String getAuthenticationScheme() {
            return null;
        }
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authorizationValue = containerRequestContext.getHeaderString("Authorization");
        if(authorizationValue.startsWith("Bearer")){
            Algorithm algorithm = Algorithm.HMAC256("caramele");
            JWTVerifier jwtVerifier= JWT.require(algorithm).withIssuer("auth0").build();

            DecodedJWT decodeToken = jwtVerifier.verify(authorizationValue.split(" ")[1]);
            if (isValidToken(decodeToken)){
                List<String> roles = decodeToken.getClaim("roles").asList(String.class);
                String username = decodeToken.getClaim("username").asString();
                containerRequestContext.setSecurityContext(new Authorization(roles,username));

            }

        }
    }

    private boolean isValidToken(DecodedJWT token) {
        return true;
    }
}
