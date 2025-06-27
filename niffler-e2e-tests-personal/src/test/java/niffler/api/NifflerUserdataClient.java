package niffler.api;

import niffler.dto.UserJson;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class NifflerUserdataClient {

    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(NifflerSpendService.nifflerSpendUri)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private NifflerUserdataService nifflerUserdataService = retrofit.create(NifflerUserdataService.class);

    public UserJson updateUser(UserJson userJson) throws Exception {
        return nifflerUserdataService.updateUser(userJson).execute().body();
    }
}
