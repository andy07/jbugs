package msg.role.entity.dto;

import msg.permission.entity.dto.PermissionDTO;

import java.util.Set;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class RoleDTO {


    private String type;

    private Set<PermissionDTO> permissions;

    public RoleDTO() {
    }

    public String getType() {
        return type;
    }

    public Set<PermissionDTO> getPermissions() {
        return permissions;
    }

    public RoleDTO setType(String type) {
        this.type = type;
        return this;
    }

    public RoleDTO setPermissions(Set<PermissionDTO> permissions) {
        this.permissions = permissions;
        return this;
    }
}
