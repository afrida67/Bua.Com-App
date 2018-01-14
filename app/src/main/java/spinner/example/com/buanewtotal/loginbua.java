package spinner.example.com.buanewtotal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class loginbua extends AppCompatActivity implements View.OnClickListener {

    //Defining views
    ProgressDialog progressDialog;
    private EditText editTextEmail;
    private TextView tt;
    private Button full,part;
    private EditText editTextPassword;
    private AppCompatButton buttonLogin,partlogin;

    //boolean variable to check user is logged in or not
    //initially it is false
    private boolean loggedIn = false;
    private boolean loggedInP = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginbua);
        setTitle("Login as Bua");

        //Initializing views
        editTextEmail = (EditText) findViewById(R.id.uuu);
        editTextPassword = (EditText) findViewById(R.id.pass);
        tt=(TextView)findViewById(R.id.cbua);
        full = (Button) findViewById(R.id.full);
        part=(Button) findViewById(R.id.part);
        buttonLogin = (AppCompatButton) findViewById(R.id.abc);

        full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),buafullreg.class));
            }
        });
        part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),buaPARTreg.class));
            }
        });

        //Adding click listener
        buttonLogin.setOnClickListener(this);
      //  partlogin.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(Config2.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences2 = getSharedPreferences(Config6.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config2.LOGGEDIN_SHARED_PREF, false);
        loggedInP=sharedPreferences2.getBoolean(Config6.LOGGEDIN_SHARED_PREF,false);

        if (loggedIn) {
           //We will start the Profile Activity
            Intent intent = new Intent(loginbua.this, userlistsearch.class);
            startActivity(intent);
            finish();
        }
    }

    private void login() {
        //Getting values from edit texts
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config2.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if (response.equalsIgnoreCase(Config2.LOGIN_SUCCESS)) {
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = loginbua.this.getSharedPreferences(Config2.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(Config2.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config2.EMAIL_SHARED_PREF, email);

                            //Saving values to editor
                            editor.commit();

                            //Starting profile activity
                           Intent intent = new Intent(loginbua.this,userlistsearch.class);
                            startActivity(intent);
                            progressDialog.dismiss();
                            finish();
                        } else {

                          //  Toast.makeText(loginbua.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config2.KEY_EMAIL, email);
                params.put(Config2.KEY_PASSWORD, password);

                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

  private void login2() {
        //Getting values from edit texts
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config6.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if (response.equalsIgnoreCase(Config6.LOGIN_SUCCESS)) {
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = loginbua.this.getSharedPreferences(Config6.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(Config6.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config6.EMAIL_SHARED_PREF, email);

                            //Saving values to editor
                            editor.commit();

                            //Starting profile activity
                           Intent intent = new Intent(loginbua.this,userlistsearch.class);
                            startActivity(intent);
                            progressDialog.dismiss();
                            finish();
                        } else {

                        //   Toast.makeText(loginbua.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config6.KEY_EMAIL, email);
                params.put(Config6.KEY_PASSWORD, password);

                //returning parameter
                return params;
            }

        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {
        //Calling the login function
        progressDialog = new ProgressDialog(loginbua.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Loading");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        login();


        login2();

    }
}
