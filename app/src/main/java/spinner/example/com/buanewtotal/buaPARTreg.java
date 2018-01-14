package spinner.example.com.buanewtotal;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class buaPARTreg extends AppCompatActivity implements View.OnClickListener {

    AlertDialog.Builder builder;

    private Button buttonChoose,full;
    private Button buttonUpload;

    private ImageView imageView;

    private EditText nn, un, pass,pn,age1,add,cook1,clean1,ref,wash1;

    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

   // private String UPLOAD_URL = "http://cherry67.netne.net/aesha/buapartreg.php";
    private String UPLOAD_URL = "https://buacom.000webhostapp.com/aesha/buapartreg.php";

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";
    private String KEY_UN = "username";
    private String KEY_Pass = "password";
    private String KEY_pn = "contact";
    private String KEY_age = "age";
    private String KEY_add = "area";
    private String KEY_cook = "cook";
    private String KEY_clean = "clean";
    private String KEY_wash = "wash";
    private String KEY_ref = "ref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bua_partreg);
        setTitle("Create New Account");

        buttonChoose = (Button) findViewById(R.id.partchoose);
        buttonUpload = (Button) findViewById(R.id.partupl);

        nn = (EditText) findViewById(R.id.pn);

        un = (EditText) findViewById(R.id.pun);
        pass = (EditText) findViewById(R.id.ppass);
        pn = (EditText) findViewById(R.id.pcon);
        age1= (EditText) findViewById(R.id.page);
        cook1= (EditText) findViewById(R.id.pcook);
        add= (EditText) findViewById(R.id.padd);
        wash1= (EditText) findViewById(R.id.ptla);
        clean1= (EditText) findViewById(R.id.pclean);
        ref= (EditText) findViewById(R.id.preff);

        imageView = (ImageView) findViewById(R.id.imageViewt);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage() {
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(buaPARTreg.this, s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(buaPARTreg.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
                String name = nn.getText().toString().trim();

                String username = un.getText().toString().trim();
                String password = pass.getText().toString().trim();
                String phone=pn.getText().toString().trim();
                String age=age1.getText().toString().trim();
                String cook= cook1.getText().toString().trim();
                String address = add.getText().toString().trim();
                String wash = wash1.getText().toString().trim();
                String clean= clean1.getText().toString().trim();
                String ref1 = ref.getText().toString().trim();

               if (nn.getText().toString().equals("") || un.getText().toString().equals("") ||
                        pass.getText().toString().equals("")||age1.getText().toString().equals("")||add.getText().toString().equals("")||
                        pn.getText().toString().equals("") || cook1.getText().toString().equals("")
                        ||wash1.getText().toString().equals("")||ref.getText().toString().equals("")
                        ||clean1.getText().toString().equals("") ) {

                    builder = new AlertDialog.Builder(buaPARTreg.this);
                    builder.setTitle("Something went wrong.....");
                    builder.setMessage("Please fill all the fields");
                    builder.setPositiveButton("Alright", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }

                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name);

                params.put(KEY_UN, username);
                params.put(KEY_Pass, password);
                params.put(KEY_pn, phone);
                params.put(KEY_age, age);
                params.put(KEY_cook, cook);
                params.put(KEY_add, address);
                params.put(KEY_clean, clean);
                params.put(KEY_ref, ref1);
                params.put(KEY_wash, wash);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (v == buttonChoose) {
            showFileChooser();


        }

        if (v == buttonUpload) {
            uploadImage();
        }
    }
}

