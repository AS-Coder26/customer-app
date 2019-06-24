package com.tisticswebcos.tistics_signup;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Home extends BaseActivity {

    ItemInitializor list;
    SearchView searchView;
    String queryProduct,d_token;
    private ArrayList<ItemInitializor> marrayList = new ArrayList<>();
    private RecyclerView mrecyclerView;
    private RecyclerAdapter mAdapter;
    RequestQueue requestQueue;
//    JsonObjectRequest orderRequest;
    StringRequest orderRequest;
    DataBaseHelper dataBaseHelper;
    SearchManager searchManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);
       requestQueue = Volley.newRequestQueue(this);
        dataBaseHelper = new DataBaseHelper(this);
        d_token = dataBaseHelper.getAllData();
       ConstraintLayout contentFrameLayout = (ConstraintLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
         getLayoutInflater().inflate(R.layout.activity_home, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);

        mrecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new RecyclerAdapter(marrayList, this);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
       // mrecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mrecyclerView.setAdapter(mAdapter);
        System.out.print("above prepare data");
       // prepareData();


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML



       searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView = (SearchView) findViewById(R.id.searchview);

        searchView.setQueryHint("Search Products");
         //Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); //Do not iconify the widget; expand it by default

        //return true;
      /*MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconified(false);*/

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), "Query submitted",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), searchView.getQuery(),Toast.LENGTH_SHORT).show();
                queryProduct = searchView.getQuery().toString();
                prepareData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getApplicationContext(),"query changed",Toast.LENGTH_SHORT).show();

                return false;
            }
        });



        return true;




    }

    private void prepareData()
    {

//        Toast.makeText(getApplicationContext(),"in prepare data",Toast.LENGTH_SHORT).show();
            Toast.makeText(Home.this,"Searched is "+queryProduct,Toast.LENGTH_SHORT).show();
        list = null;
        orderRequest = new StringRequest(Request.Method.GET,
                URLHelper.orders+"?product_name="+queryProduct,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Home.this,"Response is "+response,Toast.LENGTH_SHORT).show();
                            try
                            {
                                JSONArray jsonArray = new JSONArray(response);
                                if(jsonArray.length()>0)
                                {
                                    Log.d("JSOn Response",jsonArray.getJSONObject(0).toString());
                                    for(int i=0;i<jsonArray.length();i++)
                                    {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        String productName = jsonObject.getString("product_name");
                                        String productType = jsonObject.getString("product_type");
                                        int price = jsonObject.getInt("price");
                                        Log.d("Product Name",productName);
                                        Log.d("Product Type",productType);
                                        Log.d("Price",""+price);
                                        list = new ItemInitializor(productName,productType,price);
                                        marrayList.add(list);
                                    }
                                    mAdapter.notifyDataSetChanged();
                                }
                                else
                                {
                                    Toast.makeText(Home.this,"Empty JSON ARRAY",Toast.LENGTH_SHORT).show();
                                }

                            }
                            catch(Exception e){
                                System.out.print(e.toString());
                            }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home.this,"Error is"+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
          /* @Override
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("product_name", queryProduct);
                Log.d("LoginParams", "" + params);
                return params;
            }*/

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-auth",d_token) ;
                // Toast.makeText(getApplicationContext(),dataBaseHelper.getAllData(),Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),"getheaders",Toast.LENGTH_SHORT).show();

                return headers;
            }
        };
//        orderRequest = new JsonObjectRequest(Request.Method.GET, URLHelper.orders, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d("ProductAPIResponse",response.toString());
//                try {
//                    Toast.makeText(getApplicationContext(),"ProductAPIResponse"+response.toString(),Toast.LENGTH_SHORT).show();
////                    Toast.makeText(getApplicationContext(),"response",Toast.LENGTH_SHORT).show();
//
////                    JSONArray array = response.getJSONArray("products");
//                    JSONArray jsonArray = new JSONArray(response);
//                    if(jsonArray.length()>0)
//                    {
//                        for(int i=0;i<jsonArray.length();i++)
//                        {
//                            JSONObject jsonObject = jsonArray.getJSONObject(i);
//                            String productName = jsonObject.getString("product_name");
//                            String productType = jsonObject.getString("product_type");
//                            int price = jsonObject.getInt("price");
//                            Log.d("Product Name",productName);
//                            Log.d("Product Type",productType);
//                            Log.d("Price",""+price);
//                            list = new ItemInitializor(productName,productType,price);
//
//                        }
//                    }
//                    else
//                    {
//                        Log.d("API ERROR","NO DATA FROM ERROR");
//                    }
//
////                    for(int arraylength = 0; arraylength < response.length();arraylength++){
//
////                        JSONObject products = array.getJSONObject(arraylength);
////                        JSONObject products = response);
////                        list = new ItemInitializor(products.getString("product_name"),products.getString("product_type"),products.getInt("price"));
//                        //list = new ItemInitializor("silk","cotton",300);
////                        marrayList.add(list);
//
////                    }
//                    mAdapter.notifyDataSetChanged();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                    Log.d("Error is ",error.toString());
//                    Toast.makeText(Home.this,"Error is "+error.toString(),Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> params = new HashMap<>();
//                params.put("product_name", queryProduct);
//                Log.d("LoginParams", "" + params);
//                //Toast.makeText(getApplicationContext(),"getparams",Toast.LENGTH_SHORT).show();
//                return params;
//            }
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError{
//                HashMap<String, String> headers = new HashMap<>();
//                headers.put("x-auth",d_token) ;
//               // Toast.makeText(getApplicationContext(),dataBaseHelper.getAllData(),Toast.LENGTH_SHORT).show();
//                //Toast.makeText(getApplicationContext(),"getheaders",Toast.LENGTH_SHORT).show();
//
//                return headers;
//            }
//        };
        requestQueue.add(orderRequest);


        list = new ItemInitializor("Outdoor Furniture Polyester linen Fabric 1","Textile Material Fabric",100);
        marrayList.add(list);

        list = new ItemInitializor("Outdoor Furniture Polyester linen Fabric 2","Textile Material Fabric",100);
        marrayList.add(list);

        list = new ItemInitializor("Outdoor Furniture Polyester linen Fabric 3","Textile Material Fabric",100);
        marrayList.add(list);

        list = new ItemInitializor("Outdoor Furniture Polyester linen Fabric","Textile Material Fabric",100);
        marrayList.add(list);

        list = new ItemInitializor("Outdoor Furniture Polyester linen Fabric","Textile Material Fabric",100);
        marrayList.add(list);

        list = new ItemInitializor("Outdoor Furniture Polyester linen Fabric","Textile Material Fabric",100);
        marrayList.add(list);

        list = new ItemInitializor("Outdoor Furniture Polyester linen Fabric","Textile Material Fabric",100);
        marrayList.add(list);

        list = new ItemInitializor("Outdoor Furniture Polyester linen Fabric","Textile Material Fabric",100);
        marrayList.add(list);

        list = new ItemInitializor("Outdoor Furniture Polyester linen Fabric","Textile Material Fabric",100);
        marrayList.add(list);

        list = new ItemInitializor("Outdoor Furniture Polyester linen Fabric","Textile Material Fabric",100);
        marrayList.add(list);

        list = new ItemInitializor("Outdoor Furniture Polyester linen Fabric","Textile Material Fabric",100);
        marrayList.add(list);

        mAdapter.notifyDataSetChanged();

    }




}
