package com.tisticswebcos.tistics_signup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private TextInputLayout user_name;
    private TextInputLayout phone_no;
    private TextInputLayout user_password;
    private TextView login_link;
    private Button signup_btn;
    String name,phone,password,token,  recvdToken;
    DataBaseHelper dataBaseHelper;
    RequestQueue requestQueue;
    StringRequest signupRequest;
    static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        dataBaseHelper = new DataBaseHelper(this);
        setContentView(R.layout.activity_signup);
        requestQueue = Volley.newRequestQueue(this);
        user_name = findViewById(R.id.text_input_name);
        phone_no  = findViewById(R.id.text_input_phone);
        user_password = findViewById(R.id.text_input_password);
        signup_btn = findViewById(R.id.btn_signup);
        login_link = findViewById(R.id.link_login);

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(task.isSuccessful()){
                    token = task.getResult().getToken();
                    Log.d("Token",token);

                }
                else{
                    Log.d("Token",task.getException().getMessage());
                }
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();

            }
        });
        login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),User_Profile.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });




    }

    public void validate() {
        if(!validate_UserName() || !validate_Phone() || !validate_Password()) {
            AlertDialog alertDialog = new AlertDialog.Builder(SignupActivity.this).create();
            alertDialog.setTitle("Incorrect Values");
            alertDialog.setMessage("enter correct values");

            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alertDialog.show();
            Toast.makeText(getApplicationContext(),"Enter correct values ",Toast.LENGTH_SHORT).show();

        }
        else {


            doSignUp();


            Toast.makeText(getApplicationContext(), "Successfully Registered ", Toast.LENGTH_SHORT).show();

        }
    }
    public void doSignUp() {

        Toast.makeText(getApplicationContext(), "In Do Signup ", Toast.LENGTH_SHORT).show();

        signupRequest = new StringRequest(Request.Method.POST, URLHelper.signUp, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Signup Response", response);
                if (response.equals("true")) {

                    if (dataBaseHelper.insertData(recvdToken)){
                        Toast.makeText(getApplicationContext(), dataBaseHelper.getAllData(), Toast.LENGTH_SHORT).show();

                    }
                    //dataBaseHelper.deleteAll();
                    Intent intent = new Intent(getApplicationContext(),Home.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

                } else if (response.equals("false")) {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    if (count < 3) {
                        count++;
                        doSignUp();
                    } else {
                        Toast.makeText(SignupActivity.this, "You have Weak Internet Connecton", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("phone", phone);
                params.put("password", password);
                params.put("type","customer");
                Log.d("Login Params", "" + params);
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                HashMap<String, String> headers = new HashMap<>();
                headers.put("device-token",token) ;
                return headers;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {

                recvdToken = response.headers.get("x-auth");
                Log.d("Rcvd Token",recvdToken);
               /* if (dataBaseHelper.insertData(recvdToken)){

                    Toast.makeText(getApplicationContext(), "succesfully stored", Toast.LENGTH_SHORT).show();

                }
                else{

                    Toast.makeText(getApplicationContext(), "not successful", Toast.LENGTH_SHORT).show();

                }*/
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(signupRequest);

    }
    public boolean validate_UserName() {
        name = user_name.getEditText().getText().toString();
        if (name.isEmpty() || name.length() < 3) {
            user_name.setError("at least 3 characters");
            return false;
        } else {
            user_name.setError(null);
            return true;
        }

    }

    public boolean validate_Phone() {
        phone = phone_no.getEditText().getText().toString();

        if (phone.isEmpty() || phone.length() <9 ) {
            phone_no.setError("enter a valid phone number");
            return false;
        } else {
            phone_no.setError(null);
            return true;
        }
    }

    public boolean validate_Password() {
        password = user_password.getEditText().getText().toString();
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            user_password.setError("between 4 and 10 alphanumeric characters");
            return false;
        } else {
            user_password.setError(null);
            return true;
        }
    }

    /*public void conn()
    {




                HashMap<String, String> params = new HashMap<String,String>();
                params.put("data",name); // the entered data as the JSON body.

                JsonObjectRequest jsObjRequest = new
                        JsonObjectRequest(Request.Method.POST,
                        url,
                        new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "That didn't work!", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(jsObjRequest);


    }*/
}
