package mdar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.letter_prediction.R;

import java.util.HashMap;

import static java.lang.Character.toLowerCase;

/*
author: Mason D'arcy
email: masondarcy@gmail.com
date: 2021/03/29
 */

public class KeyboardActivity extends AppCompatActivity {
TextView userInputs;
TextView phraseHolder;
Button a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, bk, space, test;
Button [] butts = new Button[27];
HashMap probabilitySet;
String neutralColor = "#FFd8e6db";
String keyboardType = "";
int phraseCounter = 0;
Model model;

@RequiresApi(api = Build.VERSION_CODES.N)
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
}

private void init() {
    initializeViews();
    probabilitySet = GlobalMap.probs;
    keyboardType = getIntent().getStringExtra("KEYBOARD_TYPE");
    model = new Model();
    phraseHolder.setText(model.phrases[0]);
}

/*
mutateKeys loops through all the keys and changed their colors.
@param: Array of floats representing the probabilities of each letter of the alphabet, prob[0] = 0.13, 13% chance of 'a'
 */
private void mutateKeys(float[] probs) {
        if (probs != null) {
                for (int i = 0; i < probs.length; i++) {
                    butts[i].setBackgroundColor(Color.parseColor((MiscUtility.probToColor(probs[i], keyboardType))));
                }
        } else {
            for (int i = 0; i < 27; i++) {
                butts[i].setBackgroundColor(Color.parseColor(neutralColor));
            }
        }
    }

/*
disables or enables buttons so the program doesn't crash while loading resources
@param: boolean that represents if keys should be enabled or disabled
 */
private void setButtons(boolean state) {
        for(int i = 0; i < butts.length; i ++) {
            butts[i].setEnabled(state);
        }
        space.setEnabled(state);
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
        butts[26] = bk;
        space = findViewById(R.id.space);
        userInputs = findViewById(R.id.userInputs);
       // progressBar = findViewById(R.id.progressBar);
        test = findViewById(R.id.test);
        phraseHolder = findViewById(R.id.phrase_prompt);
    }

public void onClick(View view) {
        switch(view.getId()) {
            case R.id.a:
                model.toggle();
                userInputs.setText(userInputs.getText() + "a");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.b:
                model.toggle();
                userInputs.setText(userInputs.getText() + "b");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.c:
                model.toggle();
                userInputs.setText(userInputs.getText() + "c");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.d:
                model.toggle();
                userInputs.setText(userInputs.getText() + "d");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.e:
                model.toggle();
                userInputs.setText(userInputs.getText() + "e");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.f:
                model.toggle();
                userInputs.setText(userInputs.getText() + "f");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.g:
                model.toggle();
                userInputs.setText(userInputs.getText() + "g");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.h:
                model.toggle();
                userInputs.setText(userInputs.getText() + "h");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.i:
                model.toggle();
                userInputs.setText(userInputs.getText() + "i");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.j:
                model.toggle();
                userInputs.setText(userInputs.getText() + "j");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.k:
                model.toggle();
                userInputs.setText(userInputs.getText() + "k");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.l:
                model.toggle();
                userInputs.setText(userInputs.getText() + "l");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.m:
                model.toggle();
                userInputs.setText(userInputs.getText() + "m");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.n:
                model.toggle();
                userInputs.setText(userInputs.getText() + "n");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.o:
                model.toggle();
                userInputs.setText(userInputs.getText() + "o");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.p:
                model.toggle();
                userInputs.setText(userInputs.getText() + "p");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.q:
                model.toggle();
                userInputs.setText(userInputs.getText() + "q");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.r:
                model.toggle();
                userInputs.setText(userInputs.getText() + "r");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.s:
                model.toggle();
                userInputs.setText(userInputs.getText() + "s");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.t:
                model.toggle();
                userInputs.setText(userInputs.getText() + "t");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.u:
                model.toggle();
                userInputs.setText(userInputs.getText() + "u");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.v:
                model.toggle();
                userInputs.setText(userInputs.getText() + "v");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.w:
                model.toggle();
                userInputs.setText(userInputs.getText() + "w");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.x:
                model.toggle();
                userInputs.setText(userInputs.getText() + "x");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.y:
                model.toggle();
                userInputs.setText(userInputs.getText() + "y");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.z:
                model.toggle();
                userInputs.setText(userInputs.getText() + "z");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                break;
            case R.id.bk:
                //some of this could be folded into the mutatekeys function
                if(!userInputs.getText().toString().isEmpty()) {
                    userInputs.setText(userInputs.getText().subSequence(0, userInputs.getText().length() - 1));

                    if(userInputs.getText().toString().isEmpty()) {
                        for (int i = 0; i < 27; i++) {
                            butts[i].setBackgroundColor(Color.parseColor(neutralColor));
                        }
                    }else {
                            mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(userInputs.getText().toString())));
                        }
                    }
                break;
            case R.id.space:
                model.toggle();
                userInputs.setText(userInputs.getText() + " ");
                for (int i = 0; i < 27; i++) {
                    butts[i].setBackgroundColor(Color.parseColor(neutralColor));
                }
                break;
            case R.id.test:
                Log.i("DEBUG", userInputs.getText().toString());
                float[] prob = (float[]) probabilitySet.get(userInputs.getText().toString());
                for(int i = 0; i < prob.length; i++) {
                    Log.i("DEBUG", "Letter = " + MiscUtility.mapNumberToLetter(i) + ", prob: " + prob[i]);
                }
                break;
            case R.id.enter:
                Log.i("DEBUG", userInputs.getText().toString());
                // Analyze speed and accuracy
                // Write data to file
                //TODO
                model.endTime();
                model.hasStartedTyping = false;
                Log.i("DEBUG", "Elapsed time: " + model.getElapsedTime());

                // If that was the last phrase, navigate to selection activity
                if(phraseCounter == 4) {
                    phraseCounter = 0;
                    Intent intent = new Intent(getBaseContext(), SelectionActivity.class);
                    startActivity(intent);
                } else {
                    // Push the next phrase
                    phraseCounter++;
                    phraseHolder.setText(model.phrases[phraseCounter]);
                    // Clear the user input area
                    userInputs.setText("");
                    //Reset the keyboard colors
                    //TODO
                }

                break;


            default:

        }
    }
}