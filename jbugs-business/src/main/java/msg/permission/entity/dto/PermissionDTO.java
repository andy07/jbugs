package msg.permission.entity.dto;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class PermissionDTO {


    private String type;

    private String description;

    public PermissionDTO() {
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public PermissionDTO setType(String type) {
        this.type = type;
        return this;
    }

    public PermissionDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
