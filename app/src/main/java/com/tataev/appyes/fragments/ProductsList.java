package com.tataev.appyes.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.tataev.appyes.Goods;
import com.tataev.appyes.R;
import com.tataev.appyes.adapters.SubCategoriesAdapter;
import com.tataev.appyes.adapters.SubCategoriesAdapterCard;
import com.tataev.appyes.adapters.SubCategoriesAdapterGrid;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductsList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductsList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsList extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView listView,listViewCard;
    ArrayList<Goods> listData;
    SubCategoriesAdapter adapter;
    SubCategoriesAdapterCard adapterCard;
    SubCategoriesAdapterGrid adapterGrid;
    GridView gridView;

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
     * @return A new instance of fragment ProductsList.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductsList newInstance(String param1, String param2) {
        ProductsList fragment = new ProductsList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ProductsList() {
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
        View rootView = inflater.inflate(R.layout.fragment_products_list, container, false);

        listView = (ListView) rootView.findViewById(R.id.listView);
        GridView gridView = (GridView) rootView.findViewById(R.id.gridView);
        gridView.setVisibility(View.GONE);
        listViewCard = (ListView)rootView.findViewById(R.id.listViewCard);
        listViewCard.setVisibility(View.GONE);

        ImageView imageCard = (ImageView)rootView.findViewById(R.id.imageCard);
        ImageView imageGrid = (ImageView)rootView.findViewById(R.id.imageGrid);
        ImageView imageList = (ImageView)rootView.findViewById(R.id.imageList);
        imageCard.setOnClickListener(this);
        imageGrid.setOnClickListener(this);
        imageList.setOnClickListener(this);

        listData = getData();
        if (listData !=null){
            adapter = new SubCategoriesAdapter(getActivity(), listData);
            listView.setAdapter(adapter);
            adapterGrid = new SubCategoriesAdapterGrid(getActivity(), listData);
            gridView.setAdapter(adapterGrid);
            adapterCard = new SubCategoriesAdapterCard(getActivity(), listData);
            listViewCard.setAdapter(adapterCard);
        }

        this.gridView = gridView;
        this.listView = listView;

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
        switch (v.getId()){
            case R.id.imageCard:
                gridView.setVisibility(v.GONE);
                listView.setVisibility(v.GONE);
                listViewCard.setVisibility(View.VISIBLE);
                break;
            case R.id.imageGrid:
                gridView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                listViewCard.setVisibility(View.GONE);
                gridView.setNumColumns(GridView.AUTO_FIT);
                break;
            case R.id.imageList:
                gridView.setVisibility(View.GONE);
                listViewCard.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                break;
        }
    }

    public ArrayList<Goods> getData() {
        ArrayList<Goods> List = new ArrayList<>();

        Goods good1 = new Goods();
        good1.setTitle("Футболки");
        good1.setPrice("33$");
        good1.setLastPrice("40$");
        good1.setDiscount("-30%");
        good1.setAction("Акция!");
        good1.setFreeDelivering("Бесплатная доставка");
        good1.setTime("Время");
        good1.setOrdering("30 Бронь");
        good1.setNumOfSelled("115 продано");
        good1.setImageIcon(R.drawable.ic_tshirt);
        List.add(good1);

        Goods good2 = new Goods();
        good2.setTitle("Футболки");
        good2.setPrice("3000$");
        good2.setLastPrice("500000$");
        good2.setDiscount("-30%");
        good2.setAction("Акция!");
        good2.setFreeDelivering("Бесплатная доставка");
        good2.setTime("Время");
        good2.setOrdering("30 Бронь");
        good2.setNumOfSelled("50 продано");
        good2.setImageIcon(R.drawable.ic_tshirt);
        List.add(good2);

        Goods good3 = new Goods();
        good3.setTitle("Футболки");
        good3.setPrice("33$");
        good3.setLastPrice("40$");
        good3.setDiscount("-30%");
        good3.setAction("Акция!");
        good3.setFreeDelivering("Бесплатная доставка");
        good3.setTime("Время");
        good3.setOrdering("30 Бронь");
        good3.setNumOfSelled("115 продано");
        good3.setImageIcon(R.drawable.ic_tshirt);
        List.add(good3);


        Goods good4 = new Goods();
        good4.setTitle("Футболки");
        good4.setPrice("33$");
        good4.setLastPrice("40$");
        good4.setDiscount("-30%");
        good4.setAction("Акция!");
        good4.setFreeDelivering("Бесплатная доставка");
        good4.setTime("Время");
        good4.setOrdering("30 Бронь");
        good4.setNumOfSelled("115 продано");
        good4.setImageIcon(R.drawable.ic_tshirt);
        List.add(good4);


        return List;
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
