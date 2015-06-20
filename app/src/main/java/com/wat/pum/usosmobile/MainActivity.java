package com.wat.pum.usosmobile;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import android.widget.ImageView;

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
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.mikepenz.materialdrawer.util.UIUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import static android.app.PendingIntent.getActivity;


public class MainActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 1;

    public static String valueGrades;
    public static String valueUser;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get Usos response value
        System.out.print("TO z MainActivity:");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            valueGrades = extras.getString("Response_Grades");
            valueUser = extras.getString("Response_User");
            System.out.println("TO z MainActivity: " + valueGrades);

        }

        //pobieranie danych uzytkownika
        JSONObject valuesUser = createJSONObject(valueUser);
        String fullName = getFullName(valuesUser);
        String email = getEmail(valuesUser);

        //pobiranie zdjecia uzytkownika

        DrawerImageLoader.init(new DrawerImageLoader.IDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }

            @Override
            public Drawable placeholder(Context ctx) {
                return null;
            }
        });


        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Dashboard");


        // Create a few sample profile // TODO Wkleić dane otrzymane z USOSa i używając instrukcji z wiki MeterialDrawer zaimportować zdjecie przy użyciu picasso
        final IProfile profile = new ProfileDrawerItem().withName(fullName).withEmail(email).withIcon(getResources().getDrawable(R.drawable.profile));

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
        result.setSelectionByIdentifier(1, true);

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

    public JSONObject createJSONObject(String valueUser) {
        try {
            JSONObject object = new JSONObject(valueUser);
            return object;
        } catch (JSONException exc) {
            Log.i("JSONException", "JSONException in createJSONObject method");
        }
        return null;
    }

    public String getFullName(JSONObject valuesUser) {
        try {
            String fullName = valuesUser.getString("first_name") + " " + valuesUser.getString("last_name");
            return fullName;
        } catch (JSONException exc) {
            Log.i("JSONException", "JSONException in getFullName method");
        }
        return null;
    }

    public String getEmail(JSONObject valuesUser) {
        try {
            String email = valuesUser.getString("email");
            return email;
        } catch (JSONException exc) {
            Log.i("JSONException", "JSONException in getEmail method");
        }
        return null;
    }
}