package net.apkkothon.db5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UsersActivity extends AppCompatActivity {

    private TextView name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        //---initialize----------------------
        name=(TextView)findViewById(R.id.name);
        email= (TextView) findViewById(R.id.email);

        //-----method call-------------------------
        getJSONData();

    }


    private void getJSONData(){

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest("http://192.168.1.20/infinity/android5/user-json.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i=0;i<response.length();i++){

                    try {

                        JSONObject jsonObject= (JSONObject) response.get(i);

                        name.setText(jsonObject.getString("name"));
                        email.setText(jsonObject.getString("email"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(UsersActivity.this,"Something went wrong !!!!!!!",Toast.LENGTH_LONG).show();

            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);

    }



}
