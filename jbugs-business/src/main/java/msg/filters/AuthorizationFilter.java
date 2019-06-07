package msg.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import msg.user.boundary.UserFacade;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.HashSet;
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

    @Context
    ResourceInfo resourceInfo;
    @EJB
    UserFacade userFacade;

    private final String AUTHENTICATION_HEADER = "Authorization";
    private final String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
    private final String LOGIN_PATH = "users/login";
    private final String MESSAGE_FOR_HACKERS = "Mr.(Ms.) hacker, please go back to sleep! Stark TEAM are watching you!";
    private final String MESSAGE_FOR_CHANGED_PERMISSIONS = "Your permissions have changed! Go back to Login page!";
    private final String MESSAGE_FOR_DEACTIVATED_USER = "Your account has been deactivated!";
    private final String MESSAGE_FOR_UNAUTHORIZED_ACCESS = "Go back to Login page!";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authorizationValue = containerRequestContext.getHeaderString(AUTHENTICATION_HEADER);
        if (containerRequestContext.getUriInfo().getPath().equals(LOGIN_PATH)) {
            return;
        } else if (authorizationValue != null && authorizationValue.length() > 0 && authorizationValue.startsWith("Bearer")) {
            String token = authorizationValue.split(" ")[1];
            Claims body = null;
            try {
                //This line will throw an exception if it is not a signed JWS (as expected)
                body = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                        .parseClaimsJws(token).getBody();

            } catch (Exception e) {
                createResponseForUnauthorizedAccess(containerRequestContext, MESSAGE_FOR_HACKERS);
            }
            final String username = body.getSubject();
            final List<String> userPermissions = (List<String>) body.get("permissions");
            final StarkPermissions.Permission[] annotatedMethodPermissions = resourceInfo.getResourceMethod().getAnnotation(StarkPermissions.class).permissions();
            if (!verifyIfUserIsDeactivated(username)) {
                createResponseForUnauthorizedAccess(containerRequestContext, MESSAGE_FOR_DEACTIVATED_USER);
            }
            if (!verifyUserRoles(username, userPermissions)) {
                createResponseForUnauthorizedAccess(containerRequestContext, MESSAGE_FOR_CHANGED_PERMISSIONS);
            }
            if (!verifyPathPermissions(annotatedMethodPermissions, userPermissions)) {
                createResponseForUnauthorizedAccess(containerRequestContext, MESSAGE_FOR_HACKERS);
            }
            return;

        } else {
            createResponseForUnauthorizedAccess(containerRequestContext, MESSAGE_FOR_UNAUTHORIZED_ACCESS);
        }
    }


    private boolean verifyPathPermissions(StarkPermissions.Permission[] annotatedMethodPermissions, List<String> userPermissions) {
        for (StarkPermissions.Permission methodPermission : annotatedMethodPermissions) {
            boolean existPermission = false;
            for (String permission : userPermissions) {
                if ((methodPermission.toString()).equalsIgnoreCase(permission)) {
                    existPermission = true;
                    break;
                }
            }
            if (!existPermission) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyUserRoles(String username, List<String> permissions) {
        Set<String> dataBaseSetOfPermission = userFacade.findUserPermissionsByUsername(username);
        Set<String> userPermissions = new HashSet<>(permissions);
        if (dataBaseSetOfPermission.size() != userPermissions.size()) {
            return false;
        }
        return userPermissions.containsAll(dataBaseSetOfPermission);
    }


    private boolean verifyIfUserIsDeactivated(String username) {
        return userFacade.getUserStatus(username);
    }


    private void createResponseForUnauthorizedAccess(ContainerRequestContext containerRequestContext, String message) {
        Response unauthorizedAccess = Response.status(Response.Status.UNAUTHORIZED).entity(message).build();
        containerRequestContext.abortWith(unauthorizedAccess);
    }


}
