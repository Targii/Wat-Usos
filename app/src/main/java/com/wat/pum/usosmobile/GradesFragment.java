package com.wat.pum.usosmobile;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class GradesFragment extends Fragment {

    RecyclerView mRecyclerView;

    private static final String KEY_TITLE = "title";

    /**
     * Przy renderowaniu fragmentu wykorzystaj opis z pliku XML
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grades, container, false);
    }

    /**
     * Po utworzeniu widoku ustaw layout RecyclerView oraz dodaj adapter
     * Adapter - klasa uzupełniająca widok danymi
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<String> myDataset = new ArrayList<String>();
        myDataset.add("Pierwszy wiersz");
        myDataset.add("Drugi wiersz");
        myDataset.add("Trzeci wiersz");
        myDataset.add("Czwarty wiersz");
        myDataset.add("Piąty wiersz");
        myDataset.add("Szósty wiersz");
        myDataset.add("Siódmy wiersz");
        myDataset.add("Ósmy wiersz");
        myDataset.add("Pierwszy wiersz");
        myDataset.add("Drugi wiersz");
        myDataset.add("Trzeci wiersz");
        myDataset.add("Czwarty wiersz");
        myDataset.add("Piąty wiersz");
        myDataset.add("Szósty wiersz");
        myDataset.add("Siódmy wiersz");
        myDataset.add("Ósmy wiersz");

        mRecyclerView.setAdapter(new ListAdapter(getActivity().getApplicationContext(), myDataset));

    }

    public static GradesFragment newInstance(String title) {
        GradesFragment f = new GradesFragment();

        Bundle args = new Bundle();

        args.putString(KEY_TITLE, title);
        f.setArguments(args);

        return (f);
    }
}