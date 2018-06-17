package com.example.luckypratama.customer_android.api;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import com.example.luckypratama.customer_android.model.Customer;

public interface CustomerApi {

    String BASE_PATH = "http://192.168.43.224:8090/";

    // Get customers
    @GET("customers")
    Call<List<Customer>> getCustomers();

}
