package uk.ac.aston.cs3mdd.apiexample.service;

import retrofit2.Call;
import uk.ac.aston.cs3mdd.apiexample.model.UserList;

public class UserRepository {
    private RandomUser randomUserService;

    public UserRepository(RandomUser userService) {
        this.randomUserService = userService;
    }

    public Call<UserList> getListOfUsers(int numUsers, String nationality) {
        return randomUserService.getUsers(numUsers, nationality);
    }
}
