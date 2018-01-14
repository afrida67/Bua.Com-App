package spinner.example.com.buanewtotal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.amigold.fundapter.interfaces.ItemClickListener;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kosalgeek.android.json.JsonConverter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class newPartBualist extends AppCompatActivity {

    final String TAG = this.getClass().getSimpleName();

    EditText etIpAddress;
TextView textView;
    ListView lvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_part_bualist);
        setTitle("Part Timer Bua List");

        lvProduct = (ListView)findViewById(R.id.lvProduct);

        textView = (TextView) findViewById(R.id.textView9);

        //Fetching email from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");

        //Showing the current logged in email to textview
        textView.setText("Login as: " + username);

        // final String ipAddress = etIpAddress.getText().toString();


       // String url = "http://cherry67.netne.net/aesha/partBuaList.php";

        String url = "https://buacom.000webhostapp.com/aesha/partBuaList.php";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String jsonText) {
                        //Log.d(TAG, jsonText);

                        final ArrayList<Product> productList = new JsonConverter<Product>().toArrayList(jsonText, Product.class);

                        BindDictionary<Product> dictionary = new BindDictionary<>();
                        dictionary.addStringField(R.id.tvName, new StringExtractor<Product>() {
                            @Override
                            public String getStringValue(Product product, int position) {
                                return product.name;
                            }
                        }).onClick(new ItemClickListener<Product>() {
                            @Override
                            public void onClick(Product item, int position, View view) {
                                Intent in = new Intent(newPartBualist.this, SubActivity.class);
                                startActivity(in);
                            }
                        });

                        dictionary.addStringField(R.id.tvPrice, new StringExtractor<Product>() {
                            @Override
                            public String getStringValue(Product product, int position) {
                                return "" + "Age: "+product.age;
                            }
                        });

                      /* dictionary.addStringField(R.id.tvPrice, new StringExtractor<Product>() {
                            @Override
                            public String getStringValue(Product product, int position) {
                                return "" + product.price;
                            }
                        });*/

                        dictionary.addDynamicImageField(R.id.ivImage,
                                new StringExtractor<Product>() {
                                    @Override
                                    public String getStringValue(Product product, int position) {
                                        // String path = "http://"+ ipAddress +"/productproject/img/" + product.image + "";
                                       // String path = "http://cherry67.netne.net/aesha/" + product.image_url + "";

                                        String path = "https://buacom.000webhostapp.com/aesha/" + product.image_url + "";
                                        Log.d(TAG, path);
                                        return path;
                                    }
                                },
                                new DynamicImageLoader() {
                                    @Override
                                    public void loadImage(String url, ImageView imageView) {
                                        Picasso.with(getApplicationContext())
                                                .load(url)
                                                .placeholder(R.drawable.ic_launcher)
                                                .error(R.drawable.ic_launcher)
                                                .into(imageView);
                                    }
                                }
                        );

                        FunDapter<Product> adapter = new FunDapter<>(
                                getApplicationContext(),
                                productList,
                                R.layout.product_layout,
                                dictionary
                        );
                        lvProduct.setAdapter(adapter);
                        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent in = new Intent(newPartBualist.this, newPartDetail.class);
                                Product selectedProduct = productList.get(position);
                                // in.putExtra("ipaddress", ipAddress);
                                in.putExtra("product", selectedProduct);
                                startActivity(in);
                            }
                        });

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error while reading data", Toast.LENGTH_LONG).show();
                    }
                }
        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.EMAIL_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(newPartBualist.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.nac, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Logout) {
            //calling logout method when the logout button is clicked
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

}
