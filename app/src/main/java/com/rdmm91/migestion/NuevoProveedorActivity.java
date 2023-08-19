package com.rdmm91.migestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rdmm91.migestion.controllers.ProveedorController;
import com.rdmm91.migestion.models.Proveedor;

public class NuevoProveedorActivity extends AppCompatActivity {

    private EditText etRazonSocialProveedor, etDireccionProveedor, etTelefonoProveedor, etUrlProveedor, etCorreoElectronicoProveedor;
    private FloatingActionButton fabMenu, fabGuardar, fabCancelar;
    private ProveedorController proveedorController;
    Animation fab_abierto, fab_cerrado;
    Boolean abierto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_proveedor);

        fab_abierto = AnimationUtils.loadAnimation
                (this,R.anim.fab_abierto);
        fab_cerrado = AnimationUtils.loadAnimation
                (this,R.anim.fab_cerrado);

        etRazonSocialProveedor = findViewById(R.id.etRazonSocialProveedor);
        etDireccionProveedor = findViewById(R.id.etDireccionProveedor);
        etTelefonoProveedor = findViewById(R.id.etTelefonoProveedor);
        etUrlProveedor = findViewById(R.id.etUrlProveedor);
        etCorreoElectronicoProveedor = findViewById(R.id.etCorreoElectronicoProveedor);

        fabMenu = findViewById(R.id.fabMenu);
        fabGuardar = findViewById(R.id.fabGuardar);
        fabCancelar = findViewById(R.id.fabCancelar);

        proveedorController = new ProveedorController(NuevoProveedorActivity.this);

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabAnimado();
            }
        });

        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAnimado();

                etRazonSocialProveedor.setError(null);
                etDireccionProveedor.setError(null);
                etTelefonoProveedor.setError(null);
                etUrlProveedor.setError(null);
                etCorreoElectronicoProveedor.setError(null);

                String razon_social = etRazonSocialProveedor.getText().toString();
                String direccion = etDireccionProveedor.getText().toString();
                String telefono = etTelefonoProveedor.getText().toString();
                String url = etUrlProveedor.getText().toString();
                String correo_electronico = etCorreoElectronicoProveedor.getText().toString();

                if ("".equals(razon_social)) {
                    etRazonSocialProveedor.setError("Escribe la Razón Social");
                    etRazonSocialProveedor.requestFocus();
                    return;
                }
                if ("".equals(direccion)) {
                    etDireccionProveedor.setError("Escribe la Dirección");
                    etDireccionProveedor.requestFocus();
                    return;
                }
                if ("".equals(telefono)) {
                    etTelefonoProveedor.setError("Escribe el Teléfono");
                    etTelefonoProveedor.requestFocus();
                    return;
                }

                Proveedor nuevoProveedor = new Proveedor(razon_social, direccion, telefono, url, correo_electronico);
                long id_proveedor = proveedorController.nuevoProveedor(nuevoProveedor);
                if (id_proveedor == -1) {
                    Toast.makeText(NuevoProveedorActivity.this, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NuevoProveedorActivity.this, "Registro guardado.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        fabCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAnimado();
                finish();
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