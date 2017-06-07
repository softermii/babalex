package com.shakenbeer.babalex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    BabalexView babalexView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        babalexView = (BabalexView) findViewById(R.id.babalexView);
        babalexView.setItems(Data.circles());
    }
}
