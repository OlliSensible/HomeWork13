package org.Task1.InfirmationOfID;

import org.Common.DefaultBaseUrlProvider;
import org.Common.BaseUrlProvider;
import org.Task1.Models.UserEntity;

import java.util.Optional;

public class UserByIdRetrieverTest {
    public static void main(String[] args) {
        BaseUrlProvider baseUrlProvider = new DefaultBaseUrlProvider();
        UserByIdRetriever userByIdRetriever = new UserByIdRetriever(baseUrlProvider);

        int userIdToRetrieve = 2;

        Optional<UserEntity> userOptional = userByIdRetriever.getUserById(userIdToRetrieve);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            System.out.println("User with id " + userIdToRetrieve + ": " + user + ".");
        } else {
            System.out.println("User with id " + userIdToRetrieve + " not found.");
        }
    }
}
