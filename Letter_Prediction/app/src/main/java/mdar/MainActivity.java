package mdar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.letter_prediction.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.Key;
import java.util.HashMap;

import static java.lang.Character.toLowerCase;

/*
author: Mason D'arcy
email: masondarcy@gmail.com
date: 2021/03/29
 */

public class MainActivity extends AppCompatActivity {
TextView editField;
Button a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, bk, space, test;
Button [] butts = new Button[27];

HashMap probabilitySet;
ProgressBar progressBar;
String neutralColor = "#FFd8e6db";

@RequiresApi(api = Build.VERSION_CODES.N)
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        runStartupThreads();
    }

/*
mutateKeys loops through all the keys and changed their colors.
@param: Array of floats representing the probabilities of each letter of the alphabet, prob[0] = 0.13, 13% chance of 'a'
 */
private void mutateKeys(float[] probs) {
        if (probs != null) {
                for (int i = 0; i < probs.length; i++) {
                    butts[i].setBackgroundColor(Color.parseColor((MiscUtility.unstableProbToColor(probs[i]))));
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

/*
Creates a thread off the main UI thread to read data from a raw file to populate a hashmap.
Disables and re-enables the buttons.
 */
private void runStartupThreads() {
    setButtons(false);
    new Thread(new Runnable() {
        @Override
        public void run() {
            try
            {
                InputStream ins = getResources().openRawResource(
                        getResources().getIdentifier("hash",
                                "raw", getPackageName()));
                ObjectInputStream in = new ObjectInputStream(ins);

                // Method for deserialization of object
                probabilitySet = (HashMap<String, float[]>) in.readObject();

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        setButtons(true);
                    }
                });
                in.close();
                ins.close();

            }

            catch(IOException ex)
            {
                System.out.println("IOException is caught");
            }
            catch(ClassNotFoundException ex)
            {
                System.out.println("ClassNotFoundException is caught");
            }



        }
    }).start();
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
        editField = findViewById(R.id.testLabelOne);
        progressBar = findViewById(R.id.progressBar);
        test = findViewById(R.id.test);

    }

public void onClick(View view) {
        switch(view.getId()) {
            case R.id.a:
                editField.setText(editField.getText() + "a");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                Log.i("DEBUG", "Test: " + MiscUtility.findCurrentWord(editField.getText().toString()));
                break;
            case R.id.b:
                editField.setText(editField.getText() + "b");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.c:
                editField.setText(editField.getText() + "c");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.d:
                editField.setText(editField.getText() + "d");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.e:
                editField.setText(editField.getText() + "e");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.f:
                editField.setText(editField.getText() + "f");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.g:
                editField.setText(editField.getText() + "g");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.h:
                editField.setText(editField.getText() + "h");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.i:
                editField.setText(editField.getText() + "i");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.j:
                editField.setText(editField.getText() + "j");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.k:
                editField.setText(editField.getText() + "k");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.l:
                editField.setText(editField.getText() + "l");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.m:
                editField.setText(editField.getText() + "m");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.n:
                editField.setText(editField.getText() + "n");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.o:
                editField.setText(editField.getText() + "o");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.p:
                editField.setText(editField.getText() + "p");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.q:
                editField.setText(editField.getText() + "q");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.r:
                editField.setText(editField.getText() + "r");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.s:
                editField.setText(editField.getText() + "s");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.t:
                editField.setText(editField.getText() + "t");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.u:
                editField.setText(editField.getText() + "u");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.v:
                editField.setText(editField.getText() + "v");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.w:
                editField.setText(editField.getText() + "w");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.x:
                editField.setText(editField.getText() + "x");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.y:
                editField.setText(editField.getText() + "y");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.z:
                editField.setText(editField.getText() + "z");
                mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                break;
            case R.id.bk:
                //some of this could be folded into the mutatekeys function
                if(!editField.getText().toString().isEmpty()) {
                    editField.setText(editField.getText().subSequence(0, editField.getText().length() - 1));

                    if(editField.getText().toString().isEmpty()) {
                        for (int i = 0; i < 27; i++) {
                            butts[i].setBackgroundColor(Color.parseColor(neutralColor));
                        }
                    }else {
                            mutateKeys((float[]) probabilitySet.get(MiscUtility.findCurrentWord(editField.getText().toString())));
                        }
                    }
                break;
            case R.id.space:
                editField.setText(editField.getText() + " ");
                for (int i = 0; i < 27; i++) {
                    butts[i].setBackgroundColor(Color.parseColor(neutralColor));
                }
                break;
            case R.id.test:
                Log.i("DEBUG", editField.getText().toString());
                float[] prob = (float[]) probabilitySet.get(editField.getText().toString());
                for(int i = 0; i < prob.length; i++) {
                    Log.i("DEBUG", "Letter = " + MiscUtility.mapNumberToLetter(i) + ", prob: " + prob[i]);
                }
                break;
            default:

        }
    }
}