package com.tataev.appyes;

import android.graphics.Bitmap;

/**
 * Created by louas_000 on 10.10.2015.
 */
public class UsersList {

    Bitmap userBitmap;
    String userName;
    Boolean userHistory;
    Boolean userRecommendations;

    public UsersList (Bitmap userBitmap, String userName, Boolean userHistory, Boolean userRecommendations) {
        this.userBitmap = userBitmap;
        this.userName= userName;
        this.userHistory = userHistory;
        this.userRecommendations = userRecommendations;
    }

    public Bitmap getUserBitmap (){

        return this.userBitmap;
    }

    public void setUserBitmap (Bitmap userBitmap){

        this.userBitmap = userBitmap;
    }

    public String getUserName (){

        return this.userName;
    }

    public void setUserName (String userName){

        this.userName = userName;
    }

    public Boolean getUserHistory (){

        return this.userHistory;
    }

    public void setUserHistory (Boolean userHistory){

        this.userHistory = userHistory;
    }

    public Boolean getUserRecommendations (){

        return this.userRecommendations;
    }

    public void setUserRecommendations (Boolean userRecommendations){

        this.userRecommendations = userRecommendations;
    }
}
