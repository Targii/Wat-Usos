package com.wat.pum.usosmobile;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String KEY_TITLE = "title";

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

        // Probowalem podawać wygenerowany adres z USOS Api Browser ale nie działa. Albo przez SSLa albo przez brak sesji z telefonu.
        Picasso.with(getActivity()).load("http://www.whatsupwhatson.com/wp-content/uploads/2014/03/how_could_this_face_lie_to_you.jpg")
            .transform(new CircleTransform())
            .into(iv_user_photo);
        // TODO Jeżeli ktoś nie ma zdjecia lub ma zablokowane załadować domyślny. Trzeba wykrywać metodą user->has_photo
        //Ladowanie obrazka z drawable
        //int imageresource = getResources().getIdentifier("@drawable/profile", "drawable", getActivity().getPackageName());
        //iv_user_photo.setImageResource(imageresource);



    }

    public static MainActivityFragment newInstance(String title) {
        MainActivityFragment f = new MainActivityFragment();

        Bundle args = new Bundle();

        args.putString(KEY_TITLE, title);
        f.setArguments(args);

        return (f);
    }
}
