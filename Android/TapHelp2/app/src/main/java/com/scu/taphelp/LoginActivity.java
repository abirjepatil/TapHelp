package com.scu.taphelp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

public class LoginActivity extends Activity implements OnClickListener, ConnectionCallbacks, OnConnectionFailedListener{
    private static final int RC_SIGN_IN = 0;

    private static final int MY_PERMISSIONS_REQUEST_GET_ACCOUNT = 9999;

    public static String TAG = "LoginActivity";
    private GoogleApiClient mGoogleApiClient;
    private boolean mIntentInProgress;
    private boolean signedInUser;
    private ConnectionResult mConnectionResult;
    private SignInButton signinButton;
    private ImageView image;
    private TextView username, emailLabel;
    private LinearLayout profileFrame, signinFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signinButton = (SignInButton) findViewById(R.id.signin);
        signinButton.setOnClickListener(this);
        image = (ImageView) findViewById(R.id.image);
        username = (TextView) findViewById(R.id.username);
        emailLabel = (TextView) findViewById(R.id.email);
        profileFrame = (LinearLayout) findViewById(R.id.profileFrame);
        signinFrame = (LinearLayout) findViewById(R.id.signinFrame);
        mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Plus.API, Plus.PlusOptions.builder().build()).addScope(Plus.SCOPE_PLUS_LOGIN).build();
    }

    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        Log.i(TAG, "Connected to Google API client.");
    }

    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        Log.i(TAG, "Disconnected from Google API client.");
    }

    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this, 0).show();
            return;
        }
        if (!mIntentInProgress) {
            // store mConnectionResult
            mConnectionResult = result;
            if (signedInUser) {
                resolveSignInError();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        switch (requestCode) {
            case RC_SIGN_IN:
                if (responseCode == RESULT_OK) {
                    signedInUser = false;
                }
                mIntentInProgress = false;
                if (!mGoogleApiClient.isConnecting()) {
                    mGoogleApiClient.connect();
                }
                break;
        }
    }

    @Override
    public void onConnected(Bundle arg0) {
        signedInUser = false;
        Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show();
        getProfileInformation();
        updateProfile(true);
    }

    private void updateProfile(boolean isSignedIn) {
        if (isSignedIn) {
            signinFrame.setVisibility(View.GONE);
            profileFrame.setVisibility(View.VISIBLE);
           // Intent dashBoardIntent = new Intent(LoginActivity.this, DashboardActivity.class);
           // startActivity(dashBoardIntent);
        } else {
            signinFrame.setVisibility(View.VISIBLE);
            profileFrame.setVisibility(View.GONE);
        }
    }

    private void getProfileInformation() {
        try {
            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);

                /**
                 * Runtime permissions for reading account details (because API versions >= 23 need to use runtime permissions)
                 */
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.GET_ACCOUNTS)
                        != PackageManager.PERMISSION_GRANTED) {
                    /**
                     * Request the permission without showing explanation of why this permissions is needed
                     * TODO : Later follow following Google guide on requesting permissions
                     * https://developer.android.com/training/permissions/requesting.html
                     * and show an explanation for permissions to user asynchronously and get users response
                     * and if user denies that then deny the user login into taphelp
                     */
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.GET_ACCOUNTS},
                            MY_PERMISSIONS_REQUEST_GET_ACCOUNT);
                    // MY_PERMISSIONS_REQUEST_GET_ACCOUNT is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                } else {
                    /**
                     *  Otherwise no need to ask the user explicitly for permissions to get account details
                     *  The manifest.xml already has permission added : GET_ACCOUNTS
                     *  So fetch the account data for Google+ account
                     */
                    String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
                    emailLabel.setText(email);
                }

                // Fetch username details
                String personName = currentPerson.getDisplayName();
                username.setText(personName);
                String personPhotoUrl = currentPerson.getImage().getUrl();

                //new LoadProfileImage(image).execute(personPhotoUrl);
                // update profile frame with new info about Google Account
                // profile
                updateProfile(true);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error occured while reading Google+ account details : ", e);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_GET_ACCOUNT : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted.
                    String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
                    emailLabel.setText(email);

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {
        mGoogleApiClient.connect();
        updateProfile(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin:
                googlePlusLogin();
                break;
        }
    }

    public void signIn(View v) {
        googlePlusLogin();
    }

    public void logout(View v) {
        googlePlusLogout();
    }

    private void googlePlusLogin() {
        if (!mGoogleApiClient.isConnecting()) {
            signedInUser = true;
            resolveSignInError();

        }
    }

    private void googlePlusLogout() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
            updateProfile(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
