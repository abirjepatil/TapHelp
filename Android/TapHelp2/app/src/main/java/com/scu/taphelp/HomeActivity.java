package com.scu.taphelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
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
    private EditText email;
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
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signInBtn = (Button) findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateCredentials();
            }
        });
        forgotPwd = (TextView) findViewById(R.id.forgotPwd);
        googleSignIn = (Button) findViewById(R.id.googleSignIn);

        createAccountBtn = (Button) findViewById(R.id.createAccountBtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registerIntent);

            }
        });

        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent googlePlusLoginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(googlePlusLoginIntent);

            }
        });
    }

    private void initializeRequestQueue() {
        String url = "http://taphelp-taphelp.rhcloud.com/Login?username=" + email.getText().toString() + "&password=" + password.getText().toString();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //TextView responseMessage = new TextView(getApplicationContext());
                        //responseMessage.setText("Response is: "+ response);
                        //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        if("[AUTH01] Success\n".equalsIgnoreCase(response)) {
                            Log.i(TAG, "Authentication succeeded for user " + email.getText().toString());
                            Intent dashBoardIntent = new Intent(HomeActivity.this, DashboardActivity.class);
                            startActivity(dashBoardIntent);
                        } else {
                            //Authentication not successful
                            Log.i(TAG, "Authentication failed for user " + email.getText().toString());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        TextView errorMessage = new TextView(getApplicationContext());
                        errorMessage.setText("Error occurred during sign in!!");
                    }
            }
        );
        // Add the request to the RequestQueue.
        TapHelpRequestQueue.getInstance(this).addToRequestQueue(stringRequest);
    }

    //TODO : Implement method to validate credentials onClick of signInBtn
    public void validateCredentials() {
        initializeRequestQueue();
    }

    //TODO : Implement method to validate credentials onClick of forgotPwd
    public void retrievePassword() {

    }


    //TODO : Refactor code in LoginActivity to handle click on googleSignIn button
}
