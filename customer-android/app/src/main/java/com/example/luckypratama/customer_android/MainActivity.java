package com.example.luckypratama.customer_android;

import android.os.Bundle;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomerAdapter adapter;
    private ArrayList<CustomerItem> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CustomerApi.BASE_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CustomerApi request = retrofit.create(CustomerApi.class);
        Call<ArrayList<CustomerItem>> call = request.getJSON();
        call.enqueue(new Callback<ArrayList<CustomerItem>>() {
            @Override
            public void onResponse(Call<ArrayList<CustomerItem>> call, Response<ArrayList<CustomerItem>> response) {
                ArrayList<CustomerItem> cusResponse = response.body();
                data = new ArrayList<>(cusResponse);
                adapter = new CustomerAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<CustomerItem>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}