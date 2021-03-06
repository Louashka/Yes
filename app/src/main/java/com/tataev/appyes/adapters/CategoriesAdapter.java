package com.tataev.appyes.adapters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.tataev.appyes.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by louas_000 on 06.01.2016.
 */
public class CategoriesAdapter extends BaseExpandableListAdapter {

    private Context context;
    private LayoutInflater l_InflaterUA;
    private List<String> listDataHeader = new ArrayList<String>();;
    private HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();

    public CategoriesAdapter (Context context){
        this.context = context;
        l_InflaterUA = LayoutInflater.from(context);
        listDataHeader = Arrays.asList(context.getResources().getStringArray(R.array.categories_items));
        listDataChild.put(listDataHeader.get(0), Arrays.asList(context.getResources().getStringArray(R.array.manCloth)));
        listDataChild.put(listDataHeader.get(1), Arrays.asList(context.getResources().getStringArray(R.array.womanCloth)));
        listDataChild.put(listDataHeader.get(2), Arrays.asList(context.getResources().getStringArray(R.array.childCloth)));
        for (int i = 3; i < listDataHeader.size(); i++){
            listDataChild.put(listDataHeader.get(i), Arrays.asList(context.getResources().getStringArray(R.array.otherCloth)));
        }
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return listDataChild.get(listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return listDataChild.get(listDataHeader.get(groupPosition))
                .get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = l_InflaterUA.inflate(R.layout.categories_group_adapter, null);
        }
        final TextView tv = (TextView)convertView.findViewById(R.id.categories_group_item);
        tv.setText((String)getGroup(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = l_InflaterUA.inflate(R.layout.categories_child_adapter, null);
            holder.categoriesChildItem = (TextView)convertView.findViewById(R.id.categories_child_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final String childText = (String) getChild(groupPosition, childPosition);
        holder.categoriesChildItem.setText(childText);

        return convertView;
    }


    static class ViewHolder {
        private TextView categoriesChildItem;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

