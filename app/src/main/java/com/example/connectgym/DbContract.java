package com.example.connectgym;

import android.provider.BaseColumns;

public final class DbContract {


    private DbContract() {}


    public static class GymsEntry implements BaseColumns {
        public static final String TABLE_NAME = "gyms";
        public static final String COLUMN_NAME_GYM_NAME = "gym_name";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_PRICE_RANGE = "price_range";
        public static final String COLUMN_NAME_CLASS_TYPE = "type";
    }

    public static class ClassesEntry implements BaseColumns {
        public static final String TABLE_NAME = "classes";
        public static final String COLUMN_NAME_CLASS_NAME = "class_name";
        public static final String COLUMN_NAME_GYM_ID = "gym_id"; // Foreign key to Gyms table
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_DIFFICULTY = "difficulty";
    }
}