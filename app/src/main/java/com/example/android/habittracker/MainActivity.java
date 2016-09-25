package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        insertHabit();
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
                HabitEntry.COLUMN_HABIT_HOURS };

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
        while (cursor.moveToNext()) {
            result.append(cursor.getString(habitNameIndex) + " - "
                            + cursor.getInt(habitHoursIndex) + "\n");
        }
        TextView text = (TextView) findViewById(R.id.text_view);
        text.setText(result.toString());
    }
}
