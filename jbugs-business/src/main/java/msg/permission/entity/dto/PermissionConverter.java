package msg.permission.entity.dto;

import msg.permission.control.PermissionControl;
import msg.permission.entity.PermissionEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Stateless
public class PermissionConverter {

    @EJB
    private PermissionControl permissionControl;

    public PermissionEntity convertDTOToEntity(PermissionDTO permissionDTO){
        return new PermissionEntity()
                .setType(permissionDTO.getType())
                .setDescription(permissionDTO.getDescription());
    }

    public PermissionDTO convertEntityToDTO(PermissionEntity permissionEntity){
        return new PermissionDTO()
                .setType(permissionEntity.getType())
                .setDescription(permissionEntity.getDescription());
    }
}
