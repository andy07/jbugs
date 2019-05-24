// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package msg.user;

import msg.exeptions.ExceptionMessage;

/**
 * A catalog of all the Messages thrown from the User component.
 *
 * @author msg-system ag;  Daniel Donea
 * @since 1.0
 */
public class MessageCatalog {

    /** A message for the case when a user already exists with the same email. */
    public static final ExceptionMessage USER_WITH_SAME_MAIL_EXISTS
            = new ExceptionMessage("USER-01", "A user with the same email already exists.");
    public static final ExceptionMessage USER_WITH_INVALID_CREDENTIALS
            = new ExceptionMessage("USER-02", "A user with invalid credential");
    public static final ExceptionMessage USER_DEACTIVATED
            = new ExceptionMessage("USER-03", "User is deactivated");
    public static final ExceptionMessage INCORRECT_USERNAME_OR_PASSWORD
            = new ExceptionMessage("USER-04", "The username or password is incorrect");
}
