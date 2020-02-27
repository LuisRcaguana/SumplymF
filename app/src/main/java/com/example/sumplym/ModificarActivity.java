package com.example.sumplym;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sumplym.db.EquiposPersistencia;
import com.example.sumplym.moden.Equiposdb;

public class ModificarActivity extends AppCompatActivity {
    EditText etId;
    EditText etNombre;
    EditText etModelo;
    EditText etTamaño;
    EditText etProcesador;

    EquiposPersistencia ep;

    Button btnBuscar;
    Button btnGuardar;
    Button btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        etId = findViewById(R.id.etIdM);
        etNombre = findViewById(R.id.etNombreM);
        etModelo = findViewById(R.id.etModeloM);
        etTamaño = findViewById(R.id.etTamañoM);
        etProcesador = findViewById(R.id.etProcesadorM);

        ep = new EquiposPersistencia(this);

        btnBuscar = findViewById(R.id.btnBuscarM);
        btnGuardar = findViewById(R.id.btnGuardarM);
        btnLimpiar = findViewById(R.id.btnCancelarM);


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


                habilitar(true);


            } else{
                Toast.makeText(this, (R.string.no_conta),
                        Toast.LENGTH_LONG).show();

            }

        }
    }

    public void GuardarM(View view) {
        int id = Integer.parseInt(etId.getText().toString());

        String nombre = etNombre.getText().toString().trim();
        String modelo = etModelo.getText().toString().trim();
        String tamaño = etTamaño.getText().toString().trim();
        String procesador = etProcesador.getText().toString().trim();



        if(nombre.isEmpty() ||modelo.isEmpty()||tamaño.isEmpty()||procesador.isEmpty()){
            Toast.makeText(this, getString(R.string.no_datos),
                    Toast.LENGTH_LONG).show();
        }else {
            Equiposdb equiposdb = new Equiposdb(nombre, modelo, tamaño, procesador);
            equiposdb.setId(id);
            ep.actualizar(equiposdb);
            Toast.makeText(this, getString(R.string.update_ok),
                    Toast.LENGTH_LONG).show();

        }

    }

    public void LimpiarM(View view) {
        etId.setText("");
        etModelo.setText("");
        etNombre.setText("");
        etTamaño.setText("");
        etProcesador.setText("");

        habilitar(true);

    }
    private void habilitar(boolean b) {

        etNombre.setEnabled(b);
        etModelo.setEnabled(b);
        etTamaño.setEnabled(b);
        etProcesador.setEnabled(b);
        etId.setEnabled(b);

        btnLimpiar.setEnabled(b);
        btnGuardar.setEnabled(b);
        btnBuscar.setEnabled(b);
    }
}
