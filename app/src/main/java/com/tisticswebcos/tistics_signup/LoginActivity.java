package com.tisticswebcos.tistics_signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    private TextInputLayout phone_no;
    private TextInputLayout user_password;
    private Button login_btn;
    private TextView signup_link;
   /* RequestQueue requestQueue;
    StringRequest loginRequest;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_loginactivity);
        phone_no  = findViewById(R.id.text_input_phone);
        user_password = findViewById(R.id.text_input_password);
        login_btn = findViewById(R.id.btn_login);
        signup_link = findViewById(R.id.link_signup);
        signup_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });
      /*  loginRequest = new StringRequest(Request.Method.POST,
                URLHelper.login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LoginResponse",response);
                        if(response.equals("true"))
                        {

                        }
                        else if(response.equals("false")){

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LoginError",error.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("username",phone_no.getEditText().toString());
                params.put("password",phone_no.getEditText().toString());
                Log.d("Login Params",""+params);
                return params;
            }
        };*/
    }

    public void validate(View v) {
        if( !validate_Phone() || !validate_Password()) {
            Toast.makeText(getApplicationContext(),"Enter correct values ",Toast.LENGTH_SHORT).show();

        }
        else {

            login_btn.setEnabled(false);
            final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,R.style.AppTheme);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Logging in");
            progressDialog.show();
            Toast.makeText(getApplicationContext(), "Successfully Logged in", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean validate_Phone() {
        String phone = phone_no.getEditText().getText().toString();
        //get registered phone no from api and compare
        if (phone.isEmpty() || phone.length() <9 ) {
            phone_no.setError("enter a valid phone number");
            return false;
        } else {
            phone_no.setError(null);
            return true;
        }
    }

    public boolean validate_Password() {
        String password = user_password.getEditText().getText().toString();
        //get registered password from api and compare

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            user_password.setError("between 4 and 10 alphanumeric characters");
            return false;
        } else {
            user_password.setError(null);
            return true;
        }
    }
}
