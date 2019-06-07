package msg.notifications.entity.dto;

/**
 * @author msg systems AG; Silviu-Mihnea Cucuiet.
 * @since 19.1.2
 * day 6/6/2019
 * time 4:45 PM
 */
public class NotificationDTO {
    private String destination;
    private String message;
    private String type;

    public NotificationDTO() {
    }

    public String getDestination() {
        return destination;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public NotificationDTO setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public NotificationDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public NotificationDTO setType(String type) {
        this.type = type;
        return this;
    }
}
