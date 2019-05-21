package msg.user.entity.dto;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class UserOutputDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;

    public UserOutputDTO() {
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

    public UserOutputDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserOutputDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserOutputDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserOutputDTO setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }
}
