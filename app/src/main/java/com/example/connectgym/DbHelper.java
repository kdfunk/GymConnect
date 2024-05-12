package com.example.connectgym;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Database helper for GymConnect app that manages database creation and version management.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "ConnectGym.db";

    // SQL Statements for creating tables
    private static final String SQL_CREATE_GYMS = "CREATE TABLE IF NOT EXISTS Gyms (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "gymName TEXT NOT NULL," +
            "location TEXT NOT NULL," +
            "priceRange TEXT NOT NULL," +
            "classType TEXT NOT NULL);";

    private static final String SQL_CREATE_CLASSES = "CREATE TABLE IF NOT EXISTS Classes (" +
            "_id INTEGER PRIMARY KEY," +
            "className TEXT," +
            "gymId INTEGER," +
            "type TEXT," +
            "difficulty TEXT," +
            "FOREIGN KEY(gymId) REFERENCES Gyms(_id));";

    // SQL Statements for dropping tables
    private static final String SQL_DELETE_GYMS = "DROP TABLE IF EXISTS Gyms";
    private static final String SQL_DELETE_CLASSES = "DROP TABLE IF EXISTS Classes";

    private static final String SQL_CREATE_USERS = "CREATE TABLE IF NOT EXISTS Users (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT NOT NULL," +
            "email TEXT NOT NULL," +
            "password TEXT NOT NULL)";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_GYMS);
        db.execSQL(SQL_CREATE_CLASSES);
        db.execSQL(SQL_CREATE_USERS);
        Log.d("DbHelper", "Creating Users table");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_GYMS);
        db.execSQL(SQL_DELETE_CLASSES);
        onCreate(db);
        Log.d("DbHelper", "Creating Users table");

    }

    // Add a user with hashed password
    public boolean addUser(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("email", email);
        try {
            values.put("password", hashPassword(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }

        long result = db.insert("Users", null, values);
        db.close();
        return result != -1;
    }

    // Check user credentials
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            password = hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }

        String[] columns = {UserEntry._ID};
        String selection = UserEntry.COLUMN_NAME_USERNAME + "=? AND " + UserEntry.COLUMN_NAME_PASSWORD + "=?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(UserEntry.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;

    }

    // Hash passwords
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashedPassword) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Nested classes for table entries
    public static class GymsEntry implements BaseColumns {
        public static final String TABLE_NAME = "Gyms";
        public static final String COLUMN_NAME_GYM_NAME = "gymName";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_PRICE_RANGE = "priceRange";
        public static final String COLUMN_NAME_CLASS_TYPE = "classType";
    }

    public static class ClassesEntry implements BaseColumns {
        public static final String TABLE_NAME = "Classes";
        public static final String COLUMN_NAME_CLASS_NAME = "className";
        public static final String COLUMN_NAME_GYM_ID = "gymId";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_DIFFICULTY = "difficulty";
    }

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }

    // Update gym data in the database
    public void updateGymData(List<Gym> gyms) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.beginTransaction();
            for (Gym gym : gyms) {
                ContentValues values = new ContentValues();
                values.put(GymsEntry.COLUMN_NAME_GYM_NAME, gym.getName());
                values.put(GymsEntry.COLUMN_NAME_LOCATION, gym.getLocation());
                values.put(GymsEntry.COLUMN_NAME_PRICE_RANGE, gym.getPriceRange());
                values.put(GymsEntry.COLUMN_NAME_CLASS_TYPE, gym.getClassType());

                db.insertWithOnConflict(GymsEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {

        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public static class Recommendation {
        private String gymName;
        private String details;  // Example attributes

        // Constructor
        public Recommendation(String gymName, String details) {
            this.gymName = gymName;
            this.details = details;
        }

        // Getters
        public String getGymName() {
            return gymName;
        }

        public String getDetails() {
            return details;
        }

        public static List<Recommendation> generateRecommendations(User user, HashMap<String, User> dummyUsers) {
            List<Recommendation> recommendations = new ArrayList<>();
            // Logic to generate recommendations based on user preferences and dummy users
            recommendations.add(new Recommendation("Gym A", "Recommended for your fitness goals!"));
            recommendations.add(new Recommendation("Gym B", "Great for weight lifting!"));
            return recommendations;
        }
    }


}
