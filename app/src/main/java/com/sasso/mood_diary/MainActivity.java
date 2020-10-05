package com.sasso.mood_diary;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sasso.mood_diary.ui.dashboard.DashboardFragment;
import com.sasso.mood_diary.ui.home.HomeFragment;
import com.sasso.mood_diary.ui.notifications.NotificationsFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //When app start it shows the home page
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    new HomeFragment()).commit();
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.navigation_dashboard:
                            selectedFragment = new DashboardFragment();
                            break;
                        case R.id.navigation_notifications:
                            selectedFragment = new NotificationsFragment();
                            break;
                    }

                    // Prende il fragment attualmente visualizzato
                    Fragment temp = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

                    // Se il fragment da cambiare e' uguale a quello visibile returna
                    try {
                        if (temp.getClass().toString().equals(selectedFragment.getClass().toString())) return true;
                    } catch (Exception ignored){}

                    // TODO: Aggiungere parte codice in caso di errore
                    //  IDEA: Mostrare messaggio di errore con richiesta di ripetere l'azione
                    // Cambia il fragment attuale con quello selezionato
                    try {
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                                selectedFragment).commit();
                    } catch (Exception ignored){}

                    return true;
                }
            };

}