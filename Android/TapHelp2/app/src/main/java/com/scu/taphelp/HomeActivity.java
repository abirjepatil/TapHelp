package com.scu.taphelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.scu.taphelp.utils.TapHelpRequestQueue;

public class HomeActivity extends AppCompatActivity {
    private ImageView tapHelpImageLogo;
    private TextView textLogo;
    private EditText userName;
    private EditText password;
    private Button signInBtn;
    private TextView forgotPwd;
    private Button googleSignIn;
    private Button createAccountBtn;

    private static String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        tapHelpImageLogo = (ImageView) findViewById(R.id.tapHelpImageLogo);
        textLogo = (TextView) findViewById(R.id.textLogo);
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        signInBtn = (Button) findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateCredentials();
            }
        });
        forgotPwd = (TextView) findViewById(R.id.forgotPwd);

        createAccountBtn = (Button) findViewById(R.id.createAccountBtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked on signup",Toast.LENGTH_LONG);
                Intent registerIntent = new Intent(getApplicationContext(), GmailRegistrationActivity.class);
                startActivity(registerIntent);

            }
        });

        googleSignIn = (Button) findViewById(R.id.googleSignIn);
        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent googlePlusLoginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(googlePlusLoginIntent);

            }
        });
    }

    private void initializeRequestQueue() {
        String url = "http://taphelp-taphelp.rhcloud.com/Login?username="+userName.getText().toString()+"&password="+password.getText().toString();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //TextView responseMessage = new TextView(getApplicationContext());
                        //responseMessage.setText("Response is: "+ response);
                        Toast.makeText(HomeActivity.this, response, Toast.LENGTH_LONG).show();
                        if("[AUTH01] Success\n".equals(response)) {
                            Intent intent = new Intent(HomeActivity.this, DashboardActivity.class);
                            startActivity(intent);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                TextView errorMessage = new TextView(getApplicationContext());
                errorMessage.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        TapHelpRequestQueue.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void validateCredentials() {
        initializeRequestQueue();
    }

    //TODO : Implement method to validate credentials onClick of forgotPwd
    public void retrievePassword() {

    }
    //TODO : Refactor code in LoginActivity to handle click on googleSignIn button
}
