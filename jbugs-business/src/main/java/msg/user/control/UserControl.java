// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package msg.user.control;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import msg.bug.boundary.BugFacade;
import msg.exeptions.BusinessException;
import msg.notifications.boundary.NotificationFacade;
import msg.permission.entity.PermissionEntity;
import msg.permission.entity.dto.PermissionDTO;
import msg.role.boundary.RoleFacade;
import msg.role.entity.RoleEntity;
import msg.role.entity.dto.RoleDTO;
import msg.user.MessageCatalog;
import msg.user.entity.UserDao;
import msg.user.entity.UserEntity;
import msg.user.entity.dto.UserConverter;
import msg.user.entity.dto.UserDTO;
import msg.user.token.Message;
import org.json.simple.JSONArray;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;
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
    private BugFacade bugFacade;

    private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";


    private String createJWT(UserEntity userEntity) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setSubject(userEntity.getUsername())
                .addClaims(convertUserRolesToMap(userEntity.getRoles()))
                .addClaims(convertUserPermissionsToMap(userEntity.getRoles()))
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    private Map<String, Object> convertUserPermissionsToMap(Set<RoleEntity> roles) {
        Map<String,Object> map= new HashMap<>();
        Set<String> setOfPermission= new HashSet<>();
        for(RoleEntity role: roles){
            for (PermissionEntity permission : role.getPermissions()) {
                setOfPermission.add(permission.getType());
            }
        }
        JSONArray jsArrayOfPermissions = new JSONArray();
        for(String permission: setOfPermission){
            jsArrayOfPermissions.add(permission);
        }
        map.put("permissions",jsArrayOfPermissions);
        return map;
    }

    private Map<String,Object> convertUserRolesToMap(Set<RoleEntity> roles){
        Map<String,Object> map= new HashMap<>();
        JSONArray jsArrayOfRoles = new JSONArray();
        for(RoleEntity role: roles){
            jsArrayOfRoles.add(role.getType());
        }
        map.put("roles",jsArrayOfRoles);
        return map;
    }


    private boolean validateUserInput(UserDTO userDTO){

        if(userDTO.getRoles().isEmpty() || userDTO.getLastName().isEmpty()
                || userDTO.getFirstName().isEmpty() || userDTO.getEmail().isEmpty()
                || userDTO.getMobileNumber().isEmpty() || userDTO.getPassword().isEmpty())
            return false;
        if(!userDTO.getFirstName().matches("^[A-Z][a-z]+[ ]?([A-Z][a-z]+)?$"))
            return false;
        if(!userDTO.getLastName().matches("^[A-Z][a-z]+$"))
            return false;
        if(!userDTO.getMobileNumber().matches("[+]4[0|9]{1}[0-9]{9}"))
            return false;
        if(!userDTO.getEmail().matches("^[a-z0-9._%+-]+@msggroup.com"))
            return false;

        return true;
    }

    private String createUsername(String lastName, String firstName){
        String username = "";

        if (lastName.length() > 5) {
            username += lastName.substring(0, 5) + firstName.charAt(0);
        } else {
            username += lastName;
            int letters = lastName.length();
            if (firstName.length() >= (6 - letters)) {
                username += firstName.substring(0, 6 - letters);
            } else {
                username += firstName;
            }
        }
        return username.toLowerCase();
    }

    private String generate(String username, int usernameLength, int totalLength, int firstLength, int firstLength2, ArrayList<String>firstNames) {
        String finalUsername = username;

        if (usernameLength < totalLength) {
            finalUsername += firstNames.get(0).charAt(firstLength);
        } else {
            if (firstNames.size() == 2) {
                if (firstLength2 < firstNames.get(1).length()) {
                    finalUsername += firstNames.get(1).charAt(firstLength2);
                } else {
                    finalUsername += firstNames.get(1).charAt(0);
                }
            } else {
                finalUsername += firstNames.get(0).charAt(0);
            }
        }
        return  finalUsername;
    }

    private String generateAnotherUsername(int totalUsernamesInDB, List<UserEntity> userEntities, int lastNameLength, ArrayList<String>firstNames){
        String finalUsername="";
        int firstNameLength2 = 0;
        int firstNameLength = firstNames.get(0).length();
        List<String>usernames = new ArrayList<>();
        for(UserEntity userEntity: userEntities){
            usernames.add(userEntity.getUsername());
        }
        Collections.sort(usernames);

        finalUsername = usernames.get(usernames.size()-1);

        if(lastNameLength >= 5) {
            finalUsername = generate(finalUsername, finalUsername.length(), (5 + firstNameLength),
                    totalUsernamesInDB, -(5 + firstNameLength) + finalUsername.length(), firstNames);
        }

        else{//avem lastname length < 5;
            finalUsername = generate(finalUsername, finalUsername.length(), (lastNameLength + firstNameLength),
                    finalUsername.length() - lastNameLength, -(lastNameLength + firstNameLength) + finalUsername.length(), firstNames);
        }
        return finalUsername;

    }

    public String createUser(final UserDTO userDTO) {

        String username;
        ArrayList<String>firstNames = new ArrayList<>();

        if(!validateUserInput(userDTO)){
            throw new BusinessException(MessageCatalog.INCORRECT_USER_INPUT);
        }

        final UserEntity newUserEntity = userConverter.convertUserDTOtoEntity(userDTO);
        newUserEntity.setStatus(true);
        newUserEntity.setCounter(5);
        if(userDTO.getFirstName().contains(" ")){
            String[] splited = userDTO.getFirstName().split("\\s+");
            firstNames.add(splited[0].toLowerCase());
            if(splited.length > 1){
                firstNames.add(splited[1].toLowerCase());
            }
        }
        else{
            firstNames.add(userDTO.getFirstName());
        }

        username = createUsername(userDTO.getLastName(), firstNames.get(0));

        List<UserEntity> userEntities = userDao.findUsernames(username);
        int usernamesNr = userEntities.size();

        if(usernamesNr > 0 && username.length() >= userEntities.get(0).getUsername().length()){
            username = generateAnotherUsername(usernamesNr, userEntities, userDTO.getLastName().length(), firstNames);
        }
        newUserEntity.setUsername(username.toLowerCase());
        userDao.createUser(newUserEntity);

        return newUserEntity.getUsername();
    }

    public String updateUser(final UserDTO userDTO) {

        if(!validateUserInput(userDTO)){
            throw new BusinessException(MessageCatalog.USERNAME_INVALID);
        }

        if(!userDao.existsUsername(userDTO.getUsername())){
            throw new BusinessException(MessageCatalog.USER_WITH_SAME_USERNAME_NOT_EXISTS);
        }


        UserEntity newUserEntity = null;
        newUserEntity = userConverter.convertUserDTOtoEntity(userDTO);
        userDao.updateUser(newUserEntity);
        return newUserEntity.getUsername();
    }

    public UserDTO getUserByUsername(String username){
        UserEntity userEntity = userDao.findByUsername(username);
        return userConverter.convertEntityToUserDTO(userEntity);
    }

    public List<UserDTO> getAll() {
        return userDao.getAll()
                .stream()
                .map(userConverter::convertEntityToUserDTO)
                .collect(Collectors.toList());
    }

    public List<String> getUsernames() {
        return userDao.getUsers();

    }

    public Message createToken(UserDTO inputDTO) {
        Message message = new Message();
        message.setToken(createJWT(authenticateUserByUsernameAndPassword(inputDTO)));
        return message;
    }

    private UserEntity authenticateUserByUsernameAndPassword(UserDTO userDTO) {
        UserEntity userEntity = userDao.findByUsername(userDTO.getUsername());
        if (userEntity != null) {
            if (userEntity.isStatus()) {
                if (userDTO.getPassword().equals(userEntity.getPassword())) {
                    userEntity.setCounter(5);
                    userDao.updateUser(userEntity);
                    return  userEntity;
                }
                updateUserWithWrongUsernameAndPassword(userEntity);
            } else
                throw new BusinessException(MessageCatalog.USER_DEACTIVATED);
        }
        else
            throw new BusinessException(MessageCatalog.INCORRECT_USERNAME_OR_PASSWORD);
        return userEntity;
    }

    private UserEntity updateUserWithWrongUsernameAndPassword(UserEntity userEntity) {
        int counter = userEntity.getCounter();
        userEntity.setCounter(--counter);
        if (counter == 0) {
            userEntity.setStatus(false);
            userDao.updateUser(userEntity);
            throw new BusinessException(MessageCatalog.USER_DEACTIVATED);
        } else {
            userDao.updateUser(userEntity);
            throw new BusinessException(MessageCatalog.INCORRECT_USERNAME_OR_PASSWORD);
        }
    }


    public Set<String> findUserPermissionsByUsername(String username) {
        Set<String> userPermissions = new HashSet<>();
        Set<RoleEntity> roles=userDao.findByUsername(username).getRoles();

        for (RoleEntity role : roles) {
            Set<PermissionEntity> permission= role.getPermissions();
            for (PermissionEntity permissionEntity : permission) {
                userPermissions.add(permissionEntity.getType());
            }

        }
        return userPermissions;
    }

    public void updateUserStatus(UserDTO inputDTO) {
        if(inputDTO.getUsername()== null || inputDTO.getUsername().isEmpty()){
            throw new BusinessException(MessageCatalog.INCORRECT_USER_INPUT);
        }
        UserEntity userEntity=userDao.findByUsername(inputDTO.getUsername());
        if(userEntity==null)
            throw new BusinessException(MessageCatalog.INCORRECT_USER_INPUT);
        boolean newStatus=inputDTO.getStatus();
        boolean oldStatus=userEntity.isStatus();
        if(!(newStatus==oldStatus)){
            if(!newStatus && bugFacade.countActiveBugsForUser(userEntity.getUsername())){
                throw new BusinessException(MessageCatalog.USER_BUGS_OPEN);
            }
            userEntity.setStatus(newStatus);
            userEntity.setCounter(5);
            userDao.updateUser(userEntity);
        }
        else
            throw new BusinessException(MessageCatalog.INCORRECT_USER_INPUT);
    }


    public boolean getUserStatus(String username) {
        return userDao.findByUsername(username).isStatus();
    }


}
