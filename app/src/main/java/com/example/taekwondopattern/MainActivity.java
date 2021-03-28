package com.example.taekwondopattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.taekwondopattern.Storage.Constant;
import com.example.taekwondopattern.viewAdaptor.gridAdapter;

import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity implements Constant {

    final int[] beltIcon = {};
    GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = findViewById(R.id.gridView);
        gridAdapter adapter = new gridAdapter(MainActivity.this, patternName, beltIcon);
        mGridView.setAdapter(adapter);

        try{
        // Attaching the layout to the toolbar object
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        toolbar.setTitle(R.string.home);
        setSupportActionBar(toolbar);
        }catch(Exception e){}

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent(MainActivity.this, patternView.class);
                    intent.putExtra("pattern", i);
                    startActivity(intent);
            }
        });

    }
}
