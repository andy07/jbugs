// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package msg.user.control;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import msg.exeptions.BusinessException;
import msg.notifications.boundary.NotificationFacade;
import msg.notifications.boundary.notificationParams.NotificationParamsWelcomeUser;
import msg.notifications.entity.NotificationType;
import msg.role.entity.RoleEntity;
import msg.user.MessageCatalog;
import msg.user.entity.UserDao;
import msg.user.entity.UserEntity;
import msg.user.entity.dto.UserConverter;
import msg.user.entity.dto.UserDTO;
import msg.user.entity.dto.UserInputDTO;
import msg.user.entity.dto.UserOutputDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Control operations for all the User related operations.
 *
 * @author msg-system ag;  Daniel Donea
 * @since 1.0
 */
@Stateless
public class UserControl {

    @EJB
    private UserDao userDao;

    @EJB
    private UserConverter userConverter;

    @EJB
    private NotificationFacade notificationFacade;


    /**
     * Creates a userDTO based on the {@link UserInputDTO}.
     *
     * @param userDTO the input User DTO. mandatory
     * @return the username of the newly created user.
     */
    public String createUser(final UserInputDTO userDTO){
        if (userDao.existsEmail(userDTO.getEmail())){
            throw new BusinessException(MessageCatalog.USER_WITH_SAME_MAIL_EXISTS);
        }

        final UserEntity newUserEntity = userConverter.convertInputDTOtoEntity(userDTO);

        newUserEntity.setUsername(this.createUserName(userDTO.getFirstName(), userDTO.getLastName()));
        newUserEntity.setPassword("DEFAULT_PASSWORD");
        userDao.createUser(newUserEntity);

        final String userFullName = newUserEntity.getFirstName() + " " + newUserEntity.getLastName();
        this.notificationFacade.createNotification(
                NotificationType.WELCOME_NEW_USER,
                new NotificationParamsWelcomeUser(userFullName, newUserEntity.getUsername()));

        return newUserEntity.getUsername();
    }

//    public UserEntity updateUser(UserInputDTO user) {
//        UserEntity entity = userConverter.convertInputDTOtoEntity(user);
//        return userDao.updateUser(entity);
//    }

    /**
     * Creates a unique user name based on the inputs.
     *
     * @param firstName the first name of the user. mandatory
     * @param lastName the last name of the user. mandatory
     * @return a unique identifier for the input user.
     */
    //TODO Replace with logic based on the specification
    //cei din afara nu tre sa stie cum creem, de aia i privata
    private String createUserName(final String firstName, final String lastName){
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int count = 8;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public List<UserOutputDTO> getAll(){
        return userDao.getAll()
                .stream()
                .map(userConverter::convertEntityToUserOutputDTO)
                .collect(Collectors.toList());
    }

    public String authenticateUserByEmail(UserInputDTO userInputDTO) {
        UserEntity userEntity= userDao.findUserByEmail(userInputDTO.getEmail());
        if(userEntity!=null){
            Algorithm algorithm = Algorithm.HMAC256("harambe");
            return JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username",userEntity.getUsername())
                    .withArrayClaim("roles",(String []) userEntity.getRoles()
                                                    .stream()
                            .map(RoleEntity::getType).toArray(String[]::new))
                                                    .sign(algorithm);
        }else {
            throw new BusinessException(MessageCatalog.USER_WITH_INVALID_CREDENTIALS);
        }
    }


    public UserOutputDTO authenticateUserByUsernameAndPassword(UserDTO userDTO) {
        UserOutputDTO userOutputDTO=null;
        UserEntity userEntity= userDao.findByUsername(userDTO.getUsername());
        if(userEntity != null){
            if(userEntity.getPassword().equals(userDTO.getPassword())){
                if(userEntity.getCounter() != 5){
                    userEntity.setCounter(5);
                    userDao.createUser(userEntity);
                }
                UserConverter userConverter=  new UserConverter();
                userOutputDTO=userConverter.convertEntityToUserOutputDTO(userEntity);
            }
            else {
                if(userEntity.isStatus()){
                    int counter=userEntity.getCounter();
                    userEntity.setCounter(--counter);

                    if(counter == 0){
                        userEntity.setStatus(false);
                        userDao.updateUser(userEntity);
                        throw new BusinessException(MessageCatalog.USER_DEACTIVATED);
                    }
                    else {
                        userDao.updateUser(userEntity);
                        throw new BusinessException(MessageCatalog.INCORRECT_USERNAME_OR_PASSWORD);

                    }

                }
                else {
                    throw new BusinessException(MessageCatalog.USER_DEACTIVATED);
                }
            }

        }
        else
            throw new BusinessException(MessageCatalog.INCORRECT_USERNAME_OR_PASSWORD);
        return userOutputDTO;
    }
}
