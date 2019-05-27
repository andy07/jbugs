package msg.user.boundary;

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
    public Response createUser(UserDTO inputDTO){
        facade.createUser(inputDTO);
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
    public Response getAll() {
        return Response.ok(facade.getAll()).build();
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAll(){
//        return Response
//                .status(200)
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Credentials", "true")
//                .header("Access-Control-Allow-Headers",
//                        "origin, content-type, accept, authorization")
//                .header("Access-Control-Allow-Methods",
//                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//                .entity(facade.getAll())
//                .build();
//    }
}
