// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package msg.user.entity.dto;

import msg.role.control.RoleControl;
import msg.user.entity.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashSet;

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
     * Converts a {@link UserInputDTO} to {@link UserEntity}.
     *
     * @param dto the input dto.
     * @return the output un-managed Entity.
     */
    public UserEntity convertInputDTOtoEntity(UserInputDTO dto) {
        final UserEntity entity = new UserEntity();
        entity
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setEmail(dto.getEmail())
                .setMobileNumber(dto.getMobileNumber())
                .setRoles(new HashSet<>());

        if (dto.getRoles() != null && !dto.getRoles().isEmpty()) {
            entity.getRoles().addAll(
                    roleControl.getRolesByTypeList(dto.getRoles()));
        }
        return entity;
    }

    public UserOutputDTO convertEntityToUserOutputDTO(UserEntity userEntity){
        final UserOutputDTO userOutputDTO = new UserOutputDTO();
        userOutputDTO.setFirstName(userEntity.getFirstName());
        userOutputDTO.setLastName(userEntity.getLastName());
        userOutputDTO.setEmail(userEntity.getEmail());
        userOutputDTO.setMobileNumber(userEntity.getMobileNumber());
        return userOutputDTO;
    }

}
