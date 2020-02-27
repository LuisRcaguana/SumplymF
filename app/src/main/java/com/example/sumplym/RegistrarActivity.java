package com.example.sumplym;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sumplym.db.EquiposPersistencia;
import com.example.sumplym.moden.Equiposdb;

public class RegistrarActivity extends AppCompatActivity {

    EditText nombree;
    EditText nmodeloE;
    EditText TamañoE;
    EditText ProcesadorE;

    EquiposPersistencia cp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        nombree = findViewById(R.id.etNombreE);
        nmodeloE = findViewById(R.id.modeloE);
        TamañoE = findViewById(R.id.tamañoE);
        ProcesadorE =findViewById(R.id.procesadorE);

        cp = new EquiposPersistencia(this);
    }

    public void GuardarR(View view) {
        String nombre = nombree.getText().toString().trim();
        String modelo = nmodeloE.getText().toString().trim();
        String tamaño = TamañoE.getText().toString().trim();
        String procesador = ProcesadorE.getText().toString().trim();


        if(nombre.isEmpty() || modelo.isEmpty() || tamaño.isEmpty() || procesador.isEmpty()){
            Toast.makeText(this, getString(R.string.no_datos),
                    Toast.LENGTH_LONG).show();
        }else{
            Equiposdb E = new Equiposdb(nombre,modelo,tamaño,procesador);
            Long id = cp.insertar(E);

            if (id != -1){
                Toast.makeText(this, getString(R.string.insertat_ok),
                        Toast.LENGTH_LONG).show();
                E.setId(id);
            }else {
                Toast.makeText(this, getString(R.string.insertat_ok),
                        Toast.LENGTH_LONG).show();
            }

        }
    }

    public void LimpiarR(View view) {
        nombree.setText("");
        nmodeloE.setText("");
        TamañoE.setText("");
        ProcesadorE.setText("");
    }
}
