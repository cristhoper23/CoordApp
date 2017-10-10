package com.cristhoper.coordapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cristhoper.coordapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoordFragment extends Fragment {

    TextView coordLat, coordLon;
    Button btnSendCoords;

    public CoordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista = inflater.inflate(R.layout.fragment_coord, container, false);

        coordLat = vista.findViewById(R.id.coordX);
        coordLon = vista.findViewById(R.id.coordY);
        btnSendCoords = vista.findViewById(R.id.btnSendCoords);

        Bundle args = getArguments();
        double lat = args.getDouble("lat");
        double lon = args.getDouble("lon");

        coordLat.setText(String.valueOf(lat));
        coordLon.setText(String.valueOf(lon));

        btnSendCoords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragMan = getFragmentManager();
                Fragment startFrag = new StartFragment();

                fragMan.beginTransaction().replace(R.id.contentFragment, startFrag).commit();
            }
        });

        return vista;
    }


}
