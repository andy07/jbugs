package msg.role.entity.dto;

import msg.permission.entity.PermissionEntity;
import msg.permission.entity.dto.PermissionConverter;
import msg.permission.entity.dto.PermissionDTO;
import msg.role.control.RoleControl;
import msg.role.entity.RoleEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.Set;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Stateless
public class RoleConverter {


    @EJB
    private PermissionConverter permissionConverter;

    @EJB
    private RoleControl roleControl;

    public RoleEntity convertDTOToEntity(RoleDTO roleDTO){


        RoleEntity roleEntity=roleControl.getRoleByType(roleDTO.getType());
        Set<PermissionEntity> permissions= new HashSet<>();
        roleDTO.getPermissions().forEach(s-> permissions.add(permissionConverter.convertDTOToEntity(s)));

        return roleEntity
                .setPermissions(permissions);
    }


    public RoleDTO convertEntityToDTO(RoleEntity roleEntity){
        Set<PermissionDTO> permissions= new HashSet<>();
        roleEntity.getPermissions().forEach(s-> permissions.add(permissionConverter.convertEntityToDTO(s)));

        return new RoleDTO()
                .setType(roleEntity.getType())
                .setPermissions(permissions);
    }
}
