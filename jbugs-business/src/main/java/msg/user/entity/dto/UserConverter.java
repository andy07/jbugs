// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package msg.user.entity.dto;

import msg.exeptions.BusinessException;
import msg.role.control.RoleControl;
import msg.role.entity.RoleEntity;
import msg.user.MessageCatalog;
import msg.user.entity.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Converts different DOs to UserEntity and vice-versa.
 *
 * @author msg-system ag;  Daniel Donea
 * @since 1.0
 */
@Stateless
public class UserConverter {

    @EJB
    private RoleControl roleControl;

    /**
     * Converts a {@link UserDTO} to {@link UserEntity}.
     *
     * @param userDTO the input dto.
     * @return the output un-managed Entity.
     */
    public UserEntity convertUserDTOtoEntity(UserDTO userDTO) {
        final UserEntity entity = new UserEntity();

        entity.setFirstName(userDTO.getFirstName())
                .setLastName(userDTO.getLastName())
                .setEmail(userDTO.getEmail())
                .setMobileNumber(userDTO.getMobileNumber())
                .setStatus(userDTO.getStatus())
                .setUsername(userDTO.getUsername())
                .setPassword(userDTO.getPassword());


        Set<RoleEntity> roleEntitySet = new HashSet<>();



            for(String roleType: userDTO.getRoles()){
                RoleEntity roleEntity=roleControl.getRoleByType(roleType);
                if(roleEntity != null){
                    roleEntitySet.add(roleEntity);
                }else{
                    throw new BusinessException(MessageCatalog.NO_SUCH_ROLE_EXISTS);
                }

            }

            entity.setRoles(roleEntitySet);


//        entity.getRoles().addAll(
//                    roleControl.getRolesByTypeList(userDTO.getRoles()));

        return entity;
    }

    public UserDTO convertEntityToUserDTO(UserEntity userEntity){

        final UserDTO userDTO = new UserDTO()
                .setFirstName(userEntity.getFirstName())
                .setLastName(userEntity.getLastName())
                .setEmail(userEntity.getEmail())
                .setMobileNumber(userEntity.getMobileNumber())
                .setStatus(userEntity.isStatus())
                .setUsername(userEntity.getUsername())
                .setPassword(userEntity.getPassword());

        List<String> roles = new ArrayList<>();

        for(RoleEntity roleEntity: userEntity.getRoles()){
            roles.add(roleEntity.getType());
        }
        userDTO.setRoles(roles);
        return userDTO;
    }

}
