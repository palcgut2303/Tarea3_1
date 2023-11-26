package com.example.tarea3_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_modoJuego;
TextView tv_baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_modoJuego = findViewById(R.id.dificultad2);
        tv_baseDatos = findViewById(R.id.textView4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.setGroupVisible(R.id.grupoMenu,true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if(id == R.id.preferencias){
            Intent intent = new Intent(this,SettingsActivity.class);
            lanzador.launch(intent);


        }


        return super.onOptionsItemSelected(item);
    }

    ActivityResultContract<Intent, ActivityResult> contrato = new ActivityResultContracts.StartActivityForResult();
    ActivityResultCallback<ActivityResult> respuesta = new ActivityResultCallback<ActivityResult>(){
        @SuppressLint("SetTextI18n")
        @Override
        public void onActivityResult(ActivityResult o) {
            if (o.getResultCode() == Activity.RESULT_OK) {
                //No hay códigos de actividad
                Intent intentDevuelto = o.getData();
                String modo = (String) intentDevuelto.getExtras().get("Modo");
                String tipoBaseDatos = (String) intentDevuelto.getExtras().get("tipoBaseDatos");
                String nombreServidor = (String) intentDevuelto.getExtras().get("nombreServidor");
                String ipServidor = (String) intentDevuelto.getExtras().get("ipServidor");

                tv_modoJuego.setText(modo);
                tv_baseDatos.setText(tipoBaseDatos + " " + nombreServidor + " " + ipServidor);
            }
        }
    };

    ActivityResultLauncher<Intent> lanzador = registerForActivityResult(contrato,respuesta);

}