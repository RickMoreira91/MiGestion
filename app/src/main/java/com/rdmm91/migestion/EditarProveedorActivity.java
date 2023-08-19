package com.rdmm91.migestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rdmm91.migestion.controllers.ProveedorController;
import com.rdmm91.migestion.models.Proveedor;

public class EditarProveedorActivity extends AppCompatActivity {

    private EditText etEditarRazonSocialProveedor, etEditarDireccionProveedor, etEditarTelefonoProveedor, etEditarUrlProveedor, etEditarCorreoElectronicoProveedor;
    private FloatingActionButton fabMenu, fabCancelar, fabGuardar;
    private Proveedor proveedor;
    private ProveedorController proveedorController;
    Animation fab_abierto, fab_cerrado;
    Boolean abierto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_proveedor);

        fab_abierto = AnimationUtils.loadAnimation
                (this,R.anim.fab_abierto);
        fab_cerrado = AnimationUtils.loadAnimation
                (this,R.anim.fab_cerrado);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }

        proveedorController = new ProveedorController(EditarProveedorActivity.this);

        long idProveedor = extras.getLong("id_proveedor");
        String razonsocialProveedor = extras.getString("razon_social");
        String direccionProveedor = extras.getString("direccion");
        String telefonoProveedor = extras.getString("telefono");
        String urlProveedor = extras.getString("url");
        String correoelectronicoProveedor = extras.getString("correo_electronico");
        proveedor = new Proveedor(idProveedor, razonsocialProveedor, direccionProveedor, telefonoProveedor, urlProveedor, correoelectronicoProveedor);

        etEditarRazonSocialProveedor = findViewById(R.id.etEditarRazonSocialProveedor);
        etEditarDireccionProveedor = findViewById(R.id.etEditarDireccionProveedor);
        etEditarTelefonoProveedor = findViewById(R.id.etEditarTelefonoProveedor);
        etEditarUrlProveedor = findViewById(R.id.etEditarUrlProveedor);
        etEditarCorreoElectronicoProveedor = findViewById(R.id.etEditarCorreoElectronicoProveedor);

        fabMenu = findViewById(R.id.fabMenu);
        fabGuardar = findViewById(R.id.fabGuardar);
        fabCancelar = findViewById(R.id.fabCancelar);

        etEditarRazonSocialProveedor.setText(proveedor.getRazon_social());
        etEditarDireccionProveedor.setText(proveedor.getDireccion());
        etEditarTelefonoProveedor.setText(proveedor.getTelefono());
        etEditarUrlProveedor.setText(proveedor.getUrl());
        etEditarCorreoElectronicoProveedor.setText(proveedor.getCorreo_electronico());

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabAnimado();
            }
        });

        fabCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAnimado();
                finish();
            }
        });

        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAnimado();

                etEditarRazonSocialProveedor.setError(null);
                etEditarDireccionProveedor.setError(null);
                etEditarTelefonoProveedor.setError(null);
                etEditarUrlProveedor.setError(null);
                etEditarCorreoElectronicoProveedor.setError(null);

                String nuevaRazonSocialProveedor = etEditarRazonSocialProveedor.getText().toString();
                String nuevaDireccionProveedor = etEditarDireccionProveedor.getText().toString();
                String nuevoTelefonoProveedor = etEditarTelefonoProveedor.getText().toString();
                String nuevaUrlProveedor = etEditarUrlProveedor.getText().toString();
                String nuevoCorreoElectronicoProveedor = etEditarCorreoElectronicoProveedor.getText().toString();

                if (nuevaRazonSocialProveedor.isEmpty()){
                    etEditarRazonSocialProveedor.setError("Escribe la Razón Social");
                    etEditarRazonSocialProveedor.requestFocus();
                    return;
                }
                if (nuevaDireccionProveedor.isEmpty()){
                    etEditarDireccionProveedor.setError("Escribe la Dirección");
                    etEditarDireccionProveedor.requestFocus();
                    return;
                }
                if (nuevoTelefonoProveedor.isEmpty()){
                    etEditarTelefonoProveedor.setError("Escribe el Teléfono");
                    etEditarTelefonoProveedor.requestFocus();
                    return;
                }

                Proveedor proveedorConCambios = new Proveedor(proveedor.getId_proveedor(), nuevaRazonSocialProveedor, nuevaDireccionProveedor, nuevoTelefonoProveedor, nuevaUrlProveedor, nuevoCorreoElectronicoProveedor);
                int filasModificadas = proveedorController.editarProveedor(proveedorConCambios);
                if (filasModificadas != 1) {
                    Toast.makeText(EditarProveedorActivity.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditarProveedorActivity.this, "Registro editado.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void fabAnimado(){
        if (abierto){
            fabGuardar.startAnimation(fab_cerrado);
            fabCancelar.startAnimation(fab_cerrado);
            fabGuardar.setClickable(false);
            fabCancelar.setClickable(false);
            abierto=false;
        }else {
            fabGuardar.startAnimation(fab_abierto);
            fabCancelar.startAnimation(fab_abierto);
            fabGuardar.setClickable(true);
            fabCancelar.setClickable(true);
            abierto=true;
        }
    }
}