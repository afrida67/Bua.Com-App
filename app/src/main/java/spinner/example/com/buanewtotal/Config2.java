package spinner.example.com.buanewtotal;

/**
 * Created by User on 5/1/2016.
 */
public class Config2 {
    //URL to our login.php file
  // public static final String LOGIN_URL = "http://cherry67.netne.net/aesha/loginPartBua.php";
 public static final String LOGIN_URL = "https://buacom.000webhostapp.com/aesha/loginPartBua.php";


    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "username";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "mylogin";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "username";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "logged";

}