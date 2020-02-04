package com.example.android.rotcare1;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DemoFragment extends Fragment {

    private TextView textView;
    Button pending_req,alloted_req;


    public DemoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo, container, false);

        pending_req = view.findViewById(R.id.v_pending_req);
        alloted_req = view.findViewById(R.id.v_alloted_req);

        pending_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(),Pending_req_Activity.class));
            }
        });

        alloted_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Alloted_req_Activity.class));
            }
        });
        return view;
    }

}
