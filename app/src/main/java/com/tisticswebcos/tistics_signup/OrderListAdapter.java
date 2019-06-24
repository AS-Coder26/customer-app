package com.tisticswebcos.tistics_signup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyOrderViewHolder> {

    private Context mcontext;
    private ArrayList<OrderListInitializor> arrayList = new ArrayList<>();
    public OrderListAdapter(ArrayList<OrderListInitializor> arrayList, Context context){
        this.arrayList = arrayList;
        this.mcontext = context;
    }
    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.myorderlistlayout,parent, false);
        return new MyOrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderViewHolder holder, final int position) {
            OrderListInitializor orderListInitializor = arrayList.get(position);
            holder.productName.setText(orderListInitializor.getName());
            holder.productType.setText(orderListInitializor.getProductType());
            if (orderListInitializor.getStatus().equals("pending")){
                holder.orderstatus.setTextColor(Color.parseColor("#F95F62"));
                holder.orderstatus.setText(orderListInitializor.getStatus());
               /* holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("onclicke","onclicked on");
                        Intent intentorder = new Intent(mcontext,MyOrderDetails.class);
                        intentorder.putExtra("prodname", arrayList.get(position).prodName);
                        mcontext.startActivity(intentorder);

                    }
                });*/
            }
            else if(orderListInitializor.getStatus().equals("confirm")){
                holder.orderstatus.setTextColor(Color.parseColor("#77D353"));
                holder.orderstatus.setText(orderListInitializor.getStatus());
               /* holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("onclicke","onclicked on");
                        Intent intentorder = new Intent(mcontext,MyOrderDetails.class);
                        intentorder.putExtra("prodname", arrayList.get(position).prodType);
                        mcontext.startActivity(intentorder);

                    }
                });*/
            }

            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("onclicke","onclicked on");
                    Intent intentorder = new Intent(mcontext, MyOrderDetails.class);
                    intentorder.putExtra("prodname", arrayList.get(position).prodName);
                    intentorder.putExtra("ostatus", arrayList.get(position).orderstatus);
                    mcontext.startActivity(intentorder);


                }
            });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyOrderViewHolder extends RecyclerView.ViewHolder
    {
        TextView productName, productType,orderstatus;
        ConstraintLayout parentLayout;

        public MyOrderViewHolder(View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.orderprodname);
            productType = itemView.findViewById(R.id.orderprodtype);
            orderstatus = itemView.findViewById(R.id.orderstatus);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
