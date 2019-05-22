package msg.permission.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * The permission entity.
 *
 * @author msg systems AG;
 * @since 19.1.2
 */
@Entity
@Table(name = "permissions")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description", nullable = false)
    private String description;

    public PermissionEntity() {
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public PermissionEntity setId(long id) {
        this.id = id;
        return this;
    }

    public PermissionEntity setType(String type) {
        this.type = type;
        return this;
    }

    public PermissionEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionEntity that = (PermissionEntity) o;
        return id == that.id &&
                type.equals(that.type) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, description);
    }
}
