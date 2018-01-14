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

public class newDetail extends AppCompatActivity {

    TextView etId, etName, etsal, etage,etpn,etref,etad;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);
        setTitle("Profile");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      //  etId = (TextView)findViewById(R.id.etId);
        etName = (TextView)findViewById(R.id.etName);
        etage = (TextView)findViewById(R.id.etage);
        etsal = (TextView)findViewById(R.id.etsal);
        etpn=(TextView)findViewById(R.id.etpn);
        etref=(TextView)findViewById(R.id.etreff);
        etad=(TextView)findViewById(R.id.etaddr);
        ivImage = (ImageView)findViewById(R.id.ivImage);



        if(getIntent().getSerializableExtra("product") != null){
         final   Product product = (Product) getIntent().getSerializableExtra("product");
           // etId.setText("ID:  " + product.id);
            etName.setText("Name:  " + product.name);
            etage.setText("Age:  " + product.age);
            etsal.setText("Salary: " + product.salary+"Tk");
            etpn.setText("Phone Number : " + product.contact);
            etad.setText("Address : " + product.area);
            etref.setText("Reference : " + product.ref);


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

         // String path = "http://cherry67.netne.net/aesha/" + product.image_url + "";
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
                        Intent intent = new Intent(newDetail.this, MainActivity.class);
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
        getMenuInflater().inflate(R.menu.nav11, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            //calling logout method when the logout button is clicked
            logout();
        }
        return super.onOptionsItemSelected(item);
    }


}
