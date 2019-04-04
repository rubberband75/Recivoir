package com.rubberband75.recivoir;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {


    private static final String TAG = "[Recivoir]MainActivity:";
    private static final int RC_SIGN_IN = 9001;

    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchNewRecipeActivity();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);


        if(account != null) {
            Log.d(TAG, "Signed In");
            updateUI(account);
        } else {
            Log.d(TAG, "Forcing Login");
            forceSignIn();
        }

        FirebaseApp.initializeApp(this);
    }



    public void forceSignIn(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, RC_SIGN_IN);
    }

    public void signOut(){
        Log.d(TAG, "Signing Out");
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                Log.d(TAG, "Signed Out");
                forceSignIn();
            }
        });
    }



    private void updateUI(GoogleSignInAccount account) {
        Log.d(TAG, "Update UI for account: " + account.getEmail());

        ((TextView) findViewById(R.id.greeting_text)).setText("Welcome " + account.getGivenName());

        NavigationView navDrawer= (NavigationView) findViewById(R.id.nav_view);
        View navHeader = navDrawer.getHeaderView(0);

        ((TextView) navHeader.findViewById(R.id.drawer_header_name)).setText(account.getDisplayName());
        ((TextView) navHeader.findViewById(R.id.drawer_header_email)).setText(account.getEmail());
    }

    private void launchNewRecipeActivity() {
        startActivity(new Intent(this, AddRecipeActivity.class));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "TODO: Make Setting Activity", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_new_recipes) {
            startActivity(new Intent(this, AddRecipeActivity.class));
        } else if (id == R.id.nav_my_recipes) {
            Toast.makeText(this, "TODO: Make My_Recipes Activity", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_my_friends) {
            Toast.makeText(this, "TODO: Make My_Friends Activity", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_find_recipes) {
            Toast.makeText(this, "TODO: Make Find_Recipes Activity", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_settings) {
            Toast.makeText(this, "TODO: Make Setting Activity", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_sign_out) {
            signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "onActivityResult: Returning from sign in attempt");
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
            if(account != null) {
                updateUI(account);
            } else {
                forceSignIn();
            }

        }
    }
}

