package com.example.watusos.watusos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wat.pum.usosmobile.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    /**
     * Po utworzeniu widoku
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView iv_user_photo = (ImageView) view.findViewById(R.id.iv_user_photo);

        // Probowalem podawa wygenerowany adres z USOS Api Browser ale nie dziaa. Albo przez SSLa albo przez brak sesji z telefonu.
        Picasso.with(getActivity()).load("http://www.whatsupwhatson.com/wp-content/uploads/2014/03/how_could_this_face_lie_to_you.jpg")
            .transform(new CircleTransform())
            .into(iv_user_photo);
        // TODO Je�li kto� nie ma zdjecia lub ma zablokowane za�adowa� domy�lny. Trzeba wykrywa� metod� user->has_photo
        //Ladowanie obrazka z drawable
        //int imageresource = getResources().getIdentifier("@drawable/profile", "drawable", getActivity().getPackageName());
        //iv_user_photo.setImageResource(imageresource);



    }
}
