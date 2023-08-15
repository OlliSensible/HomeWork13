package org.Task1.ObtainingInformationAboutUsers;

import org.Task1.Models.UserEntity;
import org.Common.DefaultBaseUrlProvider;
import org.Common.BaseUrlProvider;

import java.util.List;

public class UserListRetrieverTest {
    public static void main(String[] args) {
        BaseUrlProvider baseUrlProvider = new DefaultBaseUrlProvider();
        UserListRetriever userListRetriever = new UserListRetriever(baseUrlProvider);

        List<UserEntity> users = userListRetriever.getUsers();

        if (users != null && !users.isEmpty()) {
            System.out.println("List of users:");
            for (UserEntity user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("No users found.");
        }
    }
}
