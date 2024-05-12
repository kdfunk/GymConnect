package com.example.connectgym;

import android.provider.BaseColumns;

public final class GymContract {

    private GymContract() {}


    public static class GymEntry implements BaseColumns {
        public static final String TABLE_NAME = "gym";
        public static final String COLUMN_NAME_GYM_NAME = "name";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_CLASS_TYPE = "class_type";
        public static final String COLUMN_NAME_PRICE_RANGE = "price_range";
    }

    public static class ClassEntry implements BaseColumns {
        public static final String TABLE_NAME = "class";
        public static final String COLUMN_NAME_CLASS_NAME = "class_name";
        public static final String COLUMN_NAME_CLASS_TYPE = "class_type";
        // Add other column names as needed
    }
}
