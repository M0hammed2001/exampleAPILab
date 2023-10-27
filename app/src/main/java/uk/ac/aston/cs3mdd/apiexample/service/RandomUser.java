package uk.ac.aston.cs3mdd.apiexample.service;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uk.ac.aston.cs3mdd.apiexample.model.UserList;

public interface RandomUser {
    // For documentation see
    // https://randomuser.me/documentation

    @GET("api/?noinfo")
    Call<UserList> getUsers(@Query("results") Integer numUsers,
                            @Query("nat") String nat);
}
