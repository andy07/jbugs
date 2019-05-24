package msg.user.entity.dto;

import msg.role.entity.RoleEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class UserDTO {
    private String username;
    private String password;
//    private boolean status;
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String mobileNumber;
//    private Set<RoleEntity> roles = new HashSet<>();

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
