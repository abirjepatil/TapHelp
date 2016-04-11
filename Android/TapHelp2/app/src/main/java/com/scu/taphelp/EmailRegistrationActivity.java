package com.scu.taphelp;

/**
 * Created by ${Aasawari} on 4/8/16.
 */
public class EmailRegistrationActivity extends NativeRegistrationActivity {
    public String getURL(){
        return new StringBuffer().append("http://taphelp- taphelp.rhcloud.com/Register?USER_NAME=")
                .append(userName).append("&EMAIL_ID=").append(emailId).append("&PASSWORD=").append(password)
                .append("&USER_TYPE=").append(userType.getId()).append("&FIRST_NAME=")
                .append(firstName).append("&LAST_NAME=").append(lastName).append("&ADDRESS=")
                .append(address).append("&PINCODE=").append(zipCode)
                .append("&PHONENO=").append(phoneNumber).append("&SERVICES=")
                .append(service_type.toString()).append("&AUTHTYPE=").append(user_type).toString();
    }
}
