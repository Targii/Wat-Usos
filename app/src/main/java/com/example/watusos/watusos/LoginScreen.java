package com.example.watusos.watusos;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;


import java.net.URLEncoder;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;

public class LoginScreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        new getToken().execute();





    }

    public void onButtonZalogujClick(View view){
        // TODO Tutaj brakuje kwesti logowania użytkownika...

        Intent intent = new Intent(this,ChooseActivity.class);
        startActivity(intent);
    }

/*
    public void getUserPIN(){
        //Kod odpowiedzialny za wyświetlanie przeglądarki i przekierowania na strone logowania watu
        // utworzenie intentu przejścia do strony watu
      //  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://logowanie.wat.edu.pl/cas/login?locale=pl&service=https%3A%2F%2Fusosapps.wat.edu.pl%2Fapps%2Flogin%3Fnext%3D%252Fapps%252Fauthorize%253Foauth_token%%2526interactivity%253Dminimal"));

        // utworzenie intentu "owijającego" nasz docelowy intent, który umożliwi wybranie aplikacji
      //  Intent chooser = Intent.createChooser(intent, "Wybierz program");

       // startActivity(chooser);



    }
*/

    public class getToken extends AsyncTask<Void, Integer, String> {
        @Override
        protected String doInBackground(Void... params) {
            OAuthConsumer consumer = new CommonsHttpOAuthConsumer("24cD4dfpbF5YeDQvMgMN",
                    "2t2qmArZ24mycqVgaw8k4JayFLHgFe5QaQ4CWnaK");

            String scope = "cards";
            //Looper.prepare();
            String authUrl = "";
            try {
                OAuthProvider provider = new CommonsHttpOAuthProvider("https://usosapps.wat.edu.pl/services/oauth/request_token?scope="
                        + URLEncoder.encode(scope, "utf-8"),
                        "https://usosapps.wat.edu.pl/services/oauth/access_token",
                        "https://usosapps.wat.edu.pl/apps/authorize");
                authUrl = provider.retrieveRequestToken(consumer, "oob");
                System.out.println("URL:" + authUrl);
            } catch (Exception e1) {
                e1.printStackTrace();
                // System.out.println(e1);
            }
            System.out.println("Fetching request token...");

            //to Generuje tokeny

            System.out.println("Request token: " + consumer.getToken());
            System.out.println("Token secret: " + consumer.getTokenSecret());




            return authUrl;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl(s);
        }
    }


}
