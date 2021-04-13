package mdar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.example.letter_prediction.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

import mdar.model.SubstringProbabilityMap;

public class SelectionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

Spinner spinner;
HashMap probabilitySet;
ProgressBar progressBar;
Button start;
EditText userName;
EditText groupName;
String keyboardType = "Vanilla";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_main);
        init();
    }

    private void init() {
    spinner = (Spinner) findViewById(R.id.keyboard_spinner);
    start = (Button) findViewById(R.id.button_start);
    userName = (EditText) findViewById(R.id.userNameTextView);
    groupName = (EditText) findViewById(R.id.groupNameTextView);
    spinner.setOnItemSelectedListener(this);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.keyboard_array, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    progressBar = findViewById(R.id.progressBar);

    //Checks if the file is already loaded, if we are launching an intent to come back here
    if(!SubstringProbabilityMap.isLoaded) {
        runStartupThreads();
    } else {
        progressBar.setVisibility(View.GONE);
    }
}

    public void startTrials(View view) {
        Intent intent = new Intent(getBaseContext(), KeyboardActivity.class);
        intent.putExtra("KEYBOARD_TYPE", keyboardType);
        intent.putExtra("USER_NAME", userName.getText().toString());
        intent.putExtra("GROUP_NAME", groupName.getText().toString());
        startActivity(intent);
    }

    private void runStartupThreads() {
        start.setEnabled(false);
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
                    SubstringProbabilityMap.setMap(probabilitySet);
                    SubstringProbabilityMap.isLoaded = true;
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            start.setEnabled(true);
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

    /*Spinner implementation-----------------------------------------------------------*/
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        keyboardType = parent.getItemAtPosition(pos).toString();
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    /*---------------------------------------------------------------------------------*/
}
