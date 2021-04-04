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
            stepDescriptionCount = (int) row.getCell(2).getNumericCellValue();

            for (int i = 3; i<stepDescriptionCount+3; i++){
                if(row.getCell(i) == null){
                    stepDetail.add("");
                }else{
                    stepDetail.add(row.getCell(i).getStringCellValue());
                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        }


//        switch (pattern){
//            case SAJUJIRUGI:{
//                sajujirugi_step(step);
//                break;
//            } case SAJUMAKGI:{
//                sajumakgi_step(step);
//            }
//        }

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

    private void sajujirugi_step(int step){
        switch(step){
            case 0:{
                detailText = "Parallel ready stance toward D";
                imgCount = 1;
                stepDetail.add("");
                break;
            } case 1:{
                detailText = "Move the right foot to D, forming a right walking stance toward D while executing a middle punch to D with the right fist";
                imgCount = 6;
                stepDetail.add("Right walking stance middle punch with right fist toward D.");
                stepDetail.add("Previous Posture");
                stepDetail.add("");
                stepDetail.add("");
                stepDetail.add("Side View");
                stepDetail.add("Application - Opponent of the same height");
                break;
            } case 2:{
                detailText = "Move the right foot to A, forming a left walking stance toward while executing a low block to B with the left forearm.";
                imgCount = 5;
                stepDetail.add("left walking stance forearm low block toward B.");
                stepDetail.add("Previous Posture");
                stepDetail.add("Keep the forearms crossed in front of the right chest, placing the blocking one on the other.");
                stepDetail.add("The body becomes half facing the opponent.");
                stepDetail.add("Application - \n" + "1. Block is executed at the tibia\n" + "2. The ball of the foot is used as a pivot.\n" + "3. The outer forearm is the blocking tool.");
                break;
            } case 3:{
                detailText = "Move the right foot to B, forming a right walking stance toward B while executing a middle punch to B with the right fist.";
                imgCount = 5;
                stepDetail.add("Right walking stance middle punch toward B.");
                stepDetail.add("Previous Posture");
                stepDetail.add("");
                stepDetail.add("");
                stepDetail.add("Application - Shorter opponent");
                break;
            } case 4:{
                detailText = "Move the right foot to D, forming a left walking stance toward C while executing a low block to C with the left forearm.";
                imgCount = 6;
                stepDetail.add("Left walking stance forearm low block toward C.");
                stepDetail.add("Previous Posture");
                stepDetail.add("");
                stepDetail.add("");
                stepDetail.add("Application - Top View");
                stepDetail.add("Other View");
                break;
            } case 5:{
                detailText = "Move the right foot to C, forming a right walking stance toward C while executing a middle punch to C with the right fist.";
                imgCount = 6;
                stepDetail.add("Right walking stance middle punch toward C.");
                stepDetail.add("Previous Posture");
                stepDetail.add("");
                stepDetail.add("Side View");
                stepDetail.add("");
                stepDetail.add("Side View");
                break;
            } case 6:{
                detailText = "Move the right foot to B, forming a left walking stance toward A while executing a low block to A with the left forearm.";
                imgCount = 6;
                stepDetail.add("Left walking stance forearm low block toward A.");
                stepDetail.add("Previous Posture");
                stepDetail.add("");
                stepDetail.add("Top VIew");
                stepDetail.add("");
                break;
            } case 7:{
                detailText = "Move the right foot to A, forming a right walking stance toward A while executing a middle punch to A with the right fist.";
                imgCount = 5;
                stepDetail.add("Right walking stance middle punch toward A.");
                stepDetail.add("Previous Posture");
                stepDetail.add("");
                stepDetail.add("");
                stepDetail.add("Application - Shorter opponent");
                break;
            } case 8:{
                detailText = "END. Bring the right foot back to a ready posture.";
                imgCount = 5;
                stepDetail.add("Parallel ready stance toward D.");
                stepDetail.add("Previous Posture");
                stepDetail.add("");
                stepDetail.add("Top View");
                stepDetail.add("Side View");
                break;
            } case 9:{
                detailText = "Move the left foot to D, forming a left walking stance toward D while executing a middle punch to D with the left fist";
                imgCount = 6;
                stepDetail.add("left walking stance middle punch with left fist toward D.");
                stepDetail.add("Previous Posture");
                stepDetail.add("");
                stepDetail.add("");
                stepDetail.add("Side View");
                stepDetail.add("Application - Side View");
            } case 10:{
                detailText = "Move the left foot to B, forming a right walking stance toward while executing a low block to A with the right forearm.";
                imgCount = 4;
                stepDetail.add("right walking stance forearm low block toward A.");
                stepDetail.add("Previous Posture");
                stepDetail.add("Keep the forearms crossed in front of the right chest, placing the blocking one on the other.");
                stepDetail.add("The body becomes half facing the opponent.");
                break;
            } case 11:{
                detailText = "Move the left foot to A, forming a left walking stance toward A while executing a middle punch to A with the left fist";
                imgCount = 5;
                stepDetail.add("left walking stance middle punch with left fist toward A.");
                stepDetail.add("Previous Posture");
                stepDetail.add("");
                stepDetail.add("");
                stepDetail.add("Application");
                break;
            } case 12:{
                detailText = "Move the left foot to D, forming a right walking stance toward while executing a low block to A with the right forearm.";
                imgCount = 6;
                stepDetail.add("right walking stance forearm low block toward C.");
                stepDetail.add("Previous Posture");
                stepDetail.add("");
                stepDetail.add("Side View");
                stepDetail.add("");
                stepDetail.add("Side View");
                break;
            } case 13:{
                detailText = "Move the left foot to C, forming a left walking stance toward C while executing a middle punch to C with the left fist";
                imgCount = 5;
                stepDetail.add("left walking stance middle punch with left fist toward C.");
                stepDetail.add("Previous Posture");
                stepDetail.add("");
                stepDetail.add("");
                stepDetail.add("Side View");
                break;
            } case 14:{
                detailText = "Move the left foot to A, forming a right walking stance toward while executing a low block to B with the right forearm.";
                imgCount = 5;
                stepDetail.add("right walking stance forearm low block toward B.");
                stepDetail.add("Previous Posture");
                stepDetail.add("");
                stepDetail.add("Other View");
                stepDetail.add("");
                break;
            } case 15:{
                detailText = "Move the left foot to B, forming a left walking stance toward B while executing a middle punch to B with the left fist";
                imgCount = 5;
                stepDetail.add("left walking stance middle punch with left fist toward B.");
                stepDetail.add("Previous Posture");
                stepDetail.add("");
                stepDetail.add("");
                stepDetail.add("Application");
                break;
            } case 16:{
                detailText = "END. Bring the left foot back to a ready posture.";
                imgCount = 4;
                stepDetail.add("Parallel ready stance toward D.");
                stepDetail.add("Previous Posture");
                stepDetail.add("");
                stepDetail.add("Side View");
                break;
            }
        }

    }

    private void sajumakgi_step(int step){
//        switch(step){
//            case 0:{
//                detailText = "Parallel ready stance toward D.";
//                imgCount = 1;
//                stepDetail.add("");
//                break;
//            } case 1:{
//                detailText = "Move the right foot to C, forming a left walking stance " +
//                        "toward D while executing a low block to D with the left knife-hand.";
//                imgCount = 5;
//                stepDetail.add("Left walking stance knife-hand low block toward D.");
//                stepDetail.add("Previous Posture");
//                stepDetail.add("Keep the back forearms crossed in front of the rib cage, placing the blocking one on the other.");
//                stepDetail.add("Keep the body half facing the opponent.");
//                stepDetail.add("Application - Block is executed at the inner tibia.");
//                break;
//            } case 2:{
//                detailText = "Move the right foot to D, forming a right walking stance " +
//                        "toward D while executing a middle side block to D with the right inner forearm.";
//                imgCount = 5;
//                stepDetail.add("Right walking stance inner forearm middle side block toward D.");
//                stepDetail.add("Previous Posture");
//                stepDetail.add("");
//                stepDetail.add("");
//                stepDetail.add("Application - Side View");
//                break;
//            } case 3:{
//                detailText = "Move the right foot to A, forming a left walking stance " +
//                        "toward B while executing a low block to B with the left knife-hand.";
//                imgCount = 6;
//                stepDetail.add("Left walking stance knife-hand low block toward B.");
//                stepDetail.add("Previous Posture");
//                stepDetail.add("Keep the back forearms crossed in front of the rib cage, placing the blocking one on the other.");
//                stepDetail.add("Keep the body half facing the opponent.");
//                stepDetail.add("Application - Block is executed at the inner tibia.");
//                break;
//            }
//        }


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