package msg.notifications.boundary;

import msg.bug.entity.dto.BugDTO;
import msg.notifications.control.NotificationControl;
import msg.notifications.entity.NotificationType;
import msg.notifications.entity.dto.NotificationDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Facade for all operations on Notifications.
 *
 * @author msg systems AG; UserEntity Name.
 * @since 19.1.2
 */
@Stateless
public class NotificationFacade {

    @EJB
    private NotificationControl notificationControl;


    public NotificationFacade() {
    }

    public void createNewBugNotification(String createdBy, String assignedTo, BugDTO dto) {
        NotificationDTO ndto = new NotificationDTO();
        ndto
                .setMessage("A new bug emerges from your defector code!")
                .setSource(createdBy)
                .setDestination(assignedTo)
                .setType(NotificationType.BUG_UPDATED.name());
        notificationControl.create(ndto);
    }

    public void createUpdatedBugNotification(String createdBy, String assignedTo, BugDTO dto) {
    }

    public void createClosedBugNotification(String createdBy, String assignedTo, BugDTO dto) {
    }
}
