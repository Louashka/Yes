package com.tataev.appyes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by louas_000 on 10.10.2015.
 */
public class Defaults {

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
}
