    package at.disasterapps.progressbars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import at.disasterapps.progressbars.exceptions.CurrentIsBiggerThanEndException;
import at.disasterapps.progressbars.exceptions.CurrentIsSmallerThanZeroException;

    public class ViewProgressBarManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_progresss_bar_manual);

        int id = this.getIntent().getIntExtra("item_id", -1);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        final ProgressBarEntry progressBarEntry = databaseHandler.getProgressBarEntry(id);

        ((TextView) this.findViewById(R.id.activity_view_progress_bar_from_text)).setText(String.valueOf(progressBarEntry.getFrom()));
        ((TextView) this.findViewById(R.id.activity_view_progress_bar_to_text)).setText(String.valueOf(progressBarEntry.getTo()));
        final ProgressBar progressBar = (ProgressBar) this.findViewById(R.id.activity_view_progress_bar_progressbar);
        progressBar.setMax(progressBarEntry.getProgress().getEnd());
        try {
            progressBar.setProgress(progressBarEntry.getProgress().calculateProgress(progressBarEntry.getFrom()));
        } catch (CurrentIsBiggerThanEndException e) {
            e.printStackTrace();
        } catch (CurrentIsSmallerThanZeroException e) {
            e.printStackTrace();
        }

//        Button button = (Button) findViewById(R.id.activity_view_progress_bar_plus_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressBar.setProgress((progressBarEntry.getProgress().calculateProgress(progressBarEntry.get)));
//            }
//        });

    }
}
