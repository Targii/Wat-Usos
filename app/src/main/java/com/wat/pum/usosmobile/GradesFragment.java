package com.wat.pum.usosmobile;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        ArrayList<String[]> myDataset = new ArrayList<String[]>();

        //String [] temp_arr= new String[3];



        try {
            JSONObject serverResponseGrades = new JSONObject(MainActivity.valueGrades);

            //Tu wklejam

                //tablica Id semestrów
                JSONArray semestryArray = serverResponseGrades.getJSONArray("terms");

                //pętla do sprawdzania wszystkich semestrów
                for (int x = 0; x  < semestryArray.length(); x++) {

                   // temp_arr[0]="0";
                    //temp_arr[1]= semestryArray.getJSONObject(x).getString("id");
                    myDataset.add(new String[]{"0", semestryArray.getJSONObject(x).getString("id")});

                    JSONArray subjects = serverResponseGrades.getJSONObject("course_editions").getJSONArray(semestryArray.getJSONObject(x).getString("id"));

                    //wyswietla Id semestru w logu
                    //Log.i("grade Id semestru", semestryArray.getJSONObject(x).getString("id"));

                    final int subjectsQuantity = subjects.length();
                    //subjectsAndGrades zawira nazwy przedmiotów, unit_ids, oceny
                    //subjectsAndGrades contains subjects names, usnit_ids, grades
                    GradesClass[][] subjectsAndGrades = new GradesClass[semestryArray.length()][subjectsQuantity];




                    for (int i = 0; i < subjectsQuantity; i++) {

                        //temp_arr[0]="1";
                        //temp_arr[1]= subjects.getJSONObject(i).getJSONObject("course_name").getString("pl");
                        myDataset.add(new String[]{"1", subjects.getJSONObject(i).getJSONObject("course_name").getString("pl")});
                        //wypełnianie tabeli subjectsAndGrades
                        //subjectsAndGrades[i] = getGradesAndSubjectNames(i, subjects);


                        //wypełnianie tabeli subjectsAndGrades
                        //String Subject name
                        String subjectName = subjects.getJSONObject(i).getJSONObject("course_name").getString("pl");


                        //creating array of unit_ids
                        JSONArray unitIds = subjects.getJSONObject(i).getJSONArray("course_units_ids");


                        final int unitIdsArrayLength = unitIds.length();


                        String[] unitIdsStringArray = new String[unitIdsArrayLength];
                        String[] gradesForUnit = new String[unitIdsArrayLength];


                        //loop for check all unit_ids
                        for (int n = 0; n < unitIdsArrayLength; n++) {
                            unitIdsStringArray[n] = unitIds.getString(n);
                            gradesForUnit[n] = "";

                            //loop for get all grades
                            String gradeNumber[] = {"1", "2", "3"};
                            for (int k = 0; k < gradeNumber.length; k++) {
                                try {
                                    String grade = subjects.getJSONObject(i).getJSONObject("grades").getJSONObject("course_units_grades").getJSONObject(unitIds.getString(n)).getJSONObject(gradeNumber[k]).getString("value_symbol");
                                    gradesForUnit[n] = gradesForUnit[n] + grade + " ";
                                } catch (JSONException exc) {
                                    Log.i("brak oceny", "brak oceny nr " + (k + 1));
                                }

                            }
                            myDataset.add(new String[] {"2", unitIdsStringArray[n], gradesForUnit[n]});
                            Log.i("myDataSet", "" + myDataset.size());
                        }








                       //wybor konstruktora klasy GradesClass
                        switch (unitIdsArrayLength) {
                            case 1:
                                subjectsAndGrades[x][i] = new GradesClass(subjectName,unitIdsStringArray[0],  gradesForUnit[0] );
                                break;

                            case 2:
                                subjectsAndGrades[x][i] =  new GradesClass(subjectName, unitIdsStringArray[0],  gradesForUnit[0], unitIdsStringArray[1],  gradesForUnit[1]);
                                break;

                            case 3:
                                subjectsAndGrades[x][i] =  new GradesClass(subjectName, unitIdsStringArray[0],  gradesForUnit[0], unitIdsStringArray[1],  gradesForUnit[1], unitIdsStringArray[2],  gradesForUnit[2]);
                                break;

                            case 4:
                                subjectsAndGrades[x][i] =  new GradesClass(subjectName, unitIdsStringArray[0],  gradesForUnit[0], unitIdsStringArray[1],  gradesForUnit[1], unitIdsStringArray[2],  gradesForUnit[2], unitIdsStringArray[3],  gradesForUnit[3]);
                                break;

                            default:
                                subjectsAndGrades[x][i] =  new GradesClass(subjectName, unitIdsStringArray[0],  gradesForUnit[0], unitIdsStringArray[1],  gradesForUnit[1], unitIdsStringArray[2],  gradesForUnit[2], unitIdsStringArray[3],  gradesForUnit[3], unitIdsStringArray[4],  gradesForUnit[4]);
                        }



                        //Wypisywanie ocen do loga
                        Log.i("grade", subjectsAndGrades[x][i].subjectName + " " + subjectsAndGrades[x][i].subjectUnit1Name + " " + subjectsAndGrades[x][i].subjectUnit1Grade + " " + subjectsAndGrades[x][i].subjectUnit2Name + " " + subjectsAndGrades[x][i].subjectUnit2Grade + " " + subjectsAndGrades[x][i].subjectUnit3Name + " " + subjectsAndGrades[x][i].subjectUnit3Grade + " " + subjectsAndGrades[x][i].subjectUnit4Name + " " + subjectsAndGrades[x][i].subjectUnit4Grade + " " + subjectsAndGrades[x][i].subjectUnit5Name + " " + subjectsAndGrades[x][i].subjectUnit5Grade);

                        //myDataset.add("Pierwszy wiersz");
                    }
                }


        }catch(Exception e) {
            System.out.println(e);
        }


        /*super.onViewCreated(view, savedInstanceState);

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
        myDataset.add("Ósmy wiersz");*/

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