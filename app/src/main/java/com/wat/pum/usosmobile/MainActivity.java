package com.wat.pum.usosmobile;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialdrawer.util.UIUtils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;


public class MainActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 1;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create a few sample profile // TODO Wkleić dane otrzymane z USOSa i używając instrukcji z wiki MeterialDrawer zaimportować zdjecie przy użyciu picasso
        final IProfile profile = new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile));

        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBarSize().paddingDp(5).colorRes(R.color.material_drawer_dark_primary_text)).withIdentifier(PROFILE_SETTING),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withSavedInstance(savedInstanceState)
                .build();
        //get Usos response value
        System.out.print("TO z MainActivity:");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("Response_Text");
            System.out.println("TO z MainActivity: " + value);
              try {
                JSONObject serverResponseGrades = new JSONObject(value);

                  //Tu wklejam
                  try {
                      //tablica Id semestrów
                      JSONArray semestryArray = serverResponseGrades.getJSONArray("terms");

                      //pętla do sprawdzania wszystkich semestrów
                      for (int x = 0; x  < semestryArray.length(); x++) {
                          JSONArray subjects = serverResponseGrades.getJSONObject("course_editions").getJSONArray(semestryArray.getJSONObject(x).getString("id"));

                          //wyswietla Id semestru w logu
                          Log.i("grade Id semestru", semestryArray.getJSONObject(x).getString("id"));

                          final int subjectsQuantity = subjects.length();
                          //subjectsAndGrades zawira nazwy przedmiotów, unit_ids, oceny
                          //subjectsAndGrades contains subjects names, usnit_ids, grades
                          GradesClass[][] subjectsAndGrades = new GradesClass[semestryArray.length()][subjectsQuantity];


                          for (int i = 0; i < subjectsQuantity; i++) {
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
                          }
                      }
                  } catch (JSONException exc){
                      Log.i("creating JSONArray", "Nie udało się stworzyć JSONArray semesters");
                  }







              }catch(Exception e){
                System.out.println(e);
            }
        }


        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_dashboard).withIcon(GoogleMaterial.Icon.gmd_person).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_grades).withIcon(GoogleMaterial.Icon.gmd_school).withIdentifier(2),
                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_question),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(FontAwesome.Icon.faw_bullhorn)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (drawerItem != null && drawerItem instanceof Nameable) {
                            getSupportActionBar().setTitle(((Nameable) drawerItem).getNameRes());
                            //ignore the DemoFragment and it's layout it's just to showcase the handle with an keyboard
                            if (drawerItem.getIdentifier() == 1) {
                                Fragment f = MainActivityFragment.newInstance(getResources().getString(((Nameable) drawerItem).getNameRes()));
                                getSupportFragmentManager().beginTransaction().replace(R.id.fr_main, f).commit();
                            } else if (drawerItem.getIdentifier() == 2) {
                                Fragment f = GradesFragment.newInstance(getResources().getString(((Nameable) drawerItem).getNameRes()));
                                getSupportFragmentManager().beginTransaction().replace(R.id.fr_main, f).commit();
                            }
                        }

                        return false;
                    }


                    //if (drawerItem != null && drawerItem.getIdentifier() == 1) {



                        // To poniżej zmieniało ActionBara dodając ikonkę kosza, ale nie działa to poprawnie bo Drawer chowa się pod ActionBar.
                        //startSupportActionMode(new ActionBarCallBack());
                        //findViewById(R.id.action_mode_bar).setBackgroundColor(UIUtils.getThemeColorFromAttrOrRes(MainActivity.this, R.attr.colorPrimary, R.color.material_drawer_primary));
                    //}


                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(false)
                .build();

        // set the selection to the item with the identifier 5
        result.setSelectionByIdentifier(1, false);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    class ActionBarCallBack implements ActionMode.Callback {

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(UIUtils.getThemeColorFromAttrOrRes(MainActivity.this, R.attr.colorPrimaryDark, R.color.material_drawer_primary_dark));
            }

            mode.getMenuInflater().inflate(R.menu.cab, menu);
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }
    }



}