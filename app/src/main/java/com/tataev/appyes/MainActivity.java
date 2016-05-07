package com.tataev.appyes;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.tataev.appyes.fragments.MenuItems;
import com.tataev.appyes.fragments.Nearby;

public class MainActivity extends AppCompatActivity {

    private Fragment menuFrag;
    private FragmentTransaction fTrans;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Display launcher icon
        ActionBar ab = getSupportActionBar();
        ab.setIcon(R.drawable.ic_launcher_icon);
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_action_home_row);
        getSupportActionBar().setElevation(0);

        // Initialize Fragment with menu items
        menuFrag = new MenuItems();
        // Begin Fragment replacing
        if (menuFrag != null) {
            fragmentManager = getSupportFragmentManager();
            fTrans = fragmentManager.beginTransaction();
            // Add Fragment MenuItems to content_frame
            fTrans.replace(R.id.content_frame, menuFrag);
            fTrans.commit();
        }

        //Listen for changes in the back stack
        getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        int stackHeight = getSupportFragmentManager().getBackStackEntryCount();
                        if (stackHeight > 0) { // if we have something on the stack (doesn't include the current shown fragment)
                            getSupportActionBar().setHomeButtonEnabled(true);
                            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        } else {
                            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                            getSupportActionBar().setHomeButtonEnabled(false);
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Скачайте приложение Yes!");
                intent.putExtra(Intent.EXTRA_TEXT, "\nСоветую использовать приложение YES\n http://www.tataev.com");
                startActivity(Intent.createChooser(intent, "Share"));
                break;
            case R.id.navigation:
                menuFrag = new Nearby();
                Defaults.replaceFragment(menuFrag, this);
                break;
            case android.R.id.home:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                Fragment fragment = new MenuItems();
//                Defaults.replaceFragment(fragment, this);
                return true;
            default:
                break;
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

}
