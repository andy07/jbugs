// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package msg.user.control;

import msg.notifications.boundary.NotificationFacade;
import msg.user.entity.UserDao;
import msg.user.entity.dto.UserConverter;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Test class for UserControl.
 *
 * @author msg-system ag;  Daniel Donea
 * @since 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControlTest {

    @InjectMocks
    UserControl userControl;

    @Mock
    UserDao userDao;

    @Mock
    UserConverter userConverter;

    @Mock
    NotificationFacade notificationFacade;

    @Before
    public void setUp() {
    }

    /*@Test
    public void testCreateUserWithSuccess(){
        UserDTO user = createTestInputDTO();

        Mockito.when(userConverter.convertUserDTOtoEntity(Mockito.any())).thenCallRealMethod();
        Mockito.when(userDao.existsEmail(user.getEmail().get())).thenReturn(false);
        Mockito.doNothing().when(notificationFacade).createNotification(Mockito.any(), Mockito.any());

        this.userControl.createUser(user);
    }

    @Test(expected = BusinessException.class)
    public void testCreateUserWhenEmailAddressAlreadyExists(){
        UserInputDTO user = createTestInputDTO();

        Mockito.when(userDao.existsEmail(user.getEmail())).thenReturn(true);

        this.userControl.createUser(user);
    }

    @Test
    public void testCreateUserNotificationSent(){
        UserInputDTO user = createTestInputDTO();

        ArgumentCaptor<NotificationType> sentNotificationType = ArgumentCaptor.forClass(NotificationType.class);
        Mockito.when(userConverter.convertInputDTOtoEntity(Mockito.any())).thenCallRealMethod();
        Mockito.when(userDao.existsEmail(user.getEmail())).thenReturn(false);
        Mockito.doNothing().when(notificationFacade).createNotification(sentNotificationType.capture(), Mockito.any());

        this.userControl.createUser(user);
        Assert.assertEquals(sentNotificationType.getValue(), NotificationType.WELCOME_NEW_USER);
    }

    private UserDTO createTestInputDTO() {
        UserDTO user = new UserDTO();
        user.setLastName("Pop");
        user.setFirstName("Andrei");
        user.setEmail("axasde@yahoo.com");
        user.setMobileNumber("0700000000");
        user.setCounter(0);
        return user;
    }*/

}
