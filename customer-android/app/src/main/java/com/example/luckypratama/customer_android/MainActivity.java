package com.example.luckypratama.customer_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.luckypratama.customer_android.model.Customer;
import com.example.luckypratama.customer_android.api.CustomerApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CustomerApi.BASE_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CustomerApi customerApi = retrofit.create(CustomerApi.class);

        getCustomers(customerApi, listView);

    }

    // Get customers
    public void getCustomers(CustomerApi cusApi, final ListView listView){

        Call<List<Customer>> customersCall = cusApi.getCustomers();
        customersCall.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                List<Customer> customers = response.body();
                for (Customer customer: customers) {
                    Log.d("Customer ID", String.valueOf(customer.getId()));
                    Log.d("Customer Name", customer.getName());
                    Log.d("Customer Address", customer.getAddress());
                    Log.d("Customer Phone Number", customer.getPhone_number());
                    Log.d("Customer Email", customer.getEmail());
                }

                String[] customerNames = new String[customers.size()];

                for (int i = 0; i < customers.size(); i++) {
                    customerNames[i] = customers.get(i).getName();
                }

                listView.setAdapter(
                        new ArrayAdapter<String> (
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                customerNames
                        )
                );

            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}