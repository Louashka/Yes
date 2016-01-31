package com.tataev.appyes.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.tataev.appyes.Defaults;
import com.tataev.appyes.MainActivity;
import com.tataev.appyes.R;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoriesChildList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoriesChildList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesChildList extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView menu_ccl_tab;
    private TextView nearby_ccl_tab;
    private TextView novelty_ccl_tab;
    private TextView favor_ccl_tab;
    private TextView reservation_ccl_tab;
    private TextView categories_ccl_tab;
    private Fragment fragment;
    private SearchView search_view_ccl;
    private ListView listViewCCL;
    private int groupPosition;
    private int childPosition;
    private String[] listItems;
    private int[][] itemsArray = new int[][]{
            {R.array.upForManArray, R.array.bottomForMan, R.array.jacetsAndCoatsArray, R.array.suitAndBlazerArray,
            R.array.sportWearArray, R.array.indoorClothingArray, R.array.tranksAndShortArray, R.array.accessoriesForManArray},
            {R.array.popularForWomanArray, R.array.bottomForWomanArray, R.array.topForWomanArray, R.array.weddingDressArray,
            R.array.womanUnderClothArrray, R.array.indoorClothingArray, R.array.womanSportWearArray, R.array.pregnantWearArray, R.array.womanAccessoryArray},
            {R.array.babiesGoodArray, R.array.girlieGoodsArray, R.array.girlGoodsArray}
    };

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
     * @return A new instance of fragment CategoriesChildList.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoriesChildList newInstance(String param1, String param2) {
        CategoriesChildList fragment = new CategoriesChildList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CategoriesChildList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            groupPosition = getArguments().getInt("groupPosition");
            childPosition = getArguments().getInt("childPosition");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_categories_child_list, container, false);

        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Категории");

        Defaults.setSearchViewStyle(R.id.searchViewCCL, rootView, getActivity());

        //Initialize tab menu icons
        menu_ccl_tab = (TextView)rootView.findViewById(R.id.menu_ccl_tab);
        nearby_ccl_tab = (TextView)rootView.findViewById(R.id.nearby_ccl_tab);
        novelty_ccl_tab = (TextView)rootView.findViewById(R.id.novelty_ccl_tab);
        favor_ccl_tab = (TextView)rootView.findViewById(R.id.favor_ccl_tab);
        reservation_ccl_tab = (TextView)rootView.findViewById(R.id.reservation_ccl_tab);
        categories_ccl_tab = (TextView)rootView.findViewById(R.id.categories_ccl_tab);
        search_view_ccl = (SearchView)rootView.findViewById(R.id.searchViewCCL);
        listViewCCL = (ListView)rootView.findViewById(R.id.listViewCCL);

        try {
            listItems = getResources().getStringArray(itemsArray[groupPosition][childPosition]);
            listViewCCL.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listItems));
            listViewCCL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    fragment = new ProductsList();
                    Defaults.replaceFragment(fragment, getActivity());
                }
            });
        } catch (Exception  e) {
            e.printStackTrace();
            return null;
        }

        // Set OnClickListener to menu icons
        menu_ccl_tab.setOnClickListener(this);
        nearby_ccl_tab.setOnClickListener(this);
        novelty_ccl_tab.setOnClickListener(this);
        favor_ccl_tab.setOnClickListener(this);
        reservation_ccl_tab.setOnClickListener(this);
        categories_ccl_tab.setOnClickListener(this);
        search_view_ccl.setOnClickListener(this);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_ccl_tab:
                fragment = new MenuItems();
                Defaults.replaceFragment(fragment, getActivity());
                break;
            case R.id.nearby_ccl_tab:
                fragment = new Nearby();
                Defaults.replaceFragment(fragment, getActivity());
                break;
            case R.id.novelty_ccl_tab:
                fragment = new Novelty();
                Defaults.replaceFragment(fragment, getActivity());
                break;
            case R.id.favor_ccl_tab:
                fragment = new Favourites();
                Defaults.replaceFragment(fragment, getActivity());
                break;
            case R.id.reservation_ccl_tab:
                fragment = new Reservation();
                Defaults.replaceFragment(fragment, getActivity());
                break;
            case R.id.categories_ccl_tab:
                fragment = new Categories();
                Defaults.replaceFragment(fragment, getActivity());
                break;
            case R.id.searchViewCCL:
                search_view_ccl.onActionViewExpanded();
                break;
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
