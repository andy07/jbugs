package msg.user.entity.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDTO {


    private Optional<String> firstName = Optional.empty();

    private Optional<String> lastName = Optional.empty();

    private Optional<String> email = Optional.empty();

    private Optional<String> mobileNumber = Optional.empty();

    private Optional<Boolean> status = Optional.empty();

    private Optional<String> username = Optional.empty();

    private Optional<String> password = Optional.empty();

    private Optional<List<String>> roles = Optional.empty();


    public UserDTO() {
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = Optional.of(firstName);
        return this;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = Optional.of(lastName);;
        return this;
    }

    public UserDTO setEmail(String email) {
        this.email = Optional.of(email);;
        return this;
    }

    public UserDTO setMobileNumber(String mobileNumber) {
        this.mobileNumber = Optional.of(mobileNumber);
        return this;
    }

    public UserDTO setStatus(boolean status) {
        this.status = Optional.of(status);
        return this;
    }

    public UserDTO setUsername(String username) {
        this.username = Optional.of(username);
        return this;
    }

    public UserDTO setPassword(String password) {
        this.password = Optional.of(password);
        return this;
    }

    public UserDTO setRoles(List<String> roles) {
        this.roles = Optional.of(roles);
        return this;
    }

    public String getFirstName() {
        return firstName.orElse("");
    }


    public String getLastName() {
        return lastName.orElse(null);
    }

    public String getEmail() {
        if (email.isPresent())
            return email.get();
        return "";
    }

    public String getMobileNumber() {
        if (mobileNumber.isPresent())
            return mobileNumber.get();
        return "";
    }

    public boolean getStatus() {
        if (status.isPresent())
            return status.get();
        return false;
    }

    public String getUsername() {
        if (username.isPresent())
            return username.get();
        return "";
    }

    public String getPassword() {
        if (password.isPresent())
            return password.get();
        return "";
    }

    public List<String> getRoles() {
        if (roles.isPresent())
            return roles.get();
        return new ArrayList<>();
    }
}
