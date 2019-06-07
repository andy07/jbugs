package msg.notifications.control;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import msg.notifications.entity.NotificationDAO;
import msg.notifications.entity.NotificationEntity;
import msg.notifications.entity.dto.NotificationConverter;
import msg.notifications.entity.dto.NotificationDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Control operations for all the Notification related operations.
 *
 * @author msg systems AG;
 * @since 19.1.2
 */
@Stateless
public class NotificationControl {
    @EJB
    private NotificationDAO notificationDao;

    @EJB
    private NotificationConverter converter;

    private ConnectionFactory factory;
    private static final String EXCHANGE_NAME = "notifications";

    public NotificationControl() {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("test");
        factory.setPassword("test");
    }

    public void create(NotificationDTO dto) {
        NotificationEntity entity = converter.convertDTOToEntity(dto);
        notificationDao.createNotification(entity);
        send(dto);
    }

    private void send(NotificationDTO dto) {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);

            String message = dto.getMessage();
            byte[] body = message.getBytes(StandardCharsets.UTF_8);

            channel.basicPublish(EXCHANGE_NAME, dto.getDestination(), null, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<NotificationDTO> getNotifications(String username) {
        return notificationDao.getNotifications(username)
                .stream()
                .map(converter::convertEntityToDTO)
                .collect(Collectors.toList());
    }
}
