package msg.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Jwts;
import sun.misc.InvalidJarIndexException;

import javax.annotation.Priority;
import javax.crypto.SecretKey;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;
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
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthorizationFilter implements ContainerRequestFilter {
    private static final String AUTHENTICATION_HEADER = "Authorization";
    private static final String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
    private static final String LOGIN_PATH = "users/login";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authorizationValue = containerRequestContext.getHeaderString(AUTHENTICATION_HEADER);
        UriInfo info = containerRequestContext.getUriInfo();
        if(info.getPath().equals(LOGIN_PATH)){
            return;
        }
        else if(authorizationValue != null && authorizationValue.length()>0 && authorizationValue.startsWith("Bearer"))  {
                String token = authorizationValue.split(" ")[1];
                //This line will throw an exception if it is not a signed JWS (as expected)
            try {
                Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                        .parseClaimsJws(token);
                return;
            }catch (Exception e){
                Response unauthorizedAccess = Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Mr.(Ms.) hacker, please go back to sleep! Stark TEAM are watching you!").build();
                containerRequestContext.abortWith(unauthorizedAccess);
            }

        }
        else{
            Response unauthorizedAccess = Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Go back to Login page").build();

            containerRequestContext.abortWith(unauthorizedAccess);
        }
        }




    }
