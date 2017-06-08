package com.shakenbeer.babalex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    BabalexView babalexView;

    RecyclerView superBabalex;

    private final SnapHelper snapperCarr = new PagerSnapHelper();

    private SuperBabalexAdapter superBabalexAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        babalexView = (BabalexView) findViewById(R.id.babalexView);
//        babalexView.setItems(Data.dogs());
        superBabalex = (RecyclerView) findViewById(R.id.super_babalex);
        superBabalex.setLayoutManager(new LinearLayoutManager(this));
        snapperCarr.attachToRecyclerView(superBabalex);
        superBabalexAdapter = new SuperBabalexAdapter(Data.animals());
        superBabalex.setAdapter(superBabalexAdapter);

    }

}
