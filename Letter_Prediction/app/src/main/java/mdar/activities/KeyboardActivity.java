package mdar.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.letter_prediction.R;

import net.ricecode.similarity.JaroWinklerStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import mdar.model.ColorUtility;
import mdar.model.SubstringProbabilityMap;
import mdar.model.TimeUtility;

import static java.lang.Character.toLowerCase;

/*
author: Mason D'Arcy
email: masondarcy@gmail.com
date: 2021/03/29
 */

public class KeyboardActivity extends AppCompatActivity {
TextView userInputs;
TextView phraseHolder;
final int NUM_TRIALS = 5;
Button a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, bk, space;
Button [] butts = new Button[26];
HashMap probabilitySet;
String neutralColor = "#FFd8e6db";
String keyboardType = "";
String userName = "";
String group = "";
int phraseCounter = 0;
TimeUtility timeUtility;
String[] phrases;
String[] times = new String[NUM_TRIALS];
String[] accuracies = new String[NUM_TRIALS];

@RequiresApi(api = Build.VERSION_CODES.N)
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
}

    private void init() {
    initializeViews();
  //  initializeDataDirectory();
    phrases = getResources().getStringArray(R.array.phrases);
    probabilitySet = SubstringProbabilityMap.probs;
    keyboardType = getIntent().getStringExtra("KEYBOARD_TYPE");
    Log.i("MYDEBUG", userName);
    userName = getIntent().getStringExtra("USER_NAME");
    group = getIntent().getStringExtra("GROUP_NAME");
    timeUtility = new TimeUtility();
    phraseHolder.setText(phrases[0]);
}
    private void initializeViews() {
        a = findViewById(R.id.a);
        butts[0] = a;
        b = findViewById(R.id.b);
        butts[1] = b;
        c = findViewById(R.id.c);
        butts[2] = c;
        d = findViewById(R.id.d);
        butts[3] = d;
        e = findViewById(R.id.e);
        butts[4] = e;
        f = findViewById(R.id.f);
        butts[5] = f;
        g = findViewById(R.id.g);
        butts[6] = g;
        h = findViewById(R.id.h);
        butts[7] = h;
        i = findViewById(R.id.i);
        butts[8] = i;
        j = findViewById(R.id.j);
        butts[9] = j;
        k = findViewById(R.id.k);
        butts[10] = k;
        l = findViewById(R.id.l);
        butts[11] = l;
        m = findViewById(R.id.m);
        butts[12] = m;
        n = findViewById(R.id.n);
        butts[13] = n;
        o = findViewById(R.id.o);
        butts[14] = o;
        p = findViewById(R.id.p);
        butts[15] = p;
        q = findViewById(R.id.q);
        butts[16] = q;
        r = findViewById(R.id.r);
        butts[17] = r;
        s = findViewById(R.id.s);
        butts[18] = s;
        t = findViewById(R.id.t);
        butts[19] = t;
        u = findViewById(R.id.u);
        butts[20] = u;
        v = findViewById(R.id.v);
        butts[21] = v;
        w = findViewById(R.id.w);
        butts[22] = w;
        x = findViewById(R.id.x);
        butts[23] = x;
        y = findViewById(R.id.y);
        butts[24] = y;
        z = findViewById(R.id.z);
        butts[25] = z;
        bk = findViewById(R.id.bk);
        space = findViewById(R.id.space);
        userInputs = findViewById(R.id.userInputs);
        //  test = findViewById(R.id.test);
        phraseHolder = findViewById(R.id.phrase_prompt);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.a:
                keyStroke("a");
                break;
            case R.id.b:
                keyStroke("b");
                break;
            case R.id.c:
                keyStroke("c");
                break;
            case R.id.d:
                keyStroke("d");
                break;
            case R.id.e:
                keyStroke("e");
                break;
            case R.id.f:
                keyStroke("f");
                break;
            case R.id.g:
                keyStroke("g");
                break;
            case R.id.h:
                keyStroke("h");
                break;
            case R.id.i:
                keyStroke("i");
                break;
            case R.id.j:
                keyStroke("j");
                break;
            case R.id.k:
                keyStroke("k");
                break;
            case R.id.l:
                keyStroke("l");

                break;
            case R.id.m:
                keyStroke("m");

                break;
            case R.id.n:
                keyStroke("n");
                break;
            case R.id.o:
                keyStroke("o");
                break;
            case R.id.p:
                keyStroke("p");
                break;
            case R.id.q:
                keyStroke("q");
                break;
            case R.id.r:
                keyStroke("r");
                break;
            case R.id.s:
                keyStroke("s");
                break;
            case R.id.t:
                keyStroke("t");
                break;
            case R.id.u:
                keyStroke("u");
                break;
            case R.id.v:
                keyStroke("v");
                break;
            case R.id.w:
                keyStroke("w");
                break;
            case R.id.x:
                keyStroke("x");
                break;
            case R.id.y:
                keyStroke("y");
                break;
            case R.id.z:
                keyStroke("z");
                break;
            case R.id.bk:
            backspace();
                break;
            case R.id.space:
                space();
                break;
            case R.id.enter:
               enter();
                break;
            default:
        }
    }
    /*onClick helper functions-------------------------------------------------------------------*/
    private void keyStroke(String c) {
        timeUtility.toggle();
        userInputs.setText(userInputs.getText() + c);
        updateKeyColors((float[]) probabilitySet.get(ColorUtility.findCurrentWord(userInputs.getText().toString())));
    }

    private void backspace() {
        if(!userInputs.getText().toString().isEmpty()) {
            userInputs.setText(userInputs.getText().subSequence(0, userInputs.getText().length() - 1));
            if(userInputs.getText().toString().isEmpty()) {
                for (int i = 0; i < butts.length; i++) {
                    butts[i].setBackgroundColor(Color.parseColor(neutralColor));
                }
            }else {
                updateKeyColors((float[]) probabilitySet.get(ColorUtility.findCurrentWord(userInputs.getText().toString())));
            }
        }
    }

    private void space() {
        timeUtility.toggle();
        userInputs.setText(userInputs.getText() + " ");
        for (int i = 0; i < butts.length; i++) {
            butts[i].setBackgroundColor(Color.parseColor(neutralColor));
        }

    }

    private void enter()  {
        Log.i("DEBUG", userInputs.getText().toString());
        // Analyze speed and accuracy
        timeUtility.endTime();
        timeUtility.hasStartedTyping = false;
        String time = String.valueOf(timeUtility.getElapsedTime());
        times[phraseCounter] = time;
        accuracies[phraseCounter] = String.valueOf(getAccuracy(phrases[phraseCounter], userInputs.getText().toString()));
        if(phraseCounter == 4) {
            phraseCounter = 0;
            Intent intent = new Intent(getBaseContext(), ResultsActivity.class);
            intent.putExtra("TIMES", times);
            intent.putExtra("ACCURACIES", accuracies);
            intent.putExtra("NAME", userName);
            intent.putExtra("GROUP", group);
            startActivity(intent);

        } else {
            // Push the next phrase
            phraseCounter++;
            phraseHolder.setText(phrases[phraseCounter]);
            // Clear the user input area
            userInputs.setText("");
            //Reset the keyboard colors
            updateKeyColors(null);
        }
    }

    private void updateKeyColors(float[] probs) {
        if (probs != null) {
            for (int i = 0; i < probs.length; i++) {
                butts[i].setBackgroundColor(Color.parseColor((ColorUtility.probToColor(probs[i], keyboardType))));
            }
        } else {
            for (int i = 0; i < butts.length; i++) {
                butts[i].setBackgroundColor(Color.parseColor(neutralColor));
            }
        }
    }
    /*-------------------------------------------------------------------------------------------*/

    private double getAccuracy(String target, String source) {
        SimilarityStrategy strat = new JaroWinklerStrategy();
        StringSimilarityService service = new StringSimilarityServiceImpl(strat);
        return service.score(source, target);
    }
}