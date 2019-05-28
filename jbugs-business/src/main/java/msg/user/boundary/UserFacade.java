// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package msg.user.boundary;

import msg.user.control.UserControl;
import msg.user.entity.dto.UserDTO;

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
     * Creates a user based on the {@link UserDTO}.
     *
     * @param user the input User DTO. mandatory
     */
    //@RolesAllowed(Permissions.USER_MANAGEMENT)
    public void createUser(UserDTO user) {
         this.userControl.createUser(user);
    }

    public List<UserDTO> getAll() {
        return userControl.getAll();
    }

    public Object authenticateUser(UserDTO userInputDTO) {
        return userControl.authenticateUser(userInputDTO);
    }
}
