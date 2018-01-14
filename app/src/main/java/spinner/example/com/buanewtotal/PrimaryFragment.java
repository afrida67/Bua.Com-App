package spinner.example.com.buanewtotal;

/**
 * Created by User on 10/9/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class PrimaryFragment extends Fragment {
    /* public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         return inflater.inflate(R.layout.primary_layout,null);
      }*/
    //@Nullable
    // @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.primary_layout, container, false);
        Button button = (Button) view.findViewById(R.id.bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.bt:
                        Intent intent1 = new Intent(getActivity(), Main2Activity.class);
                        startActivity(intent1);//Edited here
                        break;
                }
            }
        });


        return view;

    }
}