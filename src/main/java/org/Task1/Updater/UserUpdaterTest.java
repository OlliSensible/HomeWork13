package org.Task1.Updater;

import org.Task1.Models.UserEntity;
import org.Common.DefaultBaseUrlProvider;
import org.Common.BaseUrlProvider;

public class UserUpdaterTest {
    public static void main(String[] args) {
        BaseUrlProvider baseUrlProvider = new DefaultBaseUrlProvider();
        UserUpdater userUpdater = new UserUpdater(baseUrlProvider);

        int userIdToUpdate = 1;

        UserEntity updatedUser = userUpdater.updateUser(userIdToUpdate);

        if (updatedUser != null) {
            System.out.println("User with id " + userIdToUpdate + " has been updated.");
            System.out.println("Updated User: " + updatedUser);
        } else {
            System.out.println("User update failed for id " + userIdToUpdate + ".");
        }
    }
}
