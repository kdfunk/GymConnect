package com.example.connectgym;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbActivity extends AppCompatActivity {

    private DbHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_layout);

        // DbHelper and SQLiteDatabase
        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();

        try {
            // Database query using these column names
            Cursor cursor = db.query(
                    GymContract.GymEntry.TABLE_NAME,  // The table to query
                    new String[] {                    // The columns to return
                            GymContract.GymEntry.COLUMN_NAME_GYM_NAME,
                            GymContract.GymEntry.COLUMN_NAME_LOCATION,
                            GymContract.GymEntry.COLUMN_NAME_CLASS_TYPE
                    },
                    null,   // The columns for the WHERE clause
                    null,   // The values for the WHERE clause
                    null,   // don't group the rows
                    null,   // don't filter by row groups
                    null    // The sort order
            );

            // Use the cursor to do something
            if (cursor.moveToFirst()) {
                do {
                    // Extract data from cursor
                    String gymName = cursor.getString(cursor.getColumnIndexOrThrow(GymContract.GymEntry.COLUMN_NAME_GYM_NAME));
                    // Use data
                } while (cursor.moveToNext());
            }

            // Close when done
            cursor.close();
        } catch (Exception e) {
            // Handle potential errors
        }
    }

    @Override
    protected void onDestroy() {
        if (db != null && db.isOpen()) {
            db.close();
        }
        if (dbHelper != null) {
            dbHelper.close();
        }
        super.onDestroy();
    }
}
