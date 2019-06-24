package com.tisticswebcos.tistics_signup;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.MyViewHolder> {

    private Context orderContext;
    private ArrayList<ItemInitializor> arrayList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<ItemInitializor> arrayList, Context orderContext)
    {
        this.arrayList = arrayList;
        this.orderContext = orderContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.v("oncreateviewholder","oncreateviewholder");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        Log.v("onbindviewholder","onbindviewholder");
        ItemInitializor itemInitializor = arrayList.get(position);
        holder.productName.setText(itemInitializor.getName());
        holder.productType.setText(itemInitializor.getProductType());
        holder.price.setText(Integer.toString(itemInitializor.getPrice()));

        holder.allorderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allorderIntent = new Intent(orderContext,ProductDetails.class);
                allorderIntent.putExtra("productname", arrayList.get(position).prodName);
                orderContext.startActivity(allorderIntent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView recyclerImage;
        ConstraintLayout allorderLayout;
        TextView productName, productType, price;
        public MyViewHolder(View itemView) {
            super(itemView);

            //recyclerImage = itemView.findViewById(R.id.recyclerImage);
            productName = itemView.findViewById(R.id.productName);
            productType = itemView.findViewById(R.id.productType);
            price = itemView.findViewById(R.id.price);
            allorderLayout = itemView.findViewById(R.id.allorderlayout);
        }
    }
}
