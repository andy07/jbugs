package msg.role.boundary;

import msg.filters.StarkPermissions;
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
    @StarkPermissions(permissions = StarkPermissions.Permission.PERMISSION_MANAGEMENT)
    public Response updateRole(RoleDTO roleDTO){
        roleFacade.updateRole(roleDTO);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @StarkPermissions(permissions = StarkPermissions.Permission.PERMISSION_MANAGEMENT)
    public Response getAll(){
        return Response.ok(roleFacade.getAll()).build();
    }


    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/type")
    @StarkPermissions(permissions = StarkPermissions.Permission.PERMISSION_MANAGEMENT)
    public Response getRoleByType(String type){
        return Response.ok(roleFacade.getRoleByType(type)).build();
    }




}
