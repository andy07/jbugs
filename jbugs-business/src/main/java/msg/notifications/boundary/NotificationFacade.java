package msg.notifications.boundary;

import msg.bug.entity.dto.BugDTO;
import msg.notifications.control.NotificationControl;
import msg.notifications.entity.NotificationType;
import msg.notifications.entity.dto.NotificationDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

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
                .setMessage("A new bug emerges from your imperfect code!")
                .setDestination(assignedTo)
                .setType(NotificationType.BUG_UPDATED.name());
        notificationControl.create(ndto);
    }

    public void createUpdatedBugNotification(String createdBy, String assignedTo, BugDTO dto) {
        NotificationDTO ndto = new NotificationDTO();
        ndto
                .setMessage("A new bug is assigned to you!")
                .setDestination(assignedTo)
                .setType(NotificationType.BUG_UPDATED.name());
        notificationControl.create(ndto);

        ndto
                .setMessage("You assigned a new bug to " + assignedTo + "!")
                .setDestination(createdBy)
                .setType(NotificationType.BUG_UPDATED.name());
        notificationControl.create(ndto);
    }

    public void createClosedBugNotification(String createdBy, String assignedTo, BugDTO dto) {
        NotificationDTO ndto = new NotificationDTO();
        ndto
                .setMessage("Your bug is closed!")
                .setDestination(assignedTo)
                .setType(NotificationType.BUG_UPDATED.name());
        notificationControl.create(ndto);

        ndto
                .setMessage("You closed this bug!")
                .setDestination(createdBy)
                .setType(NotificationType.BUG_UPDATED.name());
        notificationControl.create(ndto);
    }

    public void createNewUserNotification(String username) {
        NotificationDTO ndto = new NotificationDTO();
        ndto
                .setMessage("Welcome, " + username + "!")
                .setDestination(username)
                .setType(NotificationType.WELCOME_NEW_USER.name());
        notificationControl.create(ndto);
    }

    public void createDeactivatedNotification(String username) {
        NotificationDTO ndto = new NotificationDTO();
        ndto
                .setMessage("You have been deactivated!")
                .setDestination(username)
                .setType(NotificationType.USER_DEACTIVATED.name());
        notificationControl.create(ndto);
    }

    public List<NotificationDTO> getNotifications(String username) {
        return notificationControl.getNotifications(username);
    }


}
