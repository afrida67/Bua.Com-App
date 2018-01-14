package spinner.example.com.buanewtotal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class newPartDetail extends AppCompatActivity {

    TextView etId, etName, etc, etage,etpn,etref,etad,etw,etcl;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_part_detail);
        setTitle("Profile");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //  etId = (TextView)findViewById(R.id.etId);
        etName = (TextView)findViewById(R.id.etName);
        etage = (TextView)findViewById(R.id.etage);
        etc = (TextView)findViewById(R.id.etcok);
        etpn=(TextView)findViewById(R.id.etpn);
        etw=(TextView)findViewById(R.id.etwa);
        etref=(TextView)findViewById(R.id.etreff);
        etad=(TextView)findViewById(R.id.etaddr);
        etcl=(TextView)findViewById(R.id.etcl);
        ivImage = (ImageView)findViewById(R.id.ivImage);



        if(getIntent().getSerializableExtra("product") != null){
            final   Product product = (Product) getIntent().getSerializableExtra("product");
            // etId.setText("ID:  " + product.id);
            etName.setText("Name:  " + product.name);
            etage.setText("Age:  " + product.age);
            etc.setText("Cooking Rate: " + product.cook+"Tk");
            etw.setText("Laundry Rate: " + product.wash+"Tk");
            etpn.setText("Phone Number : " + product.contact);
            etad.setText("Address : " + product.area);
            etref.setText("Reference : " + product.ref);
            etcl.setText("Cleaning Rate : " + product.clean+"Tk");


            etpn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + product.contact));
                    startActivity(callIntent);
                }
            });

            // etDesc.setText("" + product.pro_desc);
            // etQty.setText("" + product.qty);

            // if(getIntent().getExtras().getString("ipaddress") != null) {
            // String ipAddress = getIntent().getExtras().getString("ipaddress");
            // String path = "http://sakura999.site88.net/upi/full/" + product.image_url + "";
            String path = "https://buacom.000webhostapp.com/aesha/" + product.image_url + "";
            Picasso.with(getApplicationContext()).load(path).into(ivImage);
            //  }



        }
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
                        Intent intent = new Intent(newPartDetail.this, MainActivity.class);
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
