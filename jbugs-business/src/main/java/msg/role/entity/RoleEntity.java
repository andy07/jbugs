package msg.role.entity;

import msg.permission.entity.PermissionEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The Roles Entity.
 *
 * @author msg systems AG;
 * @since 19.1.2
 */
@Entity
@Table(name = "roles")
@NamedQueries({
        @NamedQuery(name = RoleEntity.QUERY_GET_PERMISION,
                query = "select r.permissions from RoleEntity r where r.type=:type "),
        @NamedQuery(name = RoleEntity.QUERY_GET_ROLE_BY_TYPE,
                query = "select r from RoleEntity r where r.type=:type "),
        @NamedQuery(name = RoleEntity.QUERY_GET_ROLES_BY_TYPE_LIST,
                query = "select r from RoleEntity r "
                        + "where r.type in :" + RoleEntity.INPUT_TYPE_LIST),
        @NamedQuery(name = RoleEntity.ROLE_FIND_ALL,
                query = "select u from RoleEntity u")}
)
public class RoleEntity {

    public static final String QUERY_GET_ROLES_BY_TYPE_LIST = "getRolesByTypeList";
    public static final String QUERY_GET_ROLE_BY_TYPE = "getRoleByType";
    public static final String INPUT_TYPE_LIST = "type";
    public static final String QUERY_GET_PERMISION = "getPermissions";
    public static final String ROLE_FIND_ALL = "RoleEntity.findAll";


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type", unique = true, nullable = false)
    private String type;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "permissions_roles",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id", nullable = false)
    )
    private Set<PermissionEntity> permissions = new HashSet<>();


    public RoleEntity() {
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Set<PermissionEntity> getPermissions() {
        return permissions;
    }

    public RoleEntity setId(long id) {
        this.id = id;
        return this;
    }

    public RoleEntity setType(String type) {
        this.type = type;
        return this;
    }

    public RoleEntity setPermissions(Set<PermissionEntity> permissions) {
        this.permissions = permissions;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity role = (RoleEntity) o;
        return Objects.equals(type, role.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }


}
