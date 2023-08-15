package org.Task1.InfirmationOfUsername;

import org.Common.BaseUrlProvider;
import org.Common.DefaultBaseUrlProvider;
import org.Task1.Models.UserEntity;

import java.util.Optional;

public class UserByUsernameRetrieverTest {
    public static void main(String[] args) {
        BaseUrlProvider baseUrlProvider = new DefaultBaseUrlProvider();

        UserByUsernameRetriever userByUsernameRetriever = new UserByUsernameRetriever(baseUrlProvider);

        String usernameToRetrieve = "Samantha";

        Optional<UserEntity> userOptional = userByUsernameRetriever.getUserByUsername(usernameToRetrieve);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            System.out.println("User with username " + usernameToRetrieve + ": " + user);
        } else {
            System.out.println("User with username " + usernameToRetrieve + " not found.");
        }
    }
}
