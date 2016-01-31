package com.tataev.appyes;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by louas_000 on 10.10.2015.
 */
public class Defaults {

    private static String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static String DB_CONNECTION = "jdbc:mysql://46.36.220.110:3306/yes";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "root";

    // Open fragment when clicking menu TextView
    public static void replaceFragment(Fragment frag, FragmentActivity activity){
        FragmentTransaction fTrans;
        FragmentManager fragmentManager;
        if (frag != null) {
            fragmentManager = activity.getSupportFragmentManager();
            fTrans = fragmentManager.beginTransaction();
            // Add Fragment MenuItems to content_frame
            fTrans.replace(R.id.content_frame, frag);
            fTrans.addToBackStack(null);
            fTrans.commit();
        }
    }

    public static void setSearchViewStyle(int searchViewId, View rootView, FragmentActivity activity){
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) activity.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) rootView.findViewById(searchViewId);
        int id = searchView.getContext()
                .getResources()
                .getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setHintTextColor(activity.getResources().getColor(R.color.menu_text));
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default
    }

    // database connection
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    // add new statement to database
    public static void createDbUserTable(String insertTableSQL) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выполнить SQL запрос
            statement.executeUpdate(insertTableSQL);
            System.out.println("Table is updated!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
}
