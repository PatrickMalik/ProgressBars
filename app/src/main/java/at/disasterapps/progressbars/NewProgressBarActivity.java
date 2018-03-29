package at.disasterapps.progressbars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_progress_bar);
    }

    public void addProgressBarEntry(View view){
        String title = ((EditText)findViewById(R.id.activity_new_progress_bar_title_input)).getText().toString();
        String description = ((EditText)findViewById(R.id.activity_new_progress_bar_description_input)).getText().toString();

        //Ugly AF
        int from = Integer.parseInt(((EditText)findViewById(R.id.activity_new_progress_bar_from_input)).getText().toString());
        int to = Integer.parseInt(((EditText)findViewById(R.id.activity_new_progress_bar_to_input)).getText().toString());

        ProgressBarEntry progressBarEntry = new ProgressBarEntry(title, description, from, to);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        databaseHandler.addProgressBarEntry(progressBarEntry);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void cancel(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
