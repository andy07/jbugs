package msg.notifications.entity.dto;

import msg.notifications.entity.NotificationEntity;
import msg.notifications.entity.NotificationType;

import javax.ejb.Stateless;
import java.sql.Date;

/**
 * @author msg systems AG; Silviu-Mihnea Cucuiet.
 * @since 19.1.2
 * day 6/6/2019
 * time 4:49 PM
 */
@Stateless
public class NotificationConverter {

    public NotificationDTO convertEntityToDTO(NotificationEntity entity) {
        return new NotificationDTO()
                .setDestination(entity.getUsername())
                .setType(entity.getNotificationType().name())
                .setMessage(entity.getMessage());
    }

    public NotificationEntity convertDTOToEntity(NotificationDTO dto) {
        return new NotificationEntity()
                .setDate(new Date(new java.util.Date().getTime()))
                .setMessage(dto.getMessage())
                .setUsername(dto.getDestination())
                .setNotificationType(NotificationType.valueOf(dto.getType()));
    }
}
