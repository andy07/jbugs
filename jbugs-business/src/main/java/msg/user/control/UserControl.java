// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package msg.user.control;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import msg.bug.boundary.BugFacade;
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

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;
import java.util.stream.Collectors;


import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import io.jsonwebtoken.*;

import io.jsonwebtoken.Jwts;
import org.json.simple.JSONArray;

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

    @EJB
    private RoleFacade roleFacade;

    @EJB
    private BugFacade bugFacade;

    private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";

    //Sample method to construct a JWT
    public String createJWT(UserDTO userDTO) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());


        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setSubject(userDTO.getUsername())
                .addClaims(convertUserRolesToMap(userDTO.getRoles()))
                .addClaims(convertUserPerimissionToMap(userDTO.getRoles()))
                .signWith(signatureAlgorithm, signingKey);

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    private Map<String, Object> convertUserPerimissionToMap(List<String> roles) {
        Map<String,Object> map= new HashMap<>();
        Set<String> setOfPermission= new HashSet<>();
        for(String role: roles){
            RoleDTO roleDTO=roleFacade.getRoleByType(role);
            for (PermissionDTO permission:roleDTO.getPermissions()){
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

    private Map<String,Object> convertUserRolesToMap(List<String> roles){
        Map<String,Object> map= new HashMap<>();
        JSONArray jsArrayOfRoles = new JSONArray();
        for(String role: roles){
            jsArrayOfRoles.add(role);
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

    private String generate(String username, int usernameLength, int totalLength, int totalLength2, int firstLength, int firstLength2, ArrayList<String>firstNames) {
        String finalUsername = username;

        if (usernameLength < totalLength) {
            finalUsername += firstNames.get(0).charAt(firstLength); //caz cand avem lastname >=5 si avem inca litere in firstname
        } else {//nu mai avem litere in firstname
            if (firstNames.size() == 2) {//punem litere din firstName2
                if (usernameLength < totalLength2) {
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
        String finalUsername = userEntities.get(totalUsernamesInDB - 1).getUsername();
        int firstNameLength2 = 0;
        int firstNameLength = firstNames.get(0).length();
        int usernameLength = finalUsername.length();
        if(firstNames.size() == 2){
            firstNameLength2 = firstNames.get(1).length();
        }

        if(lastNameLength >= 5) {
            finalUsername = generate(finalUsername, finalUsername.length(), (5 + firstNameLength), (5 + firstNameLength + firstNameLength2),
                    totalUsernamesInDB, -(5 + firstNameLength) + finalUsername.length(), firstNames);


            /*if (usernameLength < (5 + firstNameLength)) {
                finalUsername += firstNames.get(0).charAt(-5 + usernameLength); //caz cand avem lastname >=5 si avem inca litere in firstname
            }
            else {//nu mai avem litere in firstname
                if (firstNames.size() == 2) {//punem litere din firstName2
                    if (usernameLength < (5 + firstNameLength + firstNameLength2)) {
                        finalUsername += firstNames.get(1).charAt(-(5 + firstNameLength) + usernameLength);
                    } else {
                        finalUsername += firstNames.get(1).charAt(0);
                    }
                } else {
                    finalUsername += firstNames.get(0).charAt(0);
                }
            }*/
        }

        else{//avem lastname length < 5;

            finalUsername = generate(finalUsername, usernameLength, (lastNameLength + firstNameLength), (lastNameLength + firstNameLength),
                    usernameLength - lastNameLength, -(lastNameLength + firstNameLength) + usernameLength, firstNames);

            /*if(usernameLength < (lastNameLength + firstNameLength)){
                finalUsername+= firstNames.get(0).charAt(usernameLength - lastNameLength);
            }
            else{
                if(firstNames.size() == 2){
                    if(usernameLength < (lastNameLength + firstNameLength)){
                        finalUsername+= firstNames.get(1).charAt(-(lastNameLength + firstNameLength) + usernameLength);
                    }
                    else{
                        finalUsername += firstNames.get(1).charAt(0);
                    }
                }
            }*/
        }
        return finalUsername;

    }

    /**
     * Creates a userDTO based on the {@link UserDTO}.
     *
     * @param userDTO the input User DTO. mandatory
     * @return the username of the newly created user.
     */

    public String createUser(final UserDTO userDTO) {

        String username;
        ArrayList<String>firstNames = new ArrayList<>();

        if(!validateUserInput(userDTO)){
            throw new BusinessException(MessageCatalog.INCORRECT_USER_INPUT);
        }

        final UserEntity newUserEntity = userConverter.convertUserDTOtoEntity(userDTO);
        newUserEntity.setStatus(true);
        newUserEntity.setCounter(5);
        if(!userDTO.getFirstName().contains(" ")){
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

//        this.notificationFacade.createNotification(
//                NotificationType.WELCOME_NEW_USER,
//                new NotificationParamsWelcomeUser(userFullName, newUserEntity.getUsername()));

        return newUserEntity.getUsername();
    }

    public String updateUser(final UserDTO userDTO) {
        ///userDTO=null;
        //userdao vede em, vede daca am mailu in db
//        if (userDao.existsEmail(userDTO.getEmail())){
//            throw new BusinessException(MessageCatalog.USER_WITH_SAME_MAIL_EXISTS);
//        }


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


    /**
     * Creates a unique user name based on the inputs.
     *
     * @param firstName the first name of the user. mandatory
     * @param lastName  the last name of the user. mandatory
     * @return a unique identifier for the input user.
     */
    //TODO Replace with logic based on the specification
    //cei din afara nu tre sa stie cum creem, de aia i privata
    private String createUserName(final String firstName, final String lastName) {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int count = 8;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public List<UserDTO> getAll() {
        return userDao.getAll()
                .stream()
                .map(userConverter::convertEntityToUserDTO)
                .collect(Collectors.toList());
    }


    public String authenticateUser(UserDTO userDTO) {
        UserEntity userEntity = userDao.findByUsername(userDTO.getUsername());
        if (userEntity != null) {
            Algorithm algorithm = Algorithm.HMAC256("harambe");
            return JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", userEntity.getUsername())
                    .withArrayClaim("roles", (String[]) userEntity.getRoles()
                            .stream()
                            .map(RoleEntity::getType).toArray(String[]::new))
                    .sign(algorithm);
        } else {
            throw new BusinessException(MessageCatalog.USER_WITH_INVALID_CREDENTIALS);
        }
    }

    public UserDTO authenticateUserByUsernameAndPassword(UserDTO userDTO) {
        UserDTO userDTOOutput = null;
        UserEntity userEntity = null;
        userEntity = userDao.findByUsername(userDTO.getUsername());
        if (userEntity != null) {
            if (userEntity.isStatus()) {
                if (userDTO.getPassword().equals(userEntity.getPassword())) {
                    userEntity.setCounter(5);
                    userDao.updateUser(userEntity);
                    return  userConverter.convertEntityToUserDTO(userEntity);
                } else {
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

            } else {
                throw new BusinessException(MessageCatalog.USER_DEACTIVATED);

            }
        }
        else
            throw new BusinessException(MessageCatalog.INCORRECT_USERNAME_OR_PASSWORD);
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
        UserEntity userEntity=null;
        if(userDao.findByUsername(inputDTO.getUsername())!=null)
             userEntity= userDao.findByUsername(inputDTO.getUsername());
        else
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


}
