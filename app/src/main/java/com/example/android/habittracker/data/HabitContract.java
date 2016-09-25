package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Zohaib on 25/09/16.
 */
public class HabitContract {

    private HabitContract() {
    }

    public static final class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "habits";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_HABIT_NAME = "habit";
        public final static String COLUMN_HABIT_HOURS = "hours";
    }
}
