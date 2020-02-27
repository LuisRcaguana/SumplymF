package com.example.sumplym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OpcionesActivity extends AppCompatActivity {

    private TextView usuario1;
    private TextView tvBienUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        usuario1 = findViewById(R.id.tvBienUser);
       String nombre = getIntent().getStringExtra(PantallaIncioFragment.CLAVE_NOMBRE);


        usuario1.setText(String.format(nombre));

        tvBienUser = findViewById(R.id.tvBienUser);

        tvBienUser.setText(nombre);
    }

    public void registar(View view) {
        Intent i = new Intent(this, RegistrarActivity.class);
        startActivity(i);
    }

    public void modificar(View view) {
        Intent i = new Intent(this, ModificarActivity.class);
        startActivity(i);
    }

    public void Borrar(View view) {Intent i = new Intent(this, BorrarActivity.class);
        startActivity(i);
    }

    public void Consulrae(View view) {
    }
}
