package com.example.taekwondopattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.example.taekwondopattern.Storage.Constant;
import com.example.taekwondopattern.viewAdaptor.detailGridAdapter;
import com.example.taekwondopattern.viewAdaptor.patternGridAdapter;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class patternDetailView extends AppCompatActivity implements Constant {

    static {
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLInputFactory",
                "com.fasterxml.aalto.stax.InputFactoryImpl"
        );
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLOutputFactory",
                "com.fasterxml.aalto.stax.OutputFactoryImpl"
        );
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLEventFactory",
                "com.fasterxml.aalto.stax.EventFactoryImpl"
        );
    }

    GridView mGridView;
    TextView mStepDescription;

    String detailText = new String();
    ArrayList<String> stepDetail = new ArrayList<>();
    ArrayList<Integer> stepImg = new ArrayList<>();
    ArrayList<Integer> bodyImg = new ArrayList<>();

    int imgCount = 0;
    int stepDescriptionCount;
    String stepID = "";
    String bodyID = "";

    XSSFWorkbook workbook;
    XSSFSheet sheet;
    Row row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_detail_view);

        Intent intent = getIntent();
        int pattern = intent.getIntExtra("pattern", 0);
        int step = intent.getIntExtra("step", 0);

        // Attaching the layout to the toolbar object
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        toolbar.setTitle(patternName[pattern]);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int patternXlsxID = this.getResources().getIdentifier(patternIDList[pattern], "raw", this.getPackageName());
        InputStream stream = getResources().openRawResource(patternXlsxID);
        try {
            workbook = new XSSFWorkbook(stream);
            sheet = workbook.getSheetAt(0);
            row = sheet.getRow(step);
            detailText = row.getCell(0).getStringCellValue();
            imgCount = (int) row.getCell(1).getNumericCellValue();
            for (int i = 2; i<imgCount+2; i++){
                if(row.getCell(i) == null){
                    stepDetail.add("");
                }else{
                    stepDetail.add(row.getCell(i).getStringCellValue());
                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

        stepID = patternIDList[pattern] + "step" + (step+1);
        bodyID = patternIDList[pattern] + "body" + (step+1);

        for(int i=0; i<imgCount; i++){
            int dummy = 97+i;
            int patternStepImgId = this.getResources().getIdentifier(stepID + (char)dummy, "drawable", this.getPackageName());
            stepImg.add(patternStepImgId);
            int patternBodyImgId = this.getResources().getIdentifier(bodyID + (char)dummy, "drawable", this.getPackageName());
            bodyImg.add(patternBodyImgId);
        }

        mStepDescription = findViewById(R.id.stepDescription);
        mStepDescription.setText(detailText);

        mGridView = findViewById(R.id.StepGridView);
        detailGridAdapter adapter = new detailGridAdapter(patternDetailView.this, stepDetail, stepImg, bodyImg);
        mGridView.setAdapter(adapter);

    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}