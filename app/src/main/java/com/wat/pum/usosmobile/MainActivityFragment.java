package com.wat.pum.usosmobile;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;


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


        try {
            JSONObject valuesUser= new JSONObject(MainActivity.valueUser);

            //DODAWANIE DANYCH UŻYTKOWNIKA

            // Dodawanie zdjecia
            ImageView iv_user_photo = (ImageView) view.findViewById(R.id.iv_user_photo);
            // Probowalem podawać wygenerowany adres z USOS Api Browser ale nie działa. Albo przez SSLa albo przez brak sesji z telefonu.
            Picasso.with(getActivity()).load(valuesUser.getJSONObject("photo_urls").getString("200x200"))
                    .transform(new CircleTransform())
                    .into(iv_user_photo);
            // TODO Jeżeli ktoś nie ma zdjecia lub ma zablokowane załadować domyślny. Trzeba wykrywać metodą user->has_photo
            //Ladowanie obrazka z drawable
            //int imageresource = getResources().getIdentifier("@drawable/profile", "drawable", getActivity().getPackageName());
            //iv_user_photo.setImageResource(imageresource);

            //wprowadzanie danych do dashboard
            //imie i nazwisko
            TextView tv_full_name = (TextView) view.findViewById(R.id.tv_full_name);
            try {
                tv_full_name.setText(valuesUser.getString("first_name") + " " + valuesUser.getString("last_name"));
            }catch (JSONException exc){
                tv_full_name.setText(R.string.tv_full_name_lack);
            }


            //status studenta
            TextView tv_status = (TextView) view.findViewById(R.id.tv_status);
            try {
                int a = valuesUser.getInt("student_status");
                switch (a) {
                    case 2:
                        tv_status.setText(R.string.tv_status_active);
                        break;
                    case 1:
                        tv_status.setText(R.string.tv_status_inactive);
                        break;
                    case 0:
                        tv_status.setText(R.string.tv_status_never_was);
                        break;
                    default:
                        tv_status.setText(R.string.tv_status_access_refuse);
                }
            }catch (JSONException exc){
                tv_status.setText(R.string.tv_status_access_refuse);
            }

            //numer albumu
            TextView tv_album_label = (TextView) view.findViewById(R.id.tv_album_label);
            tv_album_label.setText(R.string.tv_album_label);
            TextView tv_album = (TextView) view.findViewById(R.id.tv_album);
            try {
                tv_album.setText(valuesUser.getString("student_number"));
            }catch (JSONException exc){
                tv_album.setText(R.string.tv_album_lack);
            }


            //adres e-mail

            TextView tv_email = (TextView) view.findViewById(R.id.tv_email);
            try {
                tv_email.setText(valuesUser.getString("email"));
            }catch (JSONException exc){
                tv_email.setText(R.string.tv_email_lack);
            }

            //nr PESEL
            TextView tv_pesel = (TextView) view.findViewById(R.id.tv_pesel);
            try {
                tv_pesel.setText(valuesUser.getString("pesel"));
            }catch (JSONException exc) {
                tv_pesel.setText(R.string.tv_pesel_lack);
            }

        } catch (JSONException exc){
            Log.i("valuesUser ocject", "nie udało się stworzyć valuesUser JSONObject");
        }


    }

    public static MainActivityFragment newInstance(String title) {
        MainActivityFragment f = new MainActivityFragment();

        Bundle args = new Bundle();

        args.putString(KEY_TITLE, title);
        f.setArguments(args);

        return (f);
    }
}
