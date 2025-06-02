package niffler.api;

import niffler.dto.SpendJson;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class NifflerSpendClient {

    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(NifflerSpendService.nifflerSpendUri)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private  NifflerSpendService nifflerSpendService = retrofit.create(NifflerSpendService.class);

    public SpendJson createSpend(SpendJson spendJson) throws Exception {
        return nifflerSpendService.addSpend(spendJson).execute().body();
    }
}
