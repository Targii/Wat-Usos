package com.wat.pum.usosmobile;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class GradesFragment extends Fragment {

    public static String SEMESTER = "2014/15Z"; // kod wybrnego semestru

    public GradesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("jestem w onCreate", "jestem w onCreate");

        try {

            //Dane otrzymane z serwera
            //Data received from server
            JSONObject serverResponseGrades = new JSONObject("{\"course_editions\": {\"2014/15Z\": [{\"course_id\": \"WELECCSI-TiUM\", \"course_units_ids\": [\"27049\", \"27050\", \"27051\"], \"grades\": {\"course_units_grades\": {\"27049\": {\"1\": {\"value_symbol\": \"3,5\"}}, \"27050\": {\"1\": {\"value_symbol\": \"4\"}}, \"27051\": {\"1\": {\"value_symbol\": \"ZAL\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Multimedia techniques and devices\", \"pl\": \"Techniki i urz\\u0105dzenia multimedialne\"}}, {\"course_id\": \"WELECCSI-SW1\", \"course_units_ids\": [\"27047\", \"27048\"], \"grades\": {\"course_units_grades\": {\"27048\": {\"1\": {\"value_symbol\": \"3,5\"}}, \"27047\": {\"1\": {\"value_symbol\": \"3,5\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Systemy wbudowane 1\"}}, {\"course_id\": \"WELECCSI-ST\", \"course_units_ids\": [\"27044\", \"27045\"], \"grades\": {\"course_units_grades\": {\"27044\": {\"1\": {\"value_symbol\": \"2\"}, \"2\": {\"value_symbol\": \"2\"}}, \"27045\": {\"1\": {\"value_symbol\": \"2\"}, \"2\": {\"value_symbol\": \"NB\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Systemy teletransmisyjne\"}}, {\"course_id\": \"WELECCSI-TEiO\", \"course_units_ids\": [\"27041\", \"27042\", \"27043\"], \"grades\": {\"course_units_grades\": {\"27041\": {\"1\": {\"value_symbol\": \"NB\"}, \"2\": {\"value_symbol\": \"NB\"}, \"3\": {\"value_symbol\": \"NB\"}}, \"27042\": {\"1\": {\"value_symbol\": \"ZAL\"}}, \"27043\": {\"1\": {\"value_symbol\": \"NB\"}, \"2\": {\"value_symbol\": \"NB\"}, \"3\": {\"value_symbol\": \"NB\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Technika emisji i odbioru\"}}, {\"course_id\": \"WELECCSI-TUP\", \"course_units_ids\": [\"27039\", \"27040\"], \"grades\": {\"course_units_grades\": {\"27040\": {\"1\": {\"value_symbol\": \"4\"}}, \"27039\": {\"1\": {\"value_symbol\": \"3\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Technika uk\\u0142ad\\u00f3w programowalnych\"}}, {\"course_id\": \"WELECCSI-AS\", \"course_units_ids\": [\"27032\", \"27033\", \"27034\"], \"grades\": {\"course_units_grades\": {\"27032\": {\"1\": {\"value_symbol\": \"3\"}}, \"27033\": {\"1\": {\"value_symbol\": \"3,5\"}}, \"27034\": {\"1\": {\"value_symbol\": \"ZAL\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Signal analysis\", \"pl\": \"Analiza sygna\\u0142\\u00f3w\"}}, {\"course_id\": \"WELECCSI-MiD2\", \"course_units_ids\": [\"27029\", \"27030\", \"27031\"], \"grades\": {\"course_units_grades\": {\"27029\": {\"1\": {\"value_symbol\": \"NB\"}, \"2\": {\"value_symbol\": \"3\"}}, \"27030\": {\"1\": {\"value_symbol\": \"ZAL\"}}, \"27031\": {\"1\": {\"value_symbol\": \"NZAL\"}, \"2\": {\"value_symbol\": \"ZAL\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Modulacja i detekcja 2\"}}, {\"course_id\": \"WELECCSI-TB\", \"course_units_ids\": [\"27026\", \"27027\", \"27028\"], \"grades\": {\"course_units_grades\": {\"27026\": {\"1\": {\"value_symbol\": \"2\"}, \"2\": {\"value_symbol\": \"2\"}, \"3\": {\"value_symbol\": \"3\"}}, \"27027\": {\"1\": {\"value_symbol\": \"ZAL\"}}, \"27028\": {\"1\": {\"value_symbol\": \"ZAL\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Techniki bezprzewodowe\"}}, {\"course_id\": \"WELEXCSI-Psy\", \"course_units_ids\": [\"27024\", \"27025\"], \"grades\": {\"course_units_grades\": {\"27024\": {\"1\": {\"value_symbol\": \"4\"}}, \"27025\": {\"1\": {\"value_symbol\": \"4\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Psychology\", \"pl\": \"Psychologia\"}}, {\"course_id\": \"WELEXCSI-PTel\", \"course_units_ids\": [\"27004\", \"27005\", \"27006\"], \"grades\": {\"course_units_grades\": {\"27004\": {\"1\": {\"value_symbol\": \"3,5\"}}, \"27005\": {\"1\": {\"value_symbol\": \"ZAL\"}}, \"27006\": {\"1\": {\"value_symbol\": \"4\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Remote sensing principles\", \"pl\": \"Podstawy teletedekcji\"}}, {\"course_id\": \"WELEXCSI-SPd\", \"course_units_ids\": [\"27003\"], \"grades\": {\"course_units_grades\": {\"27003\": {\"1\": {\"value_symbol\": \"ZAL\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Diplomas seminar\", \"pl\": \"Seminaria przeddyplomowe\"}}, {\"course_id\": \"WELEXCSI-EiOP\", \"course_units_ids\": [\"27000\", \"27001\", \"27002\"], \"grades\": {\"course_units_grades\": {\"27000\": {\"1\": {\"value_symbol\": \"3\"}}, \"27001\": {\"1\": {\"value_symbol\": \"ZAL\"}}, \"27002\": {\"1\": {\"value_symbol\": \"ZAL\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Ergonomics and labor protection\", \"pl\": \"Ergonomia i ochrona pracy\"}}, {\"course_id\": \"WELEXCSI-Tb.w.cz.\", \"course_units_ids\": [\"27637\", \"27638\", \"27639\"], \"grades\": {\"course_units_grades\": {\"27637\": {\"1\": {\"value_symbol\": \"NB\"}, \"2\": {\"value_symbol\": \"3,5\"}}, \"27638\": {\"1\": {\"value_symbol\": \"NB\"}, \"2\": {\"value_symbol\": \"3\"}}, \"27639\": {\"1\": {\"value_symbol\": \"3\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Very high frequency techniques\", \"pl\": \"Technika b.w.cz.\"}}, {\"course_id\": \"WELEXCSI-UA1\", \"course_units_ids\": [\"27634\", \"27635\", \"27636\"], \"grades\": {\"course_units_grades\": {\"27634\": {\"1\": {\"value_symbol\": \"3\"}}, \"27635\": {\"1\": {\"value_symbol\": \"ZAL\"}}, \"27636\": {\"1\": {\"value_symbol\": \"4\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Analog devices 1\", \"pl\": \"Uk\\u0142ady analogowe 1\"}}, {\"course_id\": \"WELXXCSI-JA5\", \"course_units_ids\": [\"30221\"], \"grades\": {\"course_units_grades\": {\"30221\": {\"1\": {\"value_symbol\": \"4,5\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"English V\", \"pl\": \"J\\u0119zyk angielski - sem. 5\"}}], \"2013/14L\": [{\"course_id\": \"WELECCSI-MiD1\", \"course_units_ids\": [\"24240\", \"24241\", \"24242\"], \"grades\": {\"course_units_grades\": {\"24240\": {\"1\": {\"value_symbol\": \"2\"}, \"2\": {\"value_symbol\": \"2\"}, \"3\": {\"value_symbol\": \"3\"}}, \"24241\": {\"1\": {\"value_symbol\": \"NZAL\"}, \"2\": {\"value_symbol\": \"ZAL\"}}, \"24242\": {\"1\": {\"value_symbol\": \"NZAL\"}, \"2\": {\"value_symbol\": \"NZAL\"}, \"3\": {\"value_symbol\": \"ZAL\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Modulacja i detekcja 1\"}}, {\"course_id\": \"WELECCSI-SiK\", \"course_units_ids\": [\"24224\", \"24225\", \"24226\", \"24227\"], \"grades\": {\"course_units_grades\": {\"24224\": {\"1\": {\"value_symbol\": \"2\"}, \"2\": {\"value_symbol\": \"2\"}, \"3\": {\"value_symbol\": \"3\"}}, \"24225\": {\"1\": {\"value_symbol\": \"ZAL\"}}, \"24226\": {\"1\": {\"value_symbol\": \"3,5\"}}, \"24227\": {\"1\": {\"value_symbol\": \"ZAL\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Sygna\\u0142y i kodowanie\"}}, {\"course_id\": \"WELEXCSI-PO\", \"course_units_ids\": [\"23558\"], \"grades\": {\"course_units_grades\": {\"23558\": {\"1\": {\"value_symbol\": \"NB\"}, \"2\": {\"value_symbol\": \"NB\"}, \"3\": {\"value_symbol\": \"NB\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"General technical pratice\", \"pl\": \"Praktyka og\\u00f3lnotechniczna\"}}, {\"course_id\": \"WELEXCSI-AiPF1\", \"course_units_ids\": [\"23555\", \"23556\", \"23557\"], \"grades\": {\"course_units_grades\": {\"23555\": {\"1\": {\"value_symbol\": \"2\"}, \"2\": {\"value_symbol\": \"3\"}}, \"23556\": {\"1\": {\"value_symbol\": \"ZAL\"}}, \"23557\": {\"1\": {\"value_symbol\": \"4\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Antennas and waves propagation 1\", \"pl\": \"Anteny i propagacja fal 1\"}}, {\"course_id\": \"WELEXCSI-TiUD\", \"course_units_ids\": [\"23552\", \"23553\", \"23554\"], \"grades\": {\"course_units_grades\": {\"23552\": {\"1\": {\"value_symbol\": \"3\"}}, \"23553\": {\"1\": {\"value_symbol\": \"3\"}}, \"23554\": {\"1\": {\"value_symbol\": \"ZAL\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Access technologies ad devices\", \"pl\": \"Techniki i urzadzenia dost\\u0119powe\"}}, {\"course_id\": \"WELEXCSI-KUE\", \"course_units_ids\": [\"23550\", \"23551\"], \"grades\": {\"course_units_grades\": {\"23550\": {\"1\": {\"value_symbol\": \"4\"}}, \"23551\": {\"1\": {\"value_symbol\": \"3,5\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Design of electronics devices\", \"pl\": \"Konstrukcja urz\\u0105dze\\u0144 elektronicznych\"}}, {\"course_id\": \"WELEXCSI-UC2\", \"course_units_ids\": [\"23548\", \"23549\"], \"grades\": {\"course_units_grades\": {\"23548\": {\"1\": {\"value_symbol\": \"3\"}}, \"23549\": {\"1\": {\"value_symbol\": \"3\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Uk\\u0142ady cyfrowe 2\"}}, {\"course_id\": \"WELEXCSI-AKiSO\", \"course_units_ids\": [\"23546\", \"23547\"], \"grades\": {\"course_units_grades\": {\"23546\": {\"1\": {\"value_symbol\": \"3\"}}, \"23547\": {\"1\": {\"value_symbol\": \"3\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Computer architecture and operating systems\", \"pl\": \"Architektura komputer\\u00f3w i systemy operacyjne\"}}, {\"course_id\": \"WELEXCSI-UA2\", \"course_units_ids\": [\"23544\", \"23545\"], \"grades\": {\"course_units_grades\": {\"23544\": {\"1\": {\"value_symbol\": \"2\"}, \"2\": {\"value_symbol\": \"2\"}, \"3\": {\"value_symbol\": \"3\"}}, \"23545\": {\"1\": {\"value_symbol\": \"2\"}, \"2\": {\"value_symbol\": \"NB\"}, \"3\": {\"value_symbol\": \"3\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Analog devices 2\", \"pl\": \"Uk\\u0142ady analogowe 2\"}}, {\"course_id\": \"WELEXCSI-WZP\", \"course_units_ids\": [\"23542\", \"23543\"], \"grades\": {\"course_units_grades\": {\"23542\": {\"1\": {\"value_symbol\": \"4,5\"}}, \"23543\": {\"1\": {\"value_symbol\": \"ZAL\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Wybrane zagadnienia prawa\"}}, {\"course_id\": \"WELEXCSI-LSK\", \"course_units_ids\": [\"17517\", \"17518\", \"17519\"], \"grades\": {\"course_units_grades\": {\"17517\": {\"1\": {\"value_symbol\": \"2\"}, \"2\": {\"value_symbol\": \"NB\"}}, \"17518\": {\"1\": {\"value_symbol\": \"NZAL\"}, \"2\": {\"value_symbol\": \"NZAL\"}, \"3\": {\"value_symbol\": \"ZAL\"}}, \"17519\": {\"1\": {\"value_symbol\": \"NZAL\"}, \"2\": {\"value_symbol\": \"NB\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Local area networks\", \"pl\": \"Lokalne sieci komputerowe\"}}, {\"course_id\": \"WELXXCSI-JA4\", \"course_units_ids\": [\"23794\"], \"grades\": {\"course_units_grades\": {\"23794\": {\"1\": {\"value_symbol\": \"3\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"English IV\", \"pl\": \"J\\u0119zyk angielski - sem. 4\"}}, {\"course_id\": \"WELEXCSI-OiS2\", \"course_units_ids\": [\"15145\", \"15144\", \"15146\"], \"grades\": {\"course_units_grades\": {\"15144\": {\"1\": {\"value_symbol\": \"NB\"}, \"2\": {\"value_symbol\": \"NB\"}, \"3\": {\"value_symbol\": \"3\"}}, \"15145\": {\"1\": {\"value_symbol\": \"2\"}, \"2\": {\"value_symbol\": \"NB\"}, \"3\": {\"value_symbol\": \"3\"}}, \"15146\": {\"1\": {\"value_symbol\": \"3,5\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"The electric circuits 2\", \"pl\": \"Obwody i sygna\\u0142y 2\"}}, {\"course_id\": \"WELEXCSI-AM2\", \"course_units_ids\": [\"15133\", \"15134\"], \"grades\": {\"course_units_grades\": {\"15133\": {\"1\": {\"value_symbol\": \"NB\"}, \"2\": {\"value_symbol\": \"3\"}}, \"15134\": {\"1\": {\"value_symbol\": \"3\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Mathematical analysis 2\", \"pl\": \"Analiza matematyczna 2\"}}, {\"course_id\": \"WELXXCSI-WF4\", \"course_units_ids\": [\"23479\"], \"grades\": {\"course_units_grades\": {\"23479\": {\"1\": {\"value_symbol\": \"3,5\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Physical Education\", \"pl\": \"Wychowanie fizyczne IV\"}}], \"2014/15L\": [{\"course_id\": \"WELECCSI-ASK-PW\", \"course_units_ids\": [\"34664\", \"34665\", \"34666\"], \"grades\": {\"course_units_grades\": {\"34664\": {}, \"34665\": {}, \"34666\": {}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Administrowanie sieciami komputerowymi-PW\"}}, {\"course_id\": \"WELECCSI-PwSU/L\", \"course_units_ids\": [\"34661\", \"34662\", \"34663\"], \"grades\": {\"course_units_grades\": {\"34661\": {}, \"34662\": {}, \"34663\": {}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Programowanie w systemie Unix/Linux-PW\"}}, {\"course_id\": \"WELECCSI-DiUST-PW\", \"course_units_ids\": [\"34658\", \"34659\", \"34660\"], \"grades\": {\"course_units_grades\": {\"34658\": {}, \"34659\": {}, \"34660\": {}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Diagnozowanie i utrzymanie sieci telekomunikacyjnych-PW\"}}, {\"course_id\": \"WELECCSI-RSP-PW\", \"course_units_ids\": [\"34656\", \"34657\"], \"grades\": {\"course_units_grades\": {\"34656\": {}, \"34657\": {}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Rozproszone systemy pomiarowe-PW\"}}, {\"course_id\": \"WELECCSI-TO\", \"course_units_ids\": [\"34653\", \"34654\", \"34655\"], \"grades\": {\"course_units_grades\": {\"34653\": {}, \"34654\": {}, \"34655\": {}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Telekomunikacja optyczna-PW\"}}, {\"course_id\": \"WELECCSI-BST-PW\", \"course_units_ids\": [\"34650\", \"34651\", \"34652\"], \"grades\": {\"course_units_grades\": {\"34650\": {}, \"34651\": {}, \"34652\": {}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Bezprzewodowe sieci teleinformatyczne -PW\"}}, {\"course_id\": \"WELECCSI-PAI-PW\", \"course_units_ids\": [\"34648\", \"34649\"], \"grades\": {\"course_units_grades\": {\"34648\": {\"1\": {\"value_symbol\": \"4\"}}, \"34649\": {\"1\": {\"value_symbol\": \"4\"}}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Programowanie aplikacji internetowych- PW\"}}, {\"course_id\": \"WELECCSI-TwST\", \"course_units_ids\": [\"34645\", \"34646\", \"34647\"], \"grades\": {\"course_units_grades\": {\"34645\": {}, \"34646\": {}, \"34647\": {}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Techniki w sieciach telekomunikacyjnych\"}}, {\"course_id\": \"WELECCSI-PUM\", \"course_units_ids\": [\"34643\", \"34644\"], \"grades\": {\"course_units_grades\": {\"34643\": {}, \"34644\": {}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Programowanie urz\\u0105dze\\u0144 mobilnych\"}}, {\"course_id\": \"WELECCSI-PBI\", \"course_units_ids\": [\"34639\", \"34640\", \"34641\", \"34642\"], \"grades\": {\"course_units_grades\": {\"34640\": {}, \"34641\": {}, \"34642\": {}, \"34639\": {}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Podstawy bezpiecze\\u0144stwa informacyjnego\"}}, {\"course_id\": \"WELECCSI-PDSP\", \"course_units_ids\": [\"34636\", \"34637\", \"34638\"], \"grades\": {\"course_units_grades\": {\"34636\": {}, \"34637\": {}, \"34638\": {}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Procesory DSP\"}}, {\"course_id\": \"WELEXCSI-PK\", \"course_units_ids\": [\"34621\"], \"grades\": {\"course_units_grades\": {\"34621\": {}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Praktyka kierunkowa\"}}, {\"course_id\": \"WELEXCSI-PES\", \"course_units_ids\": [\"34620\", \"34619\"], \"grades\": {\"course_units_grades\": {\"34619\": {}, \"34620\": {}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"Podstawy eksploatacji system\\u00f3w\"}}, {\"course_id\": \"WELXXCSI-JA6\", \"course_units_ids\": [\"36453\"], \"grades\": {\"course_units_grades\": {\"36453\": {}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"English VI\", \"pl\": \"J\\u0119zyk angielski - sem. 6\"}}, {\"course_id\": \"WELEXCSI-LSK\", \"course_units_ids\": [\"35894\", \"35895\", \"35896\"], \"grades\": {\"course_units_grades\": {\"35896\": {}, \"35894\": {}, \"35895\": {}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"Local area networks\", \"pl\": \"Lokalne sieci komputerowe\"}}, {\"course_id\": \"WELXXCSI-JA-E\", \"course_units_ids\": [\"36454\"], \"grades\": {\"course_units_grades\": {\"36454\": {}}, \"course_grades\": {}}, \"course_name\": {\"en\": \"\", \"pl\": \"J\\u0119zyk angielski - Egzamin (B2)\"}}]}}");


            try {
                JSONArray subjects = serverResponseGrades.getJSONObject("course_editions").getJSONArray(SEMESTER);
                Log.i("JSONArray created", "JSONArray subjects created");

                final int subjectsQuantity = subjects.length();
                //subjectsAndGrades zawira nazwy przedmiotów, unit_ids, oceny
                //subjectsAndGrades contains subjects names, usnit_ids, grades
                 GradesClass[] subjectsAndGrades = new GradesClass[subjectsQuantity];


                for (int i = 0; i < subjectsQuantity; i++){
                    //wypełnianie tabeli subjectsAndGrades
                    subjectsAndGrades[i] = getGradesAndSubjectNames(i , subjects);


                    //Wypisywanie ocen do loga
                    Log.i("grade",subjectsAndGrades[i].subjectName + " " + subjectsAndGrades[i].subjectUnit1Name + " " + subjectsAndGrades[i].subjectUnit1Grade + " " +subjectsAndGrades[i].subjectUnit2Name + " " + subjectsAndGrades[i].subjectUnit2Grade + " " +subjectsAndGrades[i].subjectUnit3Name + " " + subjectsAndGrades[i].subjectUnit3Grade + " " +subjectsAndGrades[i].subjectUnit4Name + " " + subjectsAndGrades[i].subjectUnit4Grade + " " +subjectsAndGrades[i].subjectUnit5Name + " " + subjectsAndGrades[i].subjectUnit5Grade);
                }
            } catch (JSONException exc){
                Log.i("creating JSONArray", "Nie udało się stworzyć JSONArray semesters");
            }

        } catch (JSONException exc){
            Log.i("JSONException", "catched JSONException przy tworzeniu objektu ");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grades, container, false);
    }


    //metoda umieszczająca oceny i dane o przedmiotach w objektach GradesClass
    private GradesClass getGradesAndSubjectNames(int i , JSONArray subjects) {

        try {
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
                        Log.i("oceny", "brak oceny nr " + (k + 1));
                    }

                }


                //Log.i("subName + unit grades", subjectName + " " + unitIds.getString(n) + " " + gradesForUnit);
            }


            //wybor konstruktora klasy GradesClass
            switch (unitIdsArrayLength) {
                case 1:
                    return new GradesClass(subjectName,unitIdsStringArray[0],  gradesForUnit[0] );

                case 2:
                    return new GradesClass(subjectName, unitIdsStringArray[0],  gradesForUnit[0], unitIdsStringArray[1],  gradesForUnit[1]);

                case 3:
                    return new GradesClass(subjectName, unitIdsStringArray[0],  gradesForUnit[0], unitIdsStringArray[1],  gradesForUnit[1], unitIdsStringArray[2],  gradesForUnit[2]);

                case 4:
                    return new GradesClass(subjectName, unitIdsStringArray[0],  gradesForUnit[0], unitIdsStringArray[1],  gradesForUnit[1], unitIdsStringArray[2],  gradesForUnit[2], unitIdsStringArray[3],  gradesForUnit[3]);

                default:
                    return new GradesClass(subjectName, unitIdsStringArray[0],  gradesForUnit[0], unitIdsStringArray[1],  gradesForUnit[1], unitIdsStringArray[2],  gradesForUnit[2], unitIdsStringArray[3],  gradesForUnit[3], unitIdsStringArray[4],  gradesForUnit[4]);


            }
        }catch (JSONException exc){
            Log.i("error in function", "error in function getGradesAndSubjectName");
        }
        return new GradesClass();
    }

}

