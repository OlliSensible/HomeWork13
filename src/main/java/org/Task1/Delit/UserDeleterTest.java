package org.Task1.Delit;

import org.Common.DefaultBaseUrlProvider;
import org.Common.BaseUrlProvider;

import java.io.IOException;

public class UserDeleterTest {
    public static void main(String[] args) {
        BaseUrlProvider baseUrlProvider = new DefaultBaseUrlProvider();
        UserDeleter userDeleter = new UserDeleter(baseUrlProvider);

        int userIdToDelete = 1;

        try {
            int responseCode = userDeleter.getResponseCodeAfterDelete(userIdToDelete);
            System.out.println("HTTP Response Code after deleting user with id " + userIdToDelete + ": " + responseCode + ".");
        } catch (IOException e) {
            System.out.println("An error occurred while trying to delete user with id " + userIdToDelete + ".");
            e.printStackTrace();
        }
    }
}
