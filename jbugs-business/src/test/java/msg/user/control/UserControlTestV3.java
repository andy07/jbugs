package msg.user.control;

import msg.permission.entity.PermissionEntity;
import msg.role.entity.RoleEntity;
import msg.user.entity.UserDao;
import msg.user.entity.UserEntity;
import msg.user.entity.dto.UserDTO;
import msg.user.token.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.HashSet;
import java.util.Set;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class UserControlTestV3 {
    @Mock
    UserDao userDao;
    UserControl userControl;


    @Before
    public void setUp() {

        userControl = new UserControl();
        userDao = Mockito.mock(UserDao.class);  //il ia de bun
        try {
            FieldSetter.setField(userControl,
                    UserControl.class.getDeclaredField("userDao"),
                    userDao);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void verifyWrongUsernameAndPassword(){
        UserDTO userDTO = new UserDTO().setUsername("florea")
                .setPassword("pass");
        Set<PermissionEntity> permissionEntities = new HashSet<>();
        permissionEntities.add(new PermissionEntity().setType("USER_MANAGEMENT"));
        permissionEntities.add(new PermissionEntity().setType("BUG_MANAGEMENT"));
        permissionEntities.add(new PermissionEntity().setType("BUG_CLOSE"));
        permissionEntities.add(new PermissionEntity().setType("BUG_EXPORT_PDF"));
        permissionEntities.add(new PermissionEntity().setType("USERUL_ADRESAT"));
        permissionEntities.add(new PermissionEntity().setType("PERMISSION_MANAGEMENT"));
        Set<RoleEntity> roleEntities = new HashSet<>();
        roleEntities.add(new RoleEntity().setType("ADM").setPermissions(permissionEntities));
        UserEntity userEntity = new UserEntity().setUsername("florea")
                .setPassword("pass")
                .setStatus(true)
                .setRoles(roleEntities);

        Mockito.when(userDao.findByUsername(userDTO.getUsername())).thenReturn(userEntity);
        Message message = userControl.createToken(userDTO);
        Assert.assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmbG9yZWEiLCJyb2xlcyI6WyJBRE0iXSwicGVybWlzc2lvbnMiOlsiVVNFUlVMX0FEUkVTQVQiLCJCVUdfRVhQT1JUX1BERiIsIlBFUk1JU1NJT05fTUFOQUdFTUVOVCIsIkJVR19NQU5BR0VNRU5UIiwiVVNFUl9NQU5BR0VNRU5UIiwiQlVHX0NMT1NFIl19.6cynrnpry30sMqsAWkNf9_d_4zTU0myrYYGq8YOZZHY",
                message.getToken());
    }

}
