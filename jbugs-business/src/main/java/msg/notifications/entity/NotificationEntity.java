package msg.notifications.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * The Notification Entity.
 *
 * @author msg systems AG;
 * @since 19.1.2
 */
@Entity
@Table(name = "notifications")
public class NotificationEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "user_id", nullable = false)
    private long userID;

    public NotificationEntity() {
    }

    public long getId() {
        return id;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public long getUserID() {
        return userID;
    }

    public NotificationEntity setId(long id) {
        this.id = id;
        return this;
    }

    public NotificationEntity setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    public NotificationEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public NotificationEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public NotificationEntity setDate(Date date) {
        this.date = date;
        return this;
    }

    public NotificationEntity setUserID(long userID) {
        this.userID = userID;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationEntity that = (NotificationEntity) o;
        return notificationType == that.notificationType &&
                Objects.equals(url, that.url) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationType, url, message);
    }
}
