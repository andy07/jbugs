// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package msg.user.entity.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * A representation of the User, used for creating a user.
 *
 * @author msg-system ag;  Daniel Donea
 * @since 1.0
 */
public class UserInputDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private int counter;
    private List<String> roles=new ArrayList<>();

    public UserInputDTO() {
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

    public int getCounter() {
        return counter;
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserInputDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserInputDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserInputDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserInputDTO setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public UserInputDTO setCounter(int counter) {
        this.counter = counter;
        return this;
    }

    public UserInputDTO setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
}
