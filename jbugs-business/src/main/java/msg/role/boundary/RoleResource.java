package msg.role.boundary;

import msg.role.entity.RoleDAO;
import msg.role.entity.dto.RoleDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Document me.
 *
 * @author msg systems AG; Diana.
 * @since 19.1.2
 */

@Stateless
@Path("/roles")
public class RoleResource {

    @EJB
    public RoleFacade roleFacade;


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRole(RoleDTO roleDTO){
        roleFacade.updateRole(roleDTO);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.ok(roleFacade.getAll()).build();
    }


}
