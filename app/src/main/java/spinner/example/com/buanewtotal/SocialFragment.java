package spinner.example.com.buanewtotal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Ratan on 7/29/2015.
 */
public class SocialFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.social_layout, container, false);
        Button button = (Button) view.findViewById(R.id.mm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.mm:
                        Intent intent1 = new Intent(getActivity(), loginbua.class);
                        startActivity(intent1);//Edited here
                        break;
                }
            }
        });


        return view;

    }




}
