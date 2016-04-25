package com.tataev.appyes.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.costum.android.widget.LoadMoreListView;
import com.tataev.appyes.AppConfig;
import com.tataev.appyes.AppController;
import com.tataev.appyes.Defaults;
import com.tataev.appyes.MainActivity;
import com.tataev.appyes.R;
import com.tataev.appyes.UsersList;
import com.tataev.appyes.adapters.UsersAdapter;
import com.tataev.appyes.adapters.UsersSearchAdapter;
import com.tataev.appyes.helper.SQLiteHandlerUser;
import com.tataev.appyes.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Users.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Users#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Users extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ExpandableListView exListView;
    private UsersSearchAdapter usersSearchAdapter;
    private LoadMoreListView loadMoreListView;
    private UsersAdapter usersAdapter;
    private ArrayList<UsersList> usersList = new ArrayList<UsersList>();
    private ArrayList<UsersList> usersList1 = new ArrayList<UsersList>();
    private Bitmap bitmap;
    private ImageView imageRequest;
    private SearchView search_view_main;
    private Parcelable state;
    private static final String TAG = Users.class.getSimpleName();
    private Map<String, String> params;
    private ProgressDialog pDialog;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Users.
     */
    // TODO: Rename and change types and number of parameters
    public static Users newInstance(String param1, String param2) {
        Users fragment = new Users();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Users() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_users, container, false);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Пользователи");

        Defaults.setSearchViewStyle(R.id.searchViewUsers, rootView, getActivity());
        // Progress dialog
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        //Example data
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.users_logo_default);
        usersList = getUsers(usersList);

        //Initialize request icon, search field and users ListView
        imageRequest = (ImageView)rootView.findViewById(R.id.imageRequest);
        exListView = (ExpandableListView) rootView.findViewById(R.id.exListView);
        loadMoreListView = (LoadMoreListView)rootView.findViewById(R.id.loadMoreListView);
        usersList1.addAll(usersList);

        search_view_main = (SearchView)rootView.findViewById(R.id.searchViewUsers);
        search_view_main.setOnClickListener(this);
        imageRequest.setOnClickListener(this);

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        //change the position of the group dropdown indicator
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            exListView.setIndicatorBounds(width-GetPixelFromDips(35), width-GetPixelFromDips(5));
        } else {
            exListView.setIndicatorBoundsRelative(width-GetPixelFromDips(35), width-GetPixelFromDips(5));
        }

        usersSearchAdapter = new UsersSearchAdapter(getActivity());
        usersSearchAdapter.hasStableIds();
        exListView.setAdapter(usersSearchAdapter);

        usersAdapter = new UsersAdapter(getActivity(), usersList);
        loadMoreListView.setAdapter(usersAdapter);
        loadMoreListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Удалить из списка друзей?")
                        .setCancelable(true)
                        .setPositiveButton("Да",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setNegativeButton("Нет",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
                return false;
            }
        });

        (loadMoreListView)
                .setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
                    public void onLoadMore() {
                        // Do the work to load more items at the end of list here
                        new LoadDataTask().execute();
                    }
                });

        return rootView;
    }

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchViewUsers:
                search_view_main.onActionViewExpanded();
                break;
            case R.id.imageRequest:
                Fragment fragment = new Requests();
                Defaults.replaceFragment(fragment, getActivity());
            default:
                break;
        }
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private class LoadDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            if (isCancelled()) {
                return null;
            }

            // Simulates a background task
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            for (int i = 0; i < usersList1.size(); i++)
                usersList.add(usersList1.get(i));

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            usersList.add(new UsersList(bitmap, "Added after load more", true));

            // We need notify the adapter that the data have been changed
            usersAdapter.notifyDataSetChanged();

            // Call onLoadMoreComplete when the LoadMore task, has finished
            loadMoreListView.onLoadMoreComplete();

            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            // Notify the loading more operation has finished
            loadMoreListView.onLoadMoreComplete();
        }
    }

    private ArrayList<UsersList> getUsers(final ArrayList<UsersList> usersListJson) {
        // Tag used to cancel the request
        String tag_string_req = "req_get_users";

        pDialog.setMessage("Loading ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_GET_USERS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Loading Response: " + response.toString());
                hideDialog();

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject jObj = jsonArray.getJSONObject(i);
                            usersListJson.add(new UsersList(bitmap, jObj.getString("surname") + jObj.getString("name"), true));
                        }

                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        Toast.makeText(getActivity().getApplicationContext(),
                                "error loading", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Loading Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

        return usersList;
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
