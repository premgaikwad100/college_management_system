package com.example.compesastudents.ui.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.compesastudents.R;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {

    private ViewPager viewPager;
    private BranchAdapter adapter;
    private List<BranchModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about, container, false);
        list = new ArrayList<>();
        list.add(new BranchModel(R.drawable.ic_comp,"Computer Engineering","COMPUTER ENGINEERING Department was established in year 1990.It Started functioning under Government Polytechnic when the Institute was established."));

       adapter =new BranchAdapter(getContext(),list);

       viewPager = view.findViewById(R.id.viewPager);
       viewPager.setAdapter(adapter);
        ImageView imageView = view.findViewById(R.id.college_image);
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/gpkarad-79962.appspot.com/o/Notice%2Fgpk.jpeg?alt=media&token=0e0d13d4-f1d2-49d6-8ce3-9e3e8c5eb5e7").into(imageView);

        return view;
    }
}