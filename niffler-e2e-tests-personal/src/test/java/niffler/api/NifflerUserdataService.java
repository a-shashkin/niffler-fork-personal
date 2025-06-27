package niffler.api;

import niffler.dto.UserJson;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NifflerUserdataService {

    String nifflerUserdataUri = "http://127.0.0.1:8089";

    @POST("/internal/users/update")
    Call<UserJson> updateUser(@Body UserJson userJson);
}
