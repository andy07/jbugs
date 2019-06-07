package msg.notifications.entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The Notification Entity.
 *
 * @author msg systems AG;
 * @since 19.1.2
 */
@Entity
@Table(name = "notifications")
@NamedQueries(
        @NamedQuery(name = NotificationEntity.NOTIFICATIONS_FIND_BY_USERNAME,
                query = "SELECT N from NotificationEntity N where N.username = :username")
)
public class NotificationEntity {

    public static final String NOTIFICATIONS_FIND_BY_USERNAME = "notifications.findAllByUsername";
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "date", nullable = false)
    private Date date = Date.valueOf(LocalDate.now());

    @Column(name = "user_name", nullable = false)
    private String username;

    public NotificationEntity() {
    }

    public long getId() {
        return id;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public NotificationEntity setId(long id) {
        this.id = id;
        return this;
    }

    public NotificationEntity setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
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

    public NotificationEntity setUsername(String userID) {
        this.username = userID;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationEntity that = (NotificationEntity) o;
        return id == that.id &&
                username.equals(that.username) &&
                notificationType == that.notificationType &&
                Objects.equals(message, that.message) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationType, message);
    }
}
