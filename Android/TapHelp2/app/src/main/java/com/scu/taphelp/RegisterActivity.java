package com.scu.taphelp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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


public class RegisterActivity
        extends
            AppCompatActivity
        implements
            OnItemSelectedListener,
            MultiSelectionSpinner.OnMultipleItemsSelectedListener {

    private TextView registerLogo;
    private EditText firstName;
    private EditText lastName;
    private EditText password;
    private EditText confirmPassword;
    private EditText phoneNumber;
    private EditText zipCode;
    private EditText address;
    private Spinner userType;
    private MultiSelectionSpinner serviceType;
    private Button submit;

    private String user_type;

    private List<String> service_type = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        intializeView();
    }

    private void intializeView() {
        registerLogo = (TextView) findViewById(R.id.registerText);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);

        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);

        if(!matchPassWords(password.getText().toString(),confirmPassword.getText().toString())){
            Toast.makeText(this,"The passwords you entered does not match!", Toast.LENGTH_LONG).show();
        }
        phoneNumber = (EditText) findViewById(R.id.phone);
        zipCode = (EditText) findViewById(R.id.zipCode);
        address = (EditText) findViewById(R.id.address);

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

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUserToDB();
            }
        });
    }

    @Override
    public void selectedStrings(List<String> strings) {
        //Toast.makeText(this, strings.toString(), Toast.LENGTH_LONG).show();
        service_type.addAll(strings);
    }

    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    private void initializeRequestQueue() {
        String url = "http://taphelp-taphelp.rhcloud.com/Register?UID=3&USER_NAME=test&EMAIL_ID=test&PASSWORD=23456&USER_TYPE=2&FIRST_NAME=at&LAST_NAME=nt&ADDRESS=SantaClara&PINCODE=123456";

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
            } else {
                serviceType.setVisibility(View.VISIBLE);
            }
            user_type = spinner.getItemAtPosition(pos).toString();
        }
//        else if(spinner.getId() == R.id.serviceType)
//        {
//            service_type.add(spinner.getItemAtPosition(pos).toString());
//        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
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
    }
}
