package com.example.luckypratama.customer_android;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.ArrayList;

public interface CustomerApi {

//  Change the ip to yours
    String BASE_PATH = "http://192.168.0.17:8090/";

    @GET("customers")
    Call<ArrayList<CustomerItem>> getJSON();
}