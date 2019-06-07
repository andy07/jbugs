package msg.notifications.boundary;

import msg.filters.StarkPermissions;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author msg systems AG; Silviu-Mihnea Cucuiet.
 * @since 19.1.2
 * day 6/7/2019
 * time 7:36 PM
 */

@Stateless
@Path("/notifications")
public class NotificationResource {

    @EJB
    private NotificationFacade facade;

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @StarkPermissions(permissions = {StarkPermissions.Permission.USER_MANAGEMENT})
    public Response getAll(@PathParam("username") String username) {
        return Response.ok(facade.getNotifications(username)).build();
    }
}
