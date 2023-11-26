package com.example.tarea3_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    String modoSeleccionado = "";
    String tipoBaseDatos = "";
    String nombreServidor = "";
    String ipServidor = "";
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }




    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Si se selecciona el botón atrás de la barra de menú,
        // cerramos la actividad Preferencias
        if (item.getItemId() == android.R.id.home) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

            modoSeleccionado = sharedPreferences.getString("categoria","Medio");
            tipoBaseDatos = String.valueOf(sharedPreferences.getBoolean("tipoBaseDatos",false));
            if(tipoBaseDatos.equalsIgnoreCase("true")){
                tipoBaseDatos = "Externa: ";
                nombreServidor = sharedPreferences.getString("nombre","Nombre de la base de datos");
                ipServidor = sharedPreferences.getString("ip","IP del servidor");
            }else{
                tipoBaseDatos = "Intera SQLite";
                nombreServidor = "";
                ipServidor = "";
            }


            Intent intentVolver = new Intent();
            intentVolver.putExtra("Modo",modoSeleccionado);
            intentVolver.putExtra("tipoBaseDatos",tipoBaseDatos);
            intentVolver.putExtra("ipServidor",ipServidor);
            intentVolver.putExtra("nombreServidor",nombreServidor);
            setResult(RESULT_OK, intentVolver);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}