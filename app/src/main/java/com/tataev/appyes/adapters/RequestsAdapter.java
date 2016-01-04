package com.tataev.appyes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tataev.appyes.R;
import com.tataev.appyes.RoundImage;
import com.tataev.appyes.UsersList;

import java.util.ArrayList;

/**
 * Created by lou on 04.01.2016.
 */
public class RequestsAdapter extends BaseAdapter {

    private ArrayList<UsersList> requestsList;
    private Context context;
    private LayoutInflater l_InflaterUA;

    public RequestsAdapter (Context context, ArrayList<UsersList> requestsList) {
        this.context = context;
        this.requestsList = requestsList;
        l_InflaterUA = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return requestsList.size();
    }

    @Override
    public Object getItem(int position) {
        return requestsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        RoundImage roundedImage = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = l_InflaterUA.inflate(R.layout.requests_list_adapter, null);
            holder.imageRequestAva = (ImageView)convertView.findViewById(R.id.imageRequestAva);
            holder.usersNameRequest = (TextView)convertView.findViewById(R.id.usersNameRequest);
            holder.usersStatusRequest = (RadioButton)convertView.findViewById(R.id.usersStatusRequest);
            roundedImage = new RoundImage(requestsList.get(position).getUserBitmap(), 180, 180);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (roundedImage != null){
            holder.imageRequestAva.setImageDrawable(roundedImage);
        }
        holder.usersNameRequest.setText(requestsList.get(position).getUserName());
        holder.usersStatusRequest.setChecked(requestsList.get(position).getUserStatus());
        return convertView;
    }

    static class ViewHolder {
        ImageView imageRequestAva;
        TextView usersNameRequest;
        RadioButton usersStatusRequest;
    }
}
