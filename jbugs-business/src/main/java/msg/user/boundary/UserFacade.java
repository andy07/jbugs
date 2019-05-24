// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package msg.user.boundary;

import msg.user.control.UserControl;
import msg.user.entity.UserEntity;
import msg.user.entity.dto.UserDTO;
import msg.user.entity.dto.UserInputDTO;
import msg.user.entity.dto.UserOutputDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

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
     * Creates a user based on the {@link UserInputDTO}.
     *
     * @param user the input User DTO. mandatory
     */
    //@RolesAllowed(Permissions.USER_MANAGEMENT)
    public void createUser(UserInputDTO user){
         this.userControl.createUser(user);
    }

//    public void updateUser(UserInputDTO user) {
//        this.userControl.updateUser(user);
//    }

    public List<UserOutputDTO> getAll() {
        return userControl.getAll();
    }

    public UserOutputDTO authenticateUserByUsernameAndPassword(UserDTO userDTO){
        return userControl.authenticateUserByUsernameAndPassword(userDTO);
    }

    public Object authenticateUser(UserInputDTO userInputDTO) {
        return userControl.authenticateUserByEmail(userInputDTO);
    }
}
