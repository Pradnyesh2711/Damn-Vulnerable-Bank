package com.app.damnvulnerablebank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApproveBeneficiary extends AppCompatActivity {
    TextView hey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvebenificiary);
        hey=findViewById(R.id.accountid);
        Intent i =getIntent();
        String we=i.getStringExtra("id");
        hey.setText(we);
    }

 public void approveBeneficiary(View view){
     SharedPreferences sharedPreferences = getSharedPreferences("jwt", Context.MODE_PRIVATE);
     final String retrivedToken  = sharedPreferences.getString("accesstoken",null);
     EditText ed=findViewById(R.id.accountid1);
     int n = Integer.parseInt(ed.getText().toString());

     final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
     JSONObject requestData = new JSONObject();
     JSONObject requestDataEncrypted = new JSONObject();
     try {
         //input your API parameters
         requestData.put("id",n);

         // Encrypt data before sending
         requestDataEncrypted.put("enc_data", EncryptDecrypt.encrypt(requestData.toString()));

     } catch (JSONException e) {
         e.printStackTrace();
     }
     // Enter the correct url for your api service site
     sharedPreferences = getSharedPreferences("apiurl", Context.MODE_PRIVATE);
     final String url  = sharedPreferences.getString("apiurl",null);
     String endpoint="/api/beneficiary/approve";
     String finalurl = url+endpoint;
     final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, finalurl, requestDataEncrypted,
             new Response.Listener<JSONObject>() {
                 @Override
                 public void onResponse(JSONObject response) {


                     try {
                         Toast.makeText(getApplicationContext(),""+EncryptDecrypt.decrypt(response.get("enc_data").toString()), Toast.LENGTH_SHORT).show();
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }


                     startActivity(new Intent(ApproveBeneficiary.this, Dashboard.class));

                 }
             }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {
             Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
         }
     }){
         @Override
         public Map getHeaders() throws AuthFailureError {
             HashMap headers=new HashMap();
             headers.put("Authorization","Bearer "+retrivedToken);
             return headers;
         }


     };
     requestQueue.add(jsonObjectRequest);
 }

}