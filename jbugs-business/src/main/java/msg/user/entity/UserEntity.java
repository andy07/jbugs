package msg.user.entity;

import msg.role.entity.RoleEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The User entity.
 *
 * @author msg systems AG; UserEntity Name.
 * @since 19.1.2
 */
@Entity
@Table(name="users")
@NamedQueries({
        @NamedQuery(name = UserEntity.USER_COUNT_BY_EMAIL,
                query = "SELECT count(u) from UserEntity u where u.email = :" + UserEntity.EMAIL),
        @NamedQuery(name = UserEntity.USER_FIND_BY_EMAIL,
                query = "SELECT u from UserEntity u where u.email = :" + UserEntity.EMAIL),
        @NamedQuery(name = UserEntity.USER_FIND_ALL,
                query = "select u from UserEntity u"),
        @NamedQuery(name = UserEntity.USER_FIND_BY_USERNAME,
                query = "SELECT u from UserEntity u where u.username = :" + UserEntity.USERNAME),
        @NamedQuery(name = UserEntity.FIND_USERNAMES,
                query = "SELECT u from UserEntity u where u.username like :" + UserEntity.USERNAME),
        @NamedQuery(name = UserEntity.USER_COUNT_BY_USERNAME,
                query = "SELECT count(u) from UserEntity u where u.username = :" + UserEntity.USERNAME),



})
public class UserEntity{
    public static final String USER_FIND_ALL = "UserEntity.findAll";
    public static final String USER_FIND_BY_EMAIL = "UserEntity.findByEmail";
    public static final String USER_COUNT_BY_EMAIL = "UserEntity.countByEmail";
    public static final String USER_COUNT_BY_USERNAME = "UserEntity.countByUsername";
    public static final String USER_FIND_BY_USERNAME = "UserEntity.findByUsername";
    public static final String FIND_USERNAMES = "UserEntity.findUsernames";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)//todo: @Pattern
    private String email;

    @Column(name = "mobile_number", unique = true, nullable = false)
    private String mobileNumber;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="counter")
    private int counter;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToMany(cascade= CascadeType.PERSIST)
    @JoinTable(name="users_roles",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id",nullable = false),
            inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id",nullable = false)
    )
    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity() {
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getCounter() {
        return counter;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public boolean isStatus() {
        return status;
    }

    public UserEntity setId(long id) {
        this.id = id;
        return this;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserEntity setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserEntity setCounter(int counter) {
        this.counter = counter;
        return this;
    }

    public UserEntity setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public UserEntity setStatus(boolean status) {
        this.status = status;
        return this;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity userEntity = (UserEntity) o;
        return counter == userEntity.counter &&
                Objects.equals(firstName, userEntity.firstName) &&
                Objects.equals(lastName, userEntity.lastName) &&
                Objects.equals(email, userEntity.email) &&
                Objects.equals(mobileNumber, userEntity.mobileNumber) &&
                Objects.equals(username, userEntity.username) &&
                Objects.equals(password, userEntity.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, mobileNumber, username, password, counter);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", counter=" + counter +
                ", status=" + status +
                ", roles=" + roles +
                '}';
    }

}

