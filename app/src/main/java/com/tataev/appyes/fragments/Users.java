package com.tataev.appyes.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.tataev.appyes.Defaults;
import com.tataev.appyes.MainActivity;
import com.tataev.appyes.R;
import com.tataev.appyes.UsersList;
import com.tataev.appyes.adapters.UsersAdapter;
import com.tataev.appyes.adapters.UsersSearchAdapter;

import java.util.ArrayList;

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
    private ListView listViewUsers;
    private UsersAdapter usersAdapter;
    private ArrayList<UsersList> usersList = new ArrayList<UsersList>();
    private Bitmap bitmap;
    private ImageView imageRequest;
    private SearchView search_view_main;

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
        //Example data
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.users_logo_default);
        usersList.add(new UsersList(bitmap, "Khasbulat Designer", true));
        usersList.add(new UsersList(bitmap, "Mikail Designer", false));
        usersList.add(new UsersList(bitmap, "Khasbulat Designer", true));
        usersList.add(new UsersList(bitmap, "Khasbulat Designer", false));
        usersList.add(new UsersList(bitmap, "Khasbulat Designer", false));

        //Initialize request icon, search field and users ListView
        imageRequest = (ImageView)rootView.findViewById(R.id.imageRequest);
        exListView = (ExpandableListView) rootView.findViewById(R.id.exListView);
        listViewUsers = (ListView)rootView.findViewById(R.id.listViewUsers);

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
        exListView.setAdapter(usersSearchAdapter);

        usersAdapter = new UsersAdapter(getActivity(), usersList);
        listViewUsers.setAdapter(usersAdapter);

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

}
