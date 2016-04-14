package com.scu.taphelp;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.scu.taphelp.custom.MultiSelectionSpinner;
import com.scu.taphelp.utils.TapHelpRequestQueue;

import java.util.ArrayList;
import java.util.List;


public class NativeRegistrationActivity extends AppCompatActivity implements OnItemSelectedListener, MultiSelectionSpinner.OnMultipleItemsSelectedListener {

    protected TextView registerText;
    protected TextInputLayout userName;
    protected TextInputLayout firstName;
    protected TextInputLayout lastName;
    protected TextInputLayout emailId;
    protected TextInputLayout password;
    protected TextInputLayout confirmPassword;
    protected TextInputLayout phoneNumber;
    protected TextInputLayout zipCode;
    protected TextInputLayout address;
    protected Spinner userType;
    protected MultiSelectionSpinner serviceType;
    protected Button register;

    protected String user_type;
    private String auth_type;

    protected List<String> service_type = new ArrayList<String>();
    private static StringBuffer services;
    protected String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        intializeView();
    }

    private void intializeView() {
        registerText = (TextView) findViewById(R.id.registerText);
        firstName = (TextInputLayout) findViewById(R.id.input_layout_firstName);
        lastName = (TextInputLayout) findViewById(R.id.input_layout_lastName);
        userName = (TextInputLayout) findViewById(R.id.input_layout_userName);
        emailId = (TextInputLayout) findViewById(R.id.input_layout_email);

        password = (TextInputLayout) findViewById(R.id.input_layout_password);
        confirmPassword = (TextInputLayout) findViewById(R.id.input_layout_confirmPassword);
        if(!matchPassWords(password.getEditText().getText().toString(),confirmPassword.getEditText().getText().toString())){
            Toast.makeText(this,"The passwords you entered does not match!", Toast.LENGTH_LONG).show();
        }

        phoneNumber = (TextInputLayout) findViewById(R.id.input_layout_phone);
        zipCode = (TextInputLayout) findViewById(R.id.input_layout_zipCode);
        address = (TextInputLayout) findViewById(R.id.input_layout_postalAddress);

        userType = (Spinner) findViewById(R.id.userType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_type, android.R.layout.simple_spinner_item);
        userType.setOnItemSelectedListener(this);
        userType.setPrompt("Select User Type");
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.radio_select_spinner_dropdown_item);
        // Apply the adapter to the spinner
        userType.setAdapter(adapter);


        //Get UI element for multi selection spinner
        serviceType = (MultiSelectionSpinner) findViewById(R.id.serviceType);
        //Get all strings from string array defined in strings.xml for service type
        serviceType.setItems(getResources().getStringArray(R.array.service_type));
        serviceType.setPrompt("Select Service Type");
        //Initially first item is selected
        serviceType.setSelection(new int[]{0});
        //Set the listener
        serviceType.setListener(this);

        if(userType.getSelectedItem().toString().equalsIgnoreCase("Service Receiver")) {
            serviceType.setVisibility(View.GONE);
        }

        register = (Button) findViewById(R.id.submit);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUserToDB();
            }
        });
    }

    @Override
    public void selectedStrings(List<String> strings) {
           if(strings != null){
               services = new StringBuffer();
               for(int i=0;i<strings.size();i++){
                   if(i<strings.size()-1){
                       services.append(strings.get(i)+",");
                   }else{
                       services.append(strings.get(i));
                   }
               }
           }
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    public String getURL() {
        throw new UnsupportedOperationException();
    }

    private void initializeRequestQueue() {
        //TBD : Change AuthType Logic Later
        if(user_type == "2" && services==null){
            Toast.makeText(this, "Please choose atleast one service!", Toast.LENGTH_LONG);
        }
        String url = new StringBuffer().append("http://taphelp-taphelp.rhcloud.com/Register?USER_NAME=")
                .append(userName.getEditText().getText().toString()).append("&EMAIL_ID=").append(emailId.getEditText().getText().toString()).append("&PASSWORD=").append(password.getEditText().getText().toString())
                .append("&USER_TYPE=").append(user_type).append("&FIRST_NAME=")
                .append(firstName.getEditText().getText().toString()).append("&LAST_NAME=").append(lastName.getEditText().getText().toString()).append("&ADDRESS=")
                .append(address.getEditText().getText().toString()).append("&PINCODE=").append(zipCode.getEditText().getText().toString())
                .append("&PHONENO=").append(phoneNumber.getEditText().getText().toString()).append("&SERVICES=")
                .append(services).append("&AUTHTYPE=").append(auth_type).toString();

        //String url = getURL();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //TextView responseMessage = new TextView(getApplicationContext());
                        //responseMessage.setText("Response is: "+ response);
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
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

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.userType)
        {
            if(spinner.getItemAtPosition(pos).toString().equalsIgnoreCase("Service Receiver")) {
                serviceType.setVisibility(View.GONE);
                user_type = "1";
                auth_type = "1";
                services = new StringBuffer().append("NULL");
            } else {
                serviceType.setVisibility(View.VISIBLE);
                user_type = "2";
                auth_type = "0";
            }
            //user_type = spinner.getItemAtPosition(pos).toString();
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Please choose at least one service!", Toast.LENGTH_LONG);
    }

    public boolean matchPassWords(String password,String confirmPassword)
    {
        boolean pstatus = false;
        if (confirmPassword != null && password != null)
        {
            if (password.equals(confirmPassword))
            {
                pstatus = true;
            }
        }
        return pstatus;
    }

    //TODO : Insert all the details to DB
    public void insertUserToDB(){
        initializeRequestQueue();
    }
}
