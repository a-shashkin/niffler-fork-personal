package niffler.retrofit;

import niffler.dto.SpendModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SpendApiClient {

    @POST("api/spends/add")
    Call<SpendModel> createSpend(@Body SpendModel spendModel);
}
