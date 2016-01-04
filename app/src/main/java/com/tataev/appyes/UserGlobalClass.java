package com.tataev.appyes;

import android.app.Application;

/**
 * Created by lou on 04.01.2016.
 */
public class UserGlobalClass extends Application {
    private int spinnerCountryItem;
    private int spinnerCityItem;
    private int radioGender;
    private int ageFrom;
    private int ageTo;

    public int getSpinnerCountryItem(){
        return spinnerCountryItem;
    }
    public void setSpinnerCountryItem(int spinnerCountryItem){
        this.spinnerCountryItem = spinnerCountryItem;
    }

    public int getSpinnerCityItem(){
        return spinnerCityItem;
    }
    public void setSpinnerCityItem(int spinnerCityItem){
        this.spinnerCityItem = spinnerCityItem;
    }

    public int getRadioGender(){
        return radioGender;
    }
    public void setRadioGender(int radioGender){
        this.radioGender = radioGender;
    }

    public int getAgeFrom(){
        return ageFrom;
    }
    public void setAgeFrom(int ageFrom){
        this.ageFrom = ageFrom;
    }

    public int getAgeTo(){
        return ageTo;
    }
    public void setAgeTo(int ageTo){
        this.ageTo = ageTo;
    }

}
