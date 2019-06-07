// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package msg.user.boundary;

import msg.user.control.UserControl;
import msg.user.entity.UserEntity;
import msg.user.entity.dto.UserDTO;
import msg.user.token.Message;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Set;

/**
 * Facade for all operations on Users.
 *
 * @author msg-system ag;  Daniel Donea
 * @since 1.0
 */
@Stateless
public class UserFacade {

    @EJB
    private UserControl userControl;

    /**
     * Creates a user based on the {@link UserDTO}.
     *
     * @param user the input User DTO. mandatory
     */
    //@RolesAllowed(Permissions.USER_MANAGEMENT)
    public void createUser(UserDTO user){
         this.userControl.createUser(user);
    }

    public void updateUser(UserDTO user){
        this.userControl.updateUser(user);
    }


    public UserDTO getUserByUsername(String username){
        return userControl.getUserByUsername(username);
    }


    public List<UserDTO> getAll() {
        return userControl.getAll();
    }

    public Set<String> findUserPermissionsByUsername(String username){
        return userControl.findUserPermissionsByUsername(username);
    }

    public Message authenticateUserByUsernameAndPassword(UserDTO inputDTO) {
        return userControl.createToken(inputDTO);
    }

    public void updateUserStatus(UserDTO inputDTO) {
        userControl.updateUserStatus(inputDTO);
    }

    public List<String> getUserNames() {
        return userControl.getUsernames();
    }

    public boolean getUserStatus(String username) {
       return userControl.getUserStatus(username);
    }
}
