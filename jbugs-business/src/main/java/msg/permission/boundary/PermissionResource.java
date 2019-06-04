package msg.permission.boundary;

import msg.filters.StarkPermissions;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
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
@Path("/permissions")
public class PermissionResource {

    @EJB
    public PermissionFacade permissionFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @StarkPermissions(permissions = StarkPermissions.Permission.PERMISSION_MANAGEMENT)
    public Response getAll(){
        return Response.ok(permissionFacade.getAll()).build();
    }
}
