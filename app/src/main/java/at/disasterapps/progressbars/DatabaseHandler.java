package at.disasterapps.progressbars;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by Austr on 08.03.2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ProgressBarSaver";
    private static final String TABLE_PROGRESSBARENTRY = "progressBarEntry";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_FROM = "start";
    private static final String KEY_TO = "end";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROGRESSBARENTRY_TABLE = "CREATE TABLE " + TABLE_PROGRESSBARENTRY +
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TITLE + " TEXT," + KEY_DESCRIPTION +
                " TEXT,"  + KEY_FROM + " INTEGER," + KEY_TO +
                " INTEGER" + ")";

        db.execSQL(CREATE_PROGRESSBARENTRY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PROGRESSBARENTRY);

        onCreate(db);
    }

    public int addProgressBarEntry(ProgressBarEntry progressBarEntry){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, progressBarEntry.getTitle());
        values.put(KEY_DESCRIPTION, progressBarEntry.getDescription());
        values.put(KEY_FROM, progressBarEntry.getFrom());
        values.put(KEY_TO, progressBarEntry.getTo());

        int id = (int) db.insert(TABLE_PROGRESSBARENTRY, null, values);

        db.close();

        return id;
    }

    public ProgressBarEntry getProgressBarEntry(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ProgressBarEntry progressBarEntry = new ProgressBarEntry();

        ContentValues values = new ContentValues();
        Cursor cur = db.rawQuery("SELECT * FROM "+TABLE_PROGRESSBARENTRY+" WHERE id = ?", new String[]{String.valueOf(id)});
        if(cur.moveToNext()){

            progressBarEntry.setId(cur.getInt(0));
            progressBarEntry.setTitle(cur.getString(1));
            progressBarEntry.setDescription(cur.getString(2));
            progressBarEntry.setFrom(cur.getInt(3));
            progressBarEntry.setTo(cur.getInt(4));
        }
        cur.close();
        db.close();
        return progressBarEntry;
    }

    public List<ProgressBarEntry> getAllProgressBarEntries(){
        List<ProgressBarEntry> list = new ArrayList<>();

        String select = "SELECT * FROM "+TABLE_PROGRESSBARENTRY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(select, null);

        if(cur.moveToFirst()){
            do{
                ProgressBarEntry progressBarEntry = new ProgressBarEntry();
                progressBarEntry.setId(cur.getInt(0));
                progressBarEntry.setTitle(cur.getString(1));
                progressBarEntry.setDescription(cur.getString(2));
                progressBarEntry.setFrom(cur.getInt(3));
                progressBarEntry.setTo(cur.getInt(4));
                list.add(progressBarEntry);
            }while(cur.moveToNext());

        }


        db.close();
        cur.close();

        return list;
    }

    public void updateProgressBarEntry(ProgressBarEntry progressBarEntry){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, progressBarEntry.getTitle());
        values.put(KEY_DESCRIPTION, progressBarEntry.getDescription());
        values.put(KEY_FROM, progressBarEntry.getFrom());
        values.put(KEY_TO, progressBarEntry.getTo());

        db.update(TABLE_PROGRESSBARENTRY, values, "id = ?", new String[]{String.valueOf(progressBarEntry.getId())});
        db.close();
    }

    public void deleteProgressBarEntry(ProgressBarEntry progressBarEntry){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROGRESSBARENTRY, KEY_ID+"= ?", new String[]{String.valueOf(progressBarEntry.getId())});
        db.close();
    }
}
