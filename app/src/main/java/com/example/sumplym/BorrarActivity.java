package com.example.sumplym;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sumplym.db.EquiposPersistencia;
import com.example.sumplym.moden.Equiposdb;

public class BorrarActivity extends AppCompatActivity {

    EditText etId;
    EditText etNombre;
    EditText etModelo;
    EditText etTamaño;
    EditText etProcesador;

    EquiposPersistencia ep;

    Button btnBuscar;
    Button btnBorrar;
    Button btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);

        etId = findViewById(R.id.etIdM);
        etNombre = findViewById(R.id.etNombreM);
        etModelo = findViewById(R.id.etModeloM);
        etTamaño = findViewById(R.id.etTamañoM);
        etProcesador = findViewById(R.id.etProcesadorM);

        ep = new EquiposPersistencia(this);

        btnBuscar = findViewById(R.id.btnBuscarM);
        btnBorrar = findViewById(R.id.btnBorrarB);
        btnLimpiar = findViewById(R.id.btnlimpiarB);
    }

    public void BuscarM(View view) {
        String Sid = etId.getText().toString().trim();

        if (Sid.isEmpty()){
            Toast.makeText(this, (R.string.no_id),
                    Toast.LENGTH_LONG).show();

        }else{
            //Convertirlo a entero
            int Id = Integer.parseInt(Sid);
            Equiposdb equioi = ep.leercontacot(Id);



            if(equioi !=null) {

                etNombre.setText(equioi.getNombreE());
                etModelo.setText(equioi.getModeloE());
                etTamaño.setText(equioi.getTamaño());
                etProcesador.setText(equioi.getProcesadorE());


               btnBorrar.setEnabled(true);


            } else{
                Toast.makeText(this, (R.string.no_conta),
                        Toast.LENGTH_LONG).show();

            }

        }
    }

    public void Borrar(View view) {
        

    }

    public void LimpiarM(View view) {
    }
}
