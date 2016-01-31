package com.tataev.appyes.fragments;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tataev.appyes.Defaults;
import com.tataev.appyes.MainActivity;
import com.tataev.appyes.R;
import com.tataev.appyes.RoundImage;

import java.sql.SQLException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Registration.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Registration#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registration extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Uri mImageCaptureUri;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private ImageView imageRegLogo;
    private Button buttonReg;
    private String insertTableSQL;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registration.
     */
    // TODO: Rename and change types and number of parameters
    public static Registration newInstance(String param1, String param2) {
        Registration fragment = new Registration();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Registration() {
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
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Регистрация");
        View rootView = inflater.inflate(R.layout.fragment_registration, container, false);
        imageRegLogo = (ImageView)rootView.findViewById(R.id.imageRegLogo);
        buttonReg = (Button)rootView.findViewById(R.id.buttonReg);

        imageRegLogo.setMaxWidth(350);
        imageRegLogo.setMaxHeight(350);
        imageRegLogo.setOnClickListener(this);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK)
            return;
        switch (requestCode) {
            case RESULT_LOAD_IMAGE:
                mImageCaptureUri = data.getData();
                doCrop();
                break;
            case CROP_FROM_CAMERA:
                Bundle extras = data.getExtras();
                if (extras != null) {
                    try {
                        Bitmap mBitmap = extras.getParcelable("data");
                        RoundImage roundedImage = new RoundImage(mBitmap, 350, 350);
                        imageRegLogo.setImageDrawable(roundedImage);
                    } catch(Exception e){
                        Toast.makeText(getActivity(), "Failed loading image from gallery", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                break;
        }

    }

    private void doCrop() {
        /**
         * Open image crop app by starting an intent
         * ‘com.android.camera.action.CROP‘.
         */
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");

        /**
         * Check if there is image cropper app installed.
         */
        List<ResolveInfo> list = getActivity().getPackageManager().queryIntentActivities(
                intent, 0);

        int size = list.size();

        /**
         * If there is no image cropper app, display warning message
         */
        if (size == 0) {

            Toast.makeText(getActivity(), "Can not find image crop app",
                    Toast.LENGTH_SHORT).show();

            return;
        } else {
            /**
             * Specify the image path, crop dimension and scale
             */
            intent.setData(mImageCaptureUri);

            intent.putExtra("aspectX", 50);
            intent.putExtra("aspectY", 50);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", true);
            /**
             * There is posibility when more than one image cropper app exist,
             * so we have to check for it first. If there is only one app, open
             * then app.
             */

            Intent i = new Intent(intent);
            ResolveInfo res = list.get(0);

            i.setComponent(new ComponentName(res.activityInfo.packageName,
                    res.activityInfo.name));

            startActivityForResult(i, CROP_FROM_CAMERA);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageRegLogo:
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
                break;
            case R.id.buttonReg:
                insertTableSQL = "INSERT INTO USERS"
                        + "(LOGIN, PASSWORD, EMAIL, PURCHASE_HISTORY, GOODS_RECOMMENDATIONS) " + "VALUES"
                        + "(loui,'12345','lou@gmail,com', " + 1 + 1 + ")";
                try {
                    Defaults.createDbUserTable(insertTableSQL);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
