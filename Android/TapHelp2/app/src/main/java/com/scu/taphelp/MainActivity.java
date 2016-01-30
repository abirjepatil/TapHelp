package com.scu.taphelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView btn=(TextView) findViewById(R.id.register);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //
                Intent myIntent = new Intent(MainActivity.this, RegisterActivity.class);
                myIntent.putExtra("key", "hello"); //Optional parameters
                MainActivity.this.startActivity(myIntent);

            }
        });



        //Button for sign in

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Move to Registration", 10).show();
                Intent myIntent = new Intent(MainActivity.this, DashboardActivity.class);
                myIntent.putExtra("key", "hello"); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });




    }


}
