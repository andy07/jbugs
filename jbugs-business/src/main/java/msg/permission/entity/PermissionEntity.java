package msg.permission.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * The permissions entity.
 *
 * @author msg systems AG;
 * @since 19.1.2
 */
@Entity
@Table(name = "permissions")
@NamedQueries({
        @NamedQuery(name = PermissionEntity.PERMISSION_FIND_ALL,
                query = "select u from PermissionEntity u"),
        @NamedQuery(name = PermissionEntity.QUERY_GET_PERMISSION_BY_TYPE,
                query = "select r from PermissionEntity r where r.type=:type ")
}
)
public class PermissionEntity {

    public static final String PERMISSION_FIND_ALL = "PermissionEntity.findAll";
    public static final String QUERY_GET_PERMISSION_BY_TYPE = "PermissionEntity.getPermissionByType";
    public static final String INPUT_TYPE_LIST = "type";

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
