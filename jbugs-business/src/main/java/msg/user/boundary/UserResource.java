package msg.user.boundary;

import msg.user.entity.dto.UserDTO;
import msg.user.entity.dto.UserInputDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Stateless
@Path("/users")
public class UserResource {
    @EJB
    public  UserFacade facade;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserInputDTO inputDTO){
        facade.createUser(inputDTO);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(facade.getAll()).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUserByUsernameAndPassword(UserDTO userDTO) {
        return Response.ok(facade.authenticateUserByUsernameAndPassword(userDTO)).build();
    }
}
