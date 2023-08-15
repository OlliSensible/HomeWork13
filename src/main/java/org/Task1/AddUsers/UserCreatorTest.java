package org.Task1.AddUsers;

import com.google.gson.Gson;
import org.Task1.Models.UserEntity;
import org.Task1.Models.UsersExample;

public class UserCreatorTest {
    public static void main(String[] args) {
        UserEntity newUser = UsersExample.createUserExample();

        Gson gson = new Gson();
        String newUserJson = gson.toJson(newUser);
        System.out.println(newUserJson);
    }
}