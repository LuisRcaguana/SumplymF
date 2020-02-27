package com.example.sumplym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sumplym.Pojos.Usuario;
import com.example.sumplym.R;

public class Registrar_cuentaActivity extends AppCompatActivity {

    private EditText etRegUserNombre;
    private EditText etRegUserEmail;
    private EditText etPassword;

    private String userNom;
    private String correo;
    private String passwd;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cuenta);
        etRegUserEmail = findViewById(R.id.etRegUserEmail);
        etPassword = findViewById(R.id.etPassword);
        etRegUserNombre = findViewById(R.id.etRegUserNombre);
        getSupportActionBar().hide();

    }

    public void c_btnRegistrar(View view) {
        if(etRegUserNombre.getText().toString().isEmpty()){
            String userEmail = etRegUserEmail.getText().toString().trim();
            int pos = 0;
            boolean cont = true;
            while(pos < userEmail.length() && cont){
                if(userEmail.charAt(pos) == '@'){
                    cont = false;
                }
                else{
                    userNom += userEmail.charAt(pos);
                }
                pos++;
            }
        }
        else{
            userNom = etRegUserNombre.getText().toString().trim();
        }
        correo = etRegUserEmail.getText().toString().trim();
        passwd = etPassword.getText().toString().trim();
        usuario = new Usuario(userNom, correo, passwd);
        if(verificarDatos()){
            Intent i = getIntent();
            i.putExtra(getString(R.string.REG_USUARIO),usuario);
            setResult(RESULT_OK,i);
            finish();
        }
        else if(!verificarDatos()){
            Toast.makeText(this,getString(R.string.toast_verifData),Toast.LENGTH_SHORT).show();
        }
        else{
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    private boolean verificarDatos() {
        if (correo.isEmpty() || passwd.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
