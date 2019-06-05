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
    @Produces(MediaType.APPLICATION_JSON)
    @StarkPermissions(permissions = {StarkPermissions.Permission.USER_MANAGEMENT})
    public Response createUser(UserDTO inputDTO){
        facade.createUser(inputDTO);
        return Response.ok().build();
    }

    @PUT
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @StarkPermissions(permissions = {StarkPermissions.Permission.USER_MANAGEMENT})
    public Response updateUser(@PathParam("username") String username,UserDTO inputDTO){
        facade.updateUser(inputDTO);
        return Response.ok().build();
    }


    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @StarkPermissions(permissions = {StarkPermissions.Permission.USER_MANAGEMENT})
    public Response getUserByUsername(@PathParam("username") String username) {

        return Response.ok(facade.getUserByUsername(username)).build();
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
    @StarkPermissions(permissions = {StarkPermissions.Permission.USER_MANAGEMENT})
    public Response getAll() {
        return Response.ok(facade.getAll()).build();
    }

    @Path("/usernames/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @StarkPermissions(permissions = {StarkPermissions.Permission.USER_MANAGEMENT, StarkPermissions.Permission.BUG_MANAGEMENT})
    public Response getAllUsernames() {
        return Response.ok(facade.getUserNames()).build();
    }

    @POST
    @Path("/update-user-status")
    @Consumes(MediaType.APPLICATION_JSON)
    @StarkPermissions(permissions = {StarkPermissions.Permission.USER_MANAGEMENT})
    public Response updateUserStatus(UserDTO inputDTO){
        facade.updateUserStatus(inputDTO);
        return Response.ok().build();
    }

}
