package msg.user.boundary;

import msg.user.entity.dto.UserDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Stateless
@Path("/auth")
public class AuthResource {
    @EJB
    private UserFacade userFacade;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response whatever(UserDTO userInputDTO) {
        return Response.ok(userFacade.authenticateUser(userInputDTO)).build();
    }
}
