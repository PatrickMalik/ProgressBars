package at.disasterapps.progressbars;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import at.disasterapps.progressbars.exceptions.CurrentIsBiggerThanEndException;
import at.disasterapps.progressbars.exceptions.CurrentIsSmallerThanZeroException;

/**
 * Created by Austr on 05.03.2018.
 */

public class ListEntryAdapter extends ArrayAdapter<ProgressBarEntry> {


    public ListEntryAdapter(Context ctx, List objects) {
        super(ctx, R.layout.list_entry, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(getContext());
        View customView = inflator.inflate(R.layout.list_entry, parent, false);

        ProgressBarEntry progressBarEntry = getItem(position);
        TextView title = (TextView) customView.findViewById(R.id.list_entry_title_text);
        ProgressBar bar = (ProgressBar) customView.findViewById(R.id.list_entry_progressbar);

        title.setText(progressBarEntry.getTitle());
        try {
            Log.d("from: ", ""+progressBarEntry.getFrom());
            Log.d("to: ", ""+progressBarEntry.getTo());
            bar.setProgress(progressBarEntry.getProgress().calculateProgress(progressBarEntry.getFrom()+2));
        } catch (CurrentIsBiggerThanEndException e) {
            e.printStackTrace();
        } catch (CurrentIsSmallerThanZeroException e) {
            e.printStackTrace();
        }
        return customView;
    }
}
