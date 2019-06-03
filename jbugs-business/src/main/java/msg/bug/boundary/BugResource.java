package msg.bug.boundary;

import msg.bug.entity.dto.BugDTO;
import msg.filters.StarkPermissions;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author msg systems AG; Silviu-Mihnea Cucuiet.
 * @since 19.1.2
 * day 5/22/2019
 * time 7:57 PM
 */

@Stateless
@Path("/bugs")
public class BugResource {
    @EJB
    public BugFacade facade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @StarkPermissions(permission = StarkPermissions.Permission.BUG_MANAGEMENT)
    public Response getAll() {
        return Response.ok(facade.getAll()).build();
    }

    @GET
    @Path("/status/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    @StarkPermissions(permission = StarkPermissions.Permission.BUG_MANAGEMENT)
    public Response getStatusAllowed(@PathParam("status") String status) {
        return Response.ok(facade.getStatusAllowed(status)).build();
    }

    @PUT
    @Path("/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @StarkPermissions(permission = StarkPermissions.Permission.BUG_MANAGEMENT)
    public Response updateBug(@PathParam("title") String title, BugDTO dto) {
        dto = facade.update(dto);
        return Response.ok(dto).build();
    }
    @GET
    @Path("/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    @StarkPermissions(permission = StarkPermissions.Permission.BUG_MANAGEMENT)
    public Response getBugByTitle(@PathParam("title") String title) {
        return Response.ok(facade.getBugByTitle(title)).build();
    }

}
