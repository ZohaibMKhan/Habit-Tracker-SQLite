package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);
        //insertHabit();
        //deleteHabit(3);
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "zohaib");
        values.put(HabitEntry.COLUMN_HABIT_HOURS, 1);
        updateHabit(1, values);
        readHabits();
    }


    private void insertHabit() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(HabitEntry.COLUMN_HABIT_NAME, "Habit dummy");
        values.put(HabitEntry.COLUMN_HABIT_HOURS, 2);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    private void readHabits() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_HOURS};

            Cursor cursor = db.query(HabitEntry.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null);

            StringBuilder result = new StringBuilder();
            int habitNameIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int habitHoursIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_HOURS);
        try {
            while (cursor.moveToNext()) {
                result.append(cursor.getString(habitNameIndex) + " - "
                        + cursor.getInt(habitHoursIndex) + "\n");
            }
            TextView text = (TextView) findViewById(R.id.text_view);
            text.setText(result.toString());
        }
        finally {
            cursor.close();
        }
    }

    public void deleteHabit(int rowId) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String[] whereArgs = {String.valueOf(rowId)};
        db.delete(HabitEntry.TABLE_NAME,
                HabitEntry._ID + "=?",
                whereArgs);
    }

    public void updateHabit(int rowId, ContentValues values) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String[] whereArgs = {String.valueOf(rowId)};
        long newRowId = db.update(HabitEntry.TABLE_NAME, values, HabitEntry._ID + " =?", whereArgs);
        Log.v("MainActivity.class", String.valueOf(newRowId));
    }
}
