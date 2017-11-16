package net.apkkothon.db5;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private EditText name,email,pass;
    private Button reg,login,jsonUsers;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //----initialize---------------
        name=(EditText)findViewById(R.id.edtText_name);
        email=(EditText)findViewById(R.id.edtText_email);
        pass=(EditText)findViewById(R.id.edtText_pass);
        reg=(Button)findViewById(R.id.btn_reg);
        login=(Button)findViewById(R.id.btn_login);
        jsonUsers= (Button) findViewById(R.id.btn_json);

        //---dialog--------------------
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("please wait....");
        progressDialog.setCancelable(false);


        //------onClick------------------------
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registration();

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //----start LoginActivity----------------------
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

        jsonUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //-----start UsersActivity---------------------------
                startActivity(new Intent(MainActivity.this,UsersActivity.class));

            }
        });


    }


    private void registration(){

        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://192.168.1.20/infinity/android5/registration.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.cancel();
                Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.cancel();
                Toast.makeText(MainActivity.this,"something went wrong !!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();

                params.put("post_name",name.getText().toString());
                params.put("post_email",email.getText().toString());
                params.put("post_pass",pass.getText().toString());

                return params;
            }
        };

        Volley.newRequestQueue(MainActivity.this).add(stringRequest);

    }


}
