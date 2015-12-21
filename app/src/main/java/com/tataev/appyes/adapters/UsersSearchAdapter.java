package com.tataev.appyes.adapters;

import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;

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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = l_InflaterUA.inflate(R.layout.users_search_adapter, null);
            holder.editTextAgeFrom = (EditText)convertView.findViewById(R.id.editTextAgeFrom);
            holder.editTextAgeTo = (EditText)convertView.findViewById(R.id.editTextAgeTo);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        holder.editTextAgeFrom.setFilters(new InputFilter[]{ new InputFilterMinMax("12", "99")});
//        holder.editTextAgeTo.setFilters(new InputFilter[]{ new InputFilterMinMax("12", "99")});
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class ViewHolder {
        private EditText editTextAgeFrom;
        private EditText editTextAgeTo;
    }

}

