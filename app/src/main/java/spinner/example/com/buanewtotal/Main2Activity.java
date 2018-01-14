package spinner.example.com.buanewtotal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class
Main2Activity extends AppCompatActivity {
    ListView list;
    /*String[] web = {
            "Farmgate",
            "Uttara",
            "Mirpur DOHS",
            "Puran Dhaka"
    } ;*/
    String[] web;
    Integer[] imageId = {
            R.drawable.i1,
            R.drawable.i1,
            R.drawable.i1,
            R.drawable.i1

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Intent intent = new Intent(this, CountrycodeActivity.class);
        web = getResources().getStringArray(R.array.colors);
        setTitle("Service Areas");

        CustomList adapter = new
                CustomList(Main2Activity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                switch (position) {
                    //    Toast.makeText(Main2Activity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
                    case 0:

                        //  Intent newActivity = new Intent(this, Mercury.class);
                       // startActivity(new Intent(getApplicationContext(), fulltimer.class));
                        startActivityForResult(intent, 1);
                        // startActivity(newActivity);
                        break;
                    case 1:
                         startActivityForResult(intent, 1);
                        break;
                 //   case 2:
                     //    startActivityForResult(intent, 1);
                      //  break;
                   // case 3:
                       // startActivityForResult(intent, 1);
                       /// break;

                }
            }
        });
    }

}
