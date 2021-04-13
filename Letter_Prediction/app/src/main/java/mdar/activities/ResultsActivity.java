package mdar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letter_prediction.R;

public class ResultsActivity extends AppCompatActivity {

private String [] times;
private String [] accuracies;
private String userName;
private String group;
private TextView nameHolder;
private TextView groupHolder;
private TextView timesHolder;
private TextView accuraciesHolder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        init();
        initializeViews();
        populateViews();
    }

    private void init() {
        times = getIntent().getStringArrayExtra("TIMES");
        accuracies = getIntent().getStringArrayExtra("ACCURACIES");
        userName = getIntent().getStringExtra("NAME");
        group = getIntent().getStringExtra("GROUP");
    }

    private void initializeViews() {
        nameHolder = findViewById(R.id.nameDisplay);
        groupHolder = findViewById(R.id.groupDisplay);
        timesHolder = findViewById(R.id.timesDisplay);
        accuraciesHolder = findViewById(R.id.accuraciesDisplay);
    }

    private void populateViews() {
        nameHolder.setText(userName);
        groupHolder.setText(group);
        timesHolder.setText(arrayToString(times));
        accuraciesHolder.setText(arrayToString(accuracies));
    }
    public void onClick(View view) {
        Intent intent = new Intent(getBaseContext(), SelectionActivity.class);
        startActivity(intent);
    }

    private String arrayToString(String [] arr) {
        String output = "{";
        for(int i = 0; i < 5; i ++) {
            output += arr[i];
            if(i != 4) {
                output += ", ";
            } else {
                output += "}";
            }
        }
        return output;
    }
}
