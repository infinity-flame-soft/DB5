package net.apkkothon.db5;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText email,pass;
    private Button btnLogin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //------initialize----------------------
        email= (EditText) findViewById(R.id.email);
        pass= (EditText) findViewById(R.id.pass);
        btnLogin= (Button) findViewById(R.id.btn_login);

        //----progress Dialog--------------------
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait......");
        progressDialog.setTitle("Login");

        //----check login status----------------
        SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
        if (preferences.getBoolean("login_status",false)){

            //-------new Activity---------------
            Toast.makeText(LoginActivity.this,"Already login",Toast.LENGTH_LONG).show();
        }


        //-----onClick----------------------------
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login(){

        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://192.168.1.20/infinity/android5/login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.cancel();

                if (response.equals("success")){

                    SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("login_status",true);
                    editor.apply();

                }

                Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.cancel();
                Toast.makeText(LoginActivity.this,"Something went wrong ! try later",Toast.LENGTH_LONG).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();

                params.put("post_email",email.getText().toString());
                params.put("post_pass",pass.getText().toString());

                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);

    }
}
