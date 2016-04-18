package com.tataev.appyes.helper;

/**
 * Created by louas_000 on 10.03.2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandlerUser extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandlerUser.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "yes";

    // Login table name
    private static final String TABLE_USER = "users";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_UID = "uid";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_PHOTO = "photo";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_HISTORY = "history";
    private static final String KEY_RECOMMENDATIONS = "recommendations";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_UPDATED_AT = "updated_at";

    public SQLiteHandlerUser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LOGIN + " TEXT UNIQUE,"
                + KEY_EMAIL + " TEXT UNIQUE," + KEY_UID + " TEXT,"
                + KEY_NAME + " TEXT," + KEY_SURNAME + " TEXT,"
                + KEY_PHOTO + " TEXT," + KEY_BIRTHDAY + " TEXT,"
                + KEY_GENDER + " TEXT," + KEY_ADDRESS + " TEXT," + KEY_HISTORY + " BOOLEAN,"
                + KEY_RECOMMENDATIONS + " BOOLEAN," + KEY_UPDATED_AT + " TEXT,"
                + KEY_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String login, String email, String uid, String name, String surname, String photo, String birthday,
                        String gender, String address, Integer history, Integer recommendations, String created_at, String updated_at) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN, login); // Login
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_UID, uid); // Uid
        values.put(KEY_NAME, name); // Name
        values.put(KEY_SURNAME, surname); // Surname
        values.put(KEY_PHOTO, photo); // URL
        values.put(KEY_BIRTHDAY, birthday); // Birthday
        values.put(KEY_GENDER, gender); // Gender
        values.put(KEY_ADDRESS, address); // Address
        values.put(KEY_HISTORY, history); // History
        values.put(KEY_RECOMMENDATIONS, recommendations); // Recommendations
        values.put(KEY_CREATED_AT, created_at); // Created At
        values.put(KEY_UPDATED_AT, updated_at); // Updated At


        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("login", cursor.getString(1));
            user.put("email", cursor.getString(2));
            user.put("uid", cursor.getString(3));
            user.put("name", cursor.getString(4));
            user.put("surname", cursor.getString(5));
            user.put("photo", cursor.getString(6));
            user.put("birthday", cursor.getString(7));
            user.put("gender", cursor.getString(8));
            user.put("address", cursor.getString(9));
            user.put("history", cursor.getString(10));
            user.put("recommendations", cursor.getString(11));
            user.put("created_at", cursor.getString(12));
            user.put("updated_at", cursor.getString(13));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

    public void updateUser(String email, String uid, String name, String surname, String photo, String birthday, String gender,
                           String address, Integer history, Integer recommendations, String created_at, String updated_at){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_UID, uid); // Uid
        values.put(KEY_NAME, name); // Name
        values.put(KEY_SURNAME, surname); // Surname
        values.put(KEY_PHOTO, photo); // URL
        values.put(KEY_BIRTHDAY, birthday); // Birthday
        values.put(KEY_GENDER, gender); // Gender
        values.put(KEY_ADDRESS, address); // Address
        values.put(KEY_HISTORY, history); // History
        values.put(KEY_RECOMMENDATIONS, recommendations); // Recommendations
        values.put(KEY_CREATED_AT, created_at); // Created At
        values.put(KEY_UPDATED_AT, updated_at); // Updated At

        // Updating Row
        long id = db.update(TABLE_USER, values, KEY_UID + "= ?", new String[]{uid});;
        db.close(); // Closing database connection

        Log.d(TAG, "User updated" + id);
    }

}
