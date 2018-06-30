package com.example.luckypratama.customer_android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luckypratama.customer_android.models.CustomerItem;
import com.example.luckypratama.customer_android.R;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter <CustomerAdapter.CustomerViewHolder> {

    private ArrayList<CustomerItem> customerList;

    public static class CustomerViewHolder extends RecyclerView.ViewHolder {
        private TextView id;
        private TextView name;
        private TextView address;
        private TextView phone_number;
        private TextView email;

        public CustomerViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.cusID);
            name = itemView.findViewById(R.id.name);
//            address =  itemView.findViewById(R.id.address);
////            phone_number = itemView.findViewById(R.id.phone);
////            email = itemView.findViewById(R.id.email);
        }
    }

    public CustomerAdapter(ArrayList<CustomerItem> customerList) {
        this.customerList = customerList;
    }

    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item, parent, false);
        CustomerViewHolder cusViewHolder = new CustomerViewHolder(v);
        return cusViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomerAdapter.CustomerViewHolder holder, int position) {
        holder.id.setText("ID: "+String.valueOf(customerList.get(position).getId()));
        holder.name.setText("Name: "+customerList.get(position).getName());
//        holder.address.setText("Address: "+customerList.get(position).getAddress());
//        holder.phone_number.setText("Phone Number: "+customerList.get(position).getPhone_number());
//        holder.email.setText("Email: "+customerList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }
}