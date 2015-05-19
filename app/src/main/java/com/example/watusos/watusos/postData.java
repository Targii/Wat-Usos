package com.example.watusos.watusos;


import android.os.AsyncTask;
import android.webkit.WebView;


import java.net.URLEncoder;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;

/**
 * Created by Adam on 2015-05-10.
 * Jest to funkcja która powinna być odpowiedzialna za logowanie użytkowników do USOSA
 * Powinna to dobre słowo.
 */
public class postData extends AsyncTask<Void, Integer, String> {
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


       // new MainActivity().getUserPIN(consumer.getToken());
        //Looper.loop();



        return authUrl;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
}