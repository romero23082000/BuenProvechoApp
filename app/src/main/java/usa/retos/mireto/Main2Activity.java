package usa.retos.mireto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import java.util.Timer;
import java.util.TimerTask;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import usa.retos.mireto.databinding.ActivityMain2Binding;
/**
 *
 * Declaracion de la clase principal de la aplicacion
 * @author Andres Romero
 */
public class Main2Activity extends AppCompatActivity {
    /**
     *
     * Declaracion del atributo appBar
     * @author Andres Romero
     */
    private AppBarConfiguration mAppBarConfi;
    /**
     *
     * Declaracion del atributo activityMain
     * @author Andres romero
     */
    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);



     binding = ActivityMain2Binding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain2.toolbar);
        binding.appBarMain2.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfi = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_servicios)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main2);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfi);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main2);
        return NavigationUI.navigateUp(navController, mAppBarConfi)
                || super.onSupportNavigateUp();
    }

    public void Seleccion(View view){
        switch(view.getId()) {
            case R.id.bananas:
                Toast.makeText(this, "pantalla de bananas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cerezas:
                Toast.makeText(this, "pantalla de cerezas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.hamburguesas:
                Toast.makeText(this, "pantalla de hamburguesas", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}