package com.wat.pum.usosmobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.rey.material.widget.Button;
import com.rey.material.widget.ProgressView;
import com.rey.material.widget.Spinner;


public class LoginActivity extends ActionBarActivity {

    private ProgressView pv_linear;
    private Spinner spn_choose_server;
    private Button btn_login;
    private WebView wv_login_page;

    protected Context mContext;

    //public final static String EXTRA_MESSAGE = "com.wat.pum.usosmobile.MESSAGE"; // nazwa zmiannej przekazywanej do kolejnego activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;

        // Ukrywanie ActionBara
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        pv_linear = (ProgressView) findViewById(R.id.progress_pv_linear);

        btn_login = (Button) findViewById(R.id.btn_login);

        // Pobieranie danych o serwerach w asynctasku
        new DownloadServerInfo().execute();

        // Ustawienie czujki dla spinnera
        addListenerOnSpinnerItemSelection();

    }


    private class DownloadServerInfo extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... arg0) {
            // zadanie do wykonania
            // tutaj nie możesz nic zmieniać w UI, tylko wykonywać obliczenia i zadania
            // TODO Pobranie informacji z mother server: http://apps.usos.edu.pl/ o dostępnych serwerach apisrv->instalations
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // działania przed wykonaniem zadań
            // ta metoda działa w UI, więc możesz zmieniać różne elementy
        }

        @Override
        protected void onProgressUpdate(Void... progress) {
            super.onProgressUpdate(progress);
            // aktualizacja stanu podczas działania, można zmianiać UI
        }

        @Override
        protected void onPostExecute(Integer result) {
            // działania po wykonaniu zdania w doInBackground
            // ta metoda działa w UI, więc możesz zmieniać różne elementy!
            super.onPostExecute(result);

            if (result == 1) {
                pv_linear.stop(); // zatrzymanie paska
                findViewById(R.id.ll_download_serverinfo).setVisibility(View.GONE); // ukrycie informcji o pobieraniu

                findViewById(R.id.ll_select_server).setVisibility(View.VISIBLE); // wyswietlenie wyboru serwera

                // ustawienie listy (spinner) // TODO
                String[] items = new String[5];
                items[0] = "";
                items[1] = "PWSZ Elblag";
                items[2] = "Wojskowa Akademia Techiczna";
                items[3] = "Uniwersytet im. Adama Mickiewicza w Poznaniu";
                items[4] = "Akademia Wychowania Fizycznego w Katowicach";

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), R.layout.row_spn, items);
                adapter.setDropDownViewResource(R.layout.row_spn_dropdown);
                spn_choose_server.setAdapter(adapter);

            } else {
                // TODO Dodać wyjątek w razie błędu
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.report_bug) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addListenerOnSpinnerItemSelection() {
        spn_choose_server = (Spinner) findViewById(R.id.spn_choose_server);
        spn_choose_server.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public class CustomOnItemSelectedListener extends Activity implements Spinner.OnItemSelectedListener {

        public void onItemSelected(Spinner parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
            if (!parent.getSelectedItem().toString().equals("")) {
                // TEST Usunąć po testach
                Toast.makeText(parent.getContext(),
                        "Wybrano: " + parent.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT).show();
                // TEST

                // Jeśli coś zostało wybrane to uruchamiam przycisk
                btn_login.setEnabled(true);
                btn_login.setClickable(true);
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }

    }

    // Akcja po kliknięciu przycisku
    public void view_login(View view) {
        wv_login_page = (WebView) findViewById(R.id.wv_login_page);
        wv_login_page.setVisibility(View.VISIBLE); // pokazanie WebView
        wv_login_page.bringToFront();   // przesunięcie na wierzch

        wv_login_page.setWebViewClient(new MyWebViewClient());

        WebSettings webSettings = wv_login_page.getSettings();
        webSettings.setJavaScriptEnabled(true); // włączenie JSa

        wv_login_page.loadUrl("http://bada-world.pl/darkside/usosapp/test.html");

    }

    private class MyWebViewClient extends WebViewClient {
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            // Czujka na oauth_verifier
            // https://usosapps.wat.edu.pl/apps/authorized?oauth_token=pqQY2qRsZRv2RSkLXFxZ&oauth_verifier=60726319
            Uri oaut_url = Uri.parse(url);
            String oauth_verifier = oaut_url.getQueryParameter("oauth_verifier");

            if (oauth_verifier != null && !oauth_verifier.equals("")) {
                // TEST Usunąć po testach
                Toast.makeText(view.getContext(),
                        "oauth_verifier: " + oauth_verifier,
                        Toast.LENGTH_SHORT).show();
                // TEST

                // Przejście do kolejnego activity
                Intent intent = new Intent(mContext, MainActivity.class);
                //intent.putExtra(EXTRA_MESSAGE, message); // przekazanie zmiennych np. tokenów
                startActivity(intent);

                // TODO Zniszczyć LoginActivity, żeby nie można było do niego wrócić przyciskiem wstecz

            }
        }
    }
}
