package com.example.watusos.watusos;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LoginScreen extends Activity {
    SlidingPaneLayout mSlidingPanel;
    ListView mMenuList;
    TextView TitleText;

    String [] MenuTitles = new String[]{"First Item","Second Item","Third Item","Fourth Item"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        mSlidingPanel = (SlidingPaneLayout) findViewById(R.id.SlidingPanel);
        mMenuList = (ListView) findViewById(R.id.MenuList);


        TitleText = (TextView)findViewById(android.R.id.title);


        mMenuList.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,MenuTitles));

        mSlidingPanel.setPanelSlideListener(new SlidingPaneLayoutListener());
        mSlidingPanel.setParallaxDistance(200);




//        getActionBar().setDisplayShowHomeEnabled(true);
        //   getActionBar().setHomeButtonEnabled(true);

    }





    private class SlidingPaneLayoutListener implements SlidingPaneLayout.PanelSlideListener {

        @Override
        public void onPanelClosed(View arg0) {
            // TODO Auto-generated method stub
            // getActionBar().setTitle(getString(R.string.app_name));
            //  appImage.animate().rotation(0);
        }

        @Override
        public void onPanelOpened(View arg0) {
            // TODO Auto-generated method stub
            // getActionBar().setTitle("Menu Titles");
            // appImage.animate().rotation(90);
        }

        @Override
        public void onPanelSlide(View arg0, float arg1) {
            // TODO Auto-generated method stub

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case android.R.id.home:
                if(mSlidingPanel.isOpen()){

                    mSlidingPanel.closePane();
                    //   getActionBar().setTitle(getString(R.string.app_name));
                }
                else{

                    mSlidingPanel.openPane();
                    //     getActionBar().setTitle("Menu Titles");
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onButtonZalogujClick(View view){
        new postData().execute();
        System.out.println("To jest ten token?" );
    }

    /* As far not needed, looking for proper implementation with AsycTask
    public String getUserPIN(String userToken){
        //Kod odpowiedzialny za wyświetlanie przeglądarki i przekierowania na strone logowania watu
        // utworzenie intentu przejścia do strony watu
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://logowanie.wat.edu.pl/cas/login?locale=pl&service=https%3A%2F%2Fusosapps.wat.edu.pl%2Fapps%2Flogin%3Fnext%3D%252Fapps%252Fauthorize%253Foauth_token%"+ userToken +"%2526interactivity%253Dminimal"));

        // utworzenie intentu "owijającego" nasz docelowy intent, który umożliwi wybranie aplikacji
        Intent chooser = Intent.createChooser(intent, "Wybierz program");

        startActivity(chooser);
        EditText PinEdit = (EditText) findViewById(R.id.editText3);
        String PIN = "";
        PIN = PinEdit.getText().toString();






        return PIN;
    }*/



}
