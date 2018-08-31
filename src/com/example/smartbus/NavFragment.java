package com.example.smartbus;


import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;





public class NavFragment extends Fragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
 
        
        int position = getArguments().getInt("position");
 
        
        String[] menu = getResources().getStringArray(R.array.Menu);
 
      
        View v = inflater.inflate(R.layout.fragment_layout, container, false);
 
 
        TextView tv = (TextView) v.findViewById(R.id.tv_content);
 
   
        tv.setText(menu[position]);

        getActivity().getActionBar().setTitle(menu[position]);
 
        return v;
    }
}
