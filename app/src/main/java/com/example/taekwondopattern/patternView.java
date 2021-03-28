package com.example.taekwondopattern;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.taekwondopattern.Storage.Constant;
import com.example.taekwondopattern.viewAdaptor.patternGridAdapter;

@RequiresApi(api = Build.VERSION_CODES.N)
public class patternView extends AppCompatActivity implements Constant {

    int[] patternImg_sajujirugi = new int[SAJUJIRUGI_SIZE];
    int[] patternImg_sajumakgi = new int[SAJUMAKGI_SIZE];
    int[] patternImg_chonji = new int[CHONJI_SIZE];
    int[] patternImg_dangun = new int[DANGUN_SIZE];
    int[] patternImg_dosan = new int[DOSAN_SIZE];
    int[] patternImg_wonhyo = new int[WONHYO_SIZE];
    int[] patternImg_yulgok = new int[YULGOK_SIZE];
    int[] patternImg_joonggun = new int[JOONGGUN_SIZE];
    int[] patternImg_toigye = new int[TOIGYE_SIZE];
    int[] patternImg_hwarang = new int[HWARANG_SIZE];
    int[] patternImg_choongmoo = new int[CHOONGMOO_SIZE];

    GridView mGridView;
    ImageView mPatternDiagram;
    TextView mInterpretation;
    TextView mMovement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_view);

        Intent intent = getIntent();
        int pattern = intent.getIntExtra("pattern", 0);

        int[] patternImg = new int[]{};
        int patternDiagram = 0;
        String interpretation = "";
        String movement = "Movements - ";
        String patternID = "";
        int imgCount = 0;

        switch(pattern){
            case SAJUJIRUGI:{
                patternImg = patternImg_sajujirugi;
                patternDiagram = R.drawable.sajujirugidiagram;
                patternID = patternIDList[SAJUJIRUGI];
                imgCount = SAJUJIRUGI_SIZE;
                movement = movement + "14";
                break;
            } case SAJUMAKGI:{
                patternImg = patternImg_sajumakgi;
                patternDiagram = R.drawable.sajumakgiimage;
                patternID = patternIDList[SAJUMAKGI];
                imgCount = SAJUMAKGI_SIZE;
                movement = movement + "16";
                break;
            } case CHONGI:{
                patternImg = patternImg_chonji;
                patternDiagram = R.drawable.chonjidiagram;
                patternID = patternIDList[CHONGI];
                imgCount = CHONJI_SIZE;
                movement = movement + "19";
                interpretation = "Interpretation: \"the Heaven the Earth\". It is, in the Orient, " +
                        "interpreted as the creation of the world or the beginning of human history, " +
                        "therefore, it is the initial pattern played by the beginner. This pattern consists of two similar parts;" +
                        " one to represent the Heaven and the other the Earth.";
                break;
            } case DANGUN:{
                patternImg = patternImg_dangun;
                patternDiagram = R.drawable.dangundiagram;
                patternID = patternIDList[DANGUN];
                imgCount = DANGUN_SIZE;
                movement = movement + "21";
                interpretation = "Interpretation: Dan-Gun is named after the holy Dan-Gun, the legendary founder of Korea in the year of 2,333 B.C.";
                break;
            } case DOSAN: {
                patternImg = patternImg_dosan;
                patternDiagram = R.drawable.dosandiagram;
                patternID = patternIDList[DOSAN];
                imgCount = DOSAN_SIZE;
                movement = movement + "24";
                interpretation = "Interpretation: DO-SAN is the pseudonym of the patriot Ahn Chang-Ho (1876-1938) " +
                        "The 24 movements represent his entire life which he devoted to furthering the education of Korea and its independence movement.";
                break;
            } case WONHYO:{
                patternImg = patternImg_wonhyo;
                patternDiagram = R.drawable.wonhyodiagram;
                patternID = patternIDList[WONHYO];
                imgCount = WONHYO_SIZE;
                movement = movement + "28";
                interpretation = "WON-HYO was the noted monk who introduced Buddhism to the Silla Dynasty in the year 686 A.D.";
                break;
            }case YULGOK: {
                patternImg = patternImg_yulgok;
                patternDiagram = R.drawable.yulgokdiagram;
                patternID = patternIDList[YULGOK];
                imgCount = YULGOK_SIZE;
                movement = movement + "38";
                interpretation = "Yul-Gok is the pseudonym of a great philosopher and scholar Yil (1536-1584) nicknamed the \"Confucius of Korea\"" +
                        " The 38 movements of this pattern refer to his birthplace on 38 latitude and the diagram represents \"scholar\".";
                break;
            }case JOONGGUN: {
                patternImg = patternImg_joonggun;
                patternDiagram = R.drawable.joonggundiagram;
                patternID = patternIDList[JOONGGUN];
                imgCount = JOONGGUN_SIZE;
                movement = movement + "32";
                interpretation = "Joong-Gun is named after the patriot Ahn Joong-Gun who assassinated Hiro-Bumi Ito, the first Japanese governor-general of Korea," +
                        " known as the man who played the leading part in the Korea-Japan merger." +
                        " There are 32 movements in this pattern to represent Mr. Ahn's age when he was executed in a Lui-Shung prison (1910).";
                break;
            }case TOIGYE: {
                patternImg = patternImg_toigye;
                patternDiagram = R.drawable.toigyediagram;
                patternID = patternIDList[TOIGYE];
                imgCount = TOIGYE_SIZE;
                movement = movement + "37";
                interpretation = "Toi-Gye is the pen name of the noted scholar Yi Hwang (16th century), an authority on neo Confucianism." +
                        " The 37 movements of the pattern refer to his birthplace on 37 latitude, the diagram represents \" scholar\".";
                break;
            }case HWARANG: {
                patternImg = patternImg_hwarang;
                patternDiagram = R.drawable.hwarangdiagram;
                patternID = patternIDList[HWARANG];
                imgCount = HWARANG_SIZE;
                movement = movement + "29";
                interpretation = "HWA-RANG is named after the Hwa-Rang youth group, which originated in the Silla Dynasty in the early 7th century." +
                        " The 29 movements refer to the 29th Infantry Division, where Taekwon-Do developed into maturity.";
                break;
            }case CHOONGMOO: {
                patternImg = patternImg_choongmoo;
                patternDiagram = R.drawable.choongmoodiagram;
                patternID = patternIDList[CHOONGMOO];
                imgCount = CHOONGMOO_SIZE;
                movement = movement + "30";
                interpretation = "CHOONG-MOO was the name given to the great Admiral Yi Soon-Sin of the Lee Dynasty. " +
                        "He was reputed to have invented the first armoured battleship (Kobukson) in 1592, which is said to be the precursor of the present day submarine. " +
                        "The reason why this pattern ends with a left hand attack is to symbolize his regrettable death, " +
                        "having no chance to show his unrestrained potentiality checked by the forced reservation of his loyalty to the king.";
                break;
            }
        }

        for(int i=0; i<imgCount; i++){
            Integer dummy = i+1;
            int patternImgId = this.getResources().getIdentifier(patternID+dummy.toString(),"drawable", this.getPackageName());
            patternImg[i] = patternImgId;
        }

        // Attaching the layout to the toolbar object
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        toolbar.setTitle(patternName[pattern]);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mPatternDiagram = findViewById(R.id.patternDiagram);
        mPatternDiagram.setImageResource(patternDiagram);
        mInterpretation = findViewById(R.id.patternMeaning);
        mInterpretation.setText(interpretation);
        mMovement = findViewById(R.id.patternMovementCount);
        mMovement.setText(movement);

        mGridView = findViewById(R.id.gridView);
        patternGridAdapter adapter = new patternGridAdapter(patternView.this, patternImg);
        mGridView.setAdapter(adapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(patternView.this, patternDetailView.class);
                intent.putExtra("pattern", pattern);
                intent.putExtra("step", i);
                startActivity(intent);
            }
        });
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
