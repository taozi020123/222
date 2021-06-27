package com.sict.sqlite.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.sict.sqlite.R;


public class Fragment3 extends Fragment implements View.OnClickListener {
    private EditText type;
    private Button credites;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment3, container, false);

        type=(EditText) view.findViewById(R.id.type);
        credites=(Button)view.findViewById(R.id.credites);
        credites.setOnClickListener(this);
        return view;
    }




    @Override
    public void onClick(View v) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.addCategory("android.intent.category.DEFAULT");
            Uri content_url = Uri.parse("https://www.miamioh.edu//");
            intent.setData(content_url);
            startActivity(intent);
        }catch (Exception e){

        }

    }
}
