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
import android.widget.Spinner;

import com.tataev.appyes.InputFilterMinMax;
import com.tataev.appyes.R;

/**
 * Created by louas_000 on 10.10.2015.
 */
public class UsersSearchAdapter extends BaseExpandableListAdapter {

    private Context context;
    private LayoutInflater l_InflaterUA;

    public UsersSearchAdapter (Context context){
        this.context = context;
        l_InflaterUA = LayoutInflater.from(context);
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
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        holder.spinnerCity.setAdapter(ArrayAdapter.createFromResource(context,
                                R.array.city_default, android.R.layout.simple_spinner_item));
                        break;
                    case 1:
                        holder.spinnerCity.setAdapter(ArrayAdapter.createFromResource(context,
                                R.array.city_azerbaijan, android.R.layout.simple_spinner_item));
                        break;
                    case 2:
                        holder.spinnerCity.setAdapter(ArrayAdapter.createFromResource(context,
                                R.array.city_armenia, android.R.layout.simple_spinner_item));
                        break;
                    case 3:
                        holder.spinnerCity.setAdapter(ArrayAdapter.createFromResource(context,
                                R.array.city_byelorussia, android.R.layout.simple_spinner_item));
                        break;
                    case 4:
                        holder.spinnerCity.setAdapter(ArrayAdapter.createFromResource(context,
                                R.array.city_kazakhstan, android.R.layout.simple_spinner_item));
                        break;
                    case 5:
                        holder.spinnerCity.setAdapter(ArrayAdapter.createFromResource(context,
                                R.array.city_kyrgyzstan, android.R.layout.simple_spinner_item));
                        break;
                    case 6:
                        holder.spinnerCity.setAdapter(ArrayAdapter.createFromResource(context,
                                R.array.city_moldavia, android.R.layout.simple_spinner_item));
                        break;
                    case 7:
                        holder.spinnerCity.setAdapter(ArrayAdapter.createFromResource(context,
                                R.array.city_russia, android.R.layout.simple_spinner_item));
                        break;
                    case 8:
                        holder.spinnerCity.setAdapter(ArrayAdapter.createFromResource(context,
                                R.array.city_tajikistan, android.R.layout.simple_spinner_item));
                        break;
                    case 9:
                        holder.spinnerCity.setAdapter(ArrayAdapter.createFromResource(context,
                                R.array.city_turkmenistan, android.R.layout.simple_spinner_item));
                        break;
                    case 10:
                        holder.spinnerCity.setAdapter(ArrayAdapter.createFromResource(context,
                                R.array.city_uzbekistan, android.R.layout.simple_spinner_item));
                        break;
                    case 11:
                        holder.spinnerCity.setAdapter(ArrayAdapter.createFromResource(context,
                                R.array.city_ukraine, android.R.layout.simple_spinner_item));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                holder.spinnerCity.setAdapter(ArrayAdapter.createFromResource(context,
                        R.array.city_default, android.R.layout.simple_spinner_item));
            }
        });
//        holder.editTextAgeFrom.setFilters(new InputFilter[]{new InputFilterMinMax("12", "99")});
//        holder.editTextAgeTo.setFilters(new InputFilter[]{new InputFilterMinMax("12", "99")});

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
    }

}

