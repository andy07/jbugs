package msg.notifications.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * The DAO for the Notification Entities.
 *
 * @author msg systems AG; UserEntity Name.
 * @since 19.1.2
 */
@Stateless
public class NotificationDAO {

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    /**
     * Creates a notification based on the input Entity.
     *
     * @param notificationEntity the input entity.
     */
    public void createNotification(NotificationEntity notificationEntity){
        this.em.persist(notificationEntity);
    }

    public List<NotificationEntity> getNotifications(String username) {
        return em
                .createNamedQuery(NotificationEntity.NOTIFICATIONS_FIND_BY_USERNAME, NotificationEntity.class)
                .setParameter("username", username)
                .getResultList();
    }
}
