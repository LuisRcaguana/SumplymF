package com.example.sumplym;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sumplym.Pojos.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PantallaIncioFragment extends Fragment {

    static final String CLAVE_NOMBRE = "CONTRASEÑA";
    private View v;

    private Process pbcargar;
    private Button btnConectar;
    private TextView tvNuevaCuenta;
    private RelativeLayout rlCargando;
    private ProgressBar pbCargando;
    private Autenticacion autenticacion;

    private EditText etLoginUser;
    private EditText etLoginPasswd;

    private DatabaseReference dbR;
    private ChildEventListener cel;

    private Usuario usuario;

    public static final int REGISTRAR_CUENTA = 1;

    public PantallaIncioFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_pantalla_incio,container, false);
        autenticacion = new Autenticacion();
        btnConectar = v.findViewById(R.id.btnSignIn);
        tvNuevaCuenta = v.findViewById(R.id.tvNuevaCuenta);
        etLoginUser = v.findViewById(R.id.etLoginUser);
        etLoginPasswd = v.findViewById(R.id.etLoginPasswd);
        rlCargando = v.findViewById(R.id.rlCargando);
        pbCargando = v.findViewById(R.id.pbCargando);
        c_btnConectar();
        c_tvNuevaCuenta();

        rlCargando.setVisibility(View.INVISIBLE);
        pbCargando.setVisibility(View.INVISIBLE);

        return v;
    }


    private void c_btnConectar() {
        btnConectar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                rlCargando.setVisibility(View.VISIBLE);
                pbCargando.setVisibility(View.VISIBLE);
                autenticacion.obtenerCuenta(etLoginUser.getText().toString().trim(), etLoginPasswd.getText().toString().trim());
                autenticarUsuario();

            }

    private void autenticarUsuario() {
        if(autenticacion.validarDatos()){
            autenticacion.getFba().signInWithEmailAndPassword(etLoginUser.getText().toString().trim(),
                    etLoginPasswd.getText().toString().trim()).addOnCompleteListener((Activity) v.getContext(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        autenticacion.setUser(autenticacion.getFba().getCurrentUser());

                        dbR = FirebaseDatabase.getInstance().getReference().child("usuarios");
                        addChildEventistener();
                    } else {
                        Toast.makeText(getContext(), getString(R.string.toast_no_accede), Toast.LENGTH_SHORT).show();
                        rlCargando.setVisibility(View.INVISIBLE);
                        pbCargando.setVisibility(View.INVISIBLE);
                    }
                }
            });
        } else {
            Toast.makeText(getContext(),getString(R.string.toast_no_accede),Toast.LENGTH_SHORT).show();
            rlCargando.setVisibility(View.INVISIBLE);
            pbCargando.setVisibility(View.INVISIBLE);
        }
    }
        });
    }

    private void addChildEventistener() {
        if(cel == null){
            cel = new ChildEventListener() {

                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Usuario user = dataSnapshot.getValue(Usuario.class);
                    if(autenticacion.getFba().getCurrentUser().getEmail().equals(user.getEmail())){
                        usuario = new Usuario(user.getUser(), autenticacion.getFba().getCurrentUser().getEmail());
                        String key = dataSnapshot.getKey();
                        Intent i = new Intent(getContext(), OpcionesActivity.class);
                        i.putExtra( CLAVE_NOMBRE, usuario.getUser() );
                        startActivity(i);
                        getActivity().finish();
                    }
                    else{
                        rlCargando.setVisibility(View.INVISIBLE);
                        pbCargando.setVisibility(View.INVISIBLE);
                    }

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                }
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            };
            dbR.addChildEventListener(cel);
        }
    }
    private void c_tvNuevaCuenta() {
        tvNuevaCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(v.getContext(),Registrar_cuentaActivity.class);
                startActivityForResult(i,REGISTRAR_CUENTA);
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REGISTRAR_CUENTA){
            if(resultCode == getActivity().RESULT_OK){
                final Usuario usuario =data.getParcelableExtra("");
                String correo = usuario.getEmail();
                String passwd = usuario.obtenerContrasenia();
                autenticacion.getFba().createUserWithEmailAndPassword(correo, passwd).addOnCompleteListener((Activity) v.getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = autenticacion.getFba().getCurrentUser();
                            Toast.makeText(getContext(), getString(R.string.toast_CreateUserSucefully), Toast.LENGTH_SHORT).show();
                            etLoginUser.setText(user.getEmail());

                            DatabaseReference dbR;

                            dbR = FirebaseDatabase.getInstance().getReference().child("usuarios");
                            dbR.child(dbR.push().getKey()).setValue(usuario);
                        }
                    }
                });
            }
        }
    }


}
