package msg.user.boundary;

import msg.filters.StarkPermissions;
import msg.user.entity.dto.UserDTO;

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
    @StarkPermissions(permissions = {StarkPermissions.Permission.USER_MANAGEMENT})
    public Response createUser(UserDTO inputDTO){
        facade.createUser(inputDTO);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @StarkPermissions(permissions = {StarkPermissions.Permission.USER_MANAGEMENT})
    public Response updateUser(UserDTO inputDTO){
        facade.updateUser(inputDTO);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response authenticateUserByUsernameAndPassword(UserDTO inputDTO){
        return Response.ok(facade.authenticateUserByUsernameAndPassword(inputDTO)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @StarkPermissions(permissions = {StarkPermissions.Permission.BUG_CLOSE, StarkPermissions.Permission.USER_MANAGEMENT})
    public Response getAll() {
        return Response.ok(facade.getAll()).build();
    }

}
