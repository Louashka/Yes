package com.tataev.appyes.adapters;

import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.tataev.appyes.AppController;
import com.tataev.appyes.R;

/**
 * Created by louas_000 on 10.10.2015.
 */
public class UsersSearchAdapter extends BaseExpandableListAdapter {

    private Context context;
    private LayoutInflater l_InflaterUA;
    private AppController userGlobalClass;
    private int k = 0;
    private int Countries[] = new int[]{
            R.array.city_default,
            R.array.city_armenia,
            R.array.city_azerbaijan,
            R.array.city_byelorussia,
            R.array.city_kazakhstan,
            R.array.city_kyrgyzstan,
            R.array.city_moldavia,
            R.array.city_russia,
            R.array.city_tajikistan,
            R.array.city_turkmenistan,
            R.array.city_ukraine,
            R.array.city_uzbekistan
    };

    public UsersSearchAdapter (Context context){
        this.context = context;
        l_InflaterUA = LayoutInflater.from(context);
        userGlobalClass = (AppController)context.getApplicationContext();
    }

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = l_InflaterUA.inflate(R.layout.list_group_adapter, null);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = l_InflaterUA.inflate(R.layout.users_search_adapter, null);
            holder.editTextAgeFrom = (EditText)convertView.findViewById(R.id.editTextAgeFrom);
            holder.editTextAgeTo = (EditText)convertView.findViewById(R.id.editTextAgeTo);
            holder.spinnerCountry = (Spinner)convertView.findViewById(R.id.spinnerCountry);
            holder.spinnerCity = (Spinner)convertView.findViewById(R.id.spinnerCity);
            holder.userGender = (RadioGroup)convertView.findViewById(R.id.user_gender);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.userGender.check(userGlobalClass.getRadioGender());
        holder.userGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                userGlobalClass.setRadioGender(checkedId);
            }
        });

        holder.spinnerCountry.setSelection(userGlobalClass.getSpinnerCountryItem());
        holder.spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != userGlobalClass.getSpinnerCountryItem()) k++;
                userGlobalClass.setSpinnerCountryItem(position);
                holder.spinnerCity.setAdapter(ArrayAdapter.createFromResource(context,
                        Countries[position], android.R.layout.simple_spinner_item));
                if (k == 0){
                    holder.spinnerCity.setSelection(userGlobalClass.getSpinnerCityItem());
                } else {
                    holder.spinnerCity.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                holder.spinnerCity.setAdapter(ArrayAdapter.createFromResource(context,
                        R.array.city_default, android.R.layout.simple_spinner_item));
            }
        });

        holder.spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userGlobalClass.setSpinnerCityItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        holder.editTextAgeFrom.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
        holder.editTextAgeTo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class ViewHolder {
        private EditText editTextAgeFrom;
        private EditText editTextAgeTo;
        private Spinner spinnerCountry;
        private Spinner spinnerCity;
        private RadioGroup userGender;
    }

}

