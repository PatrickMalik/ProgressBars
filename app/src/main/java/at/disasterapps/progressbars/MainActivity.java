package at.disasterapps.progressbars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //TODO
//        this.deleteDatabase("ProgressBarSaver");

        DatabaseHandler databaseHandler = new DatabaseHandler(this);



        List<ProgressBarEntry> listEntries = databaseHandler.getAllProgressBarEntries();


        final ListEntryAdapter adapter = new ListEntryAdapter(this, listEntries);

        ListView listView = (ListView) findViewById(R.id.content_main_listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ViewProgressBarManualActivity.class);
                intent.putExtra("item_id", ((ProgressBarEntry) parent.getItemAtPosition(position)).getId()); //TODO if it doesnt work i know where to look
                startActivity(intent);
            }
        });

//        DatabaseHandler dbhandler = new DatabaseHandler(this);
//
//        int ids = dbhandler.addProgressBarEntry(new ProgressBarEntry("testTitle", "testDescription", 123, 321));
//        Log.d("Reading: ", "Reading stuff");
//
//        ProgressBarEntry testy = dbhandler.getProgressBarEntry(0);
//        Log.d("Read already", ""+testy.getId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNewProgressBar(View view){
        Intent intent = new Intent(this, NewProgressBarActivity.class);
        startActivity(intent);
    }
}
