package com.rdmm91.migestion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rdmm91.migestion.controllers.ClienteController;
import com.rdmm91.migestion.controllers.ProveedorController;
import com.rdmm91.migestion.models.Cliente;
import com.rdmm91.migestion.models.Proveedor;

public class NuevoClienteActivity extends AppCompatActivity {

    private EditText etNombreCliente, etApellidoCliente, etDireccionCliente, etTelefonoCliente;
    private FloatingActionButton fabMenu, fabGuardar, fabCancelar;
    private ClienteController clienteController;
    Animation fab_abierto, fab_cerrado;
    Boolean abierto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_cliente);

        fab_abierto = AnimationUtils.loadAnimation
                (this,R.anim.fab_abierto);
        fab_cerrado = AnimationUtils.loadAnimation
                (this,R.anim.fab_cerrado);

        etNombreCliente = findViewById(R.id.etNombreCliente);
        etApellidoCliente = findViewById(R.id.etApellidoCliente);
        etDireccionCliente = findViewById(R.id.etDireccionCliente);
        etTelefonoCliente = findViewById(R.id.etTelefonoCliente);

        fabMenu = findViewById(R.id.fabMenu);
        fabGuardar = findViewById(R.id.fabGuardar);
        fabCancelar = findViewById(R.id.fabCancelar);

        clienteController = new ClienteController(NuevoClienteActivity.this);

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

                etNombreCliente.setError(null);
                etApellidoCliente.setError(null);
                etDireccionCliente.setError(null);
                etTelefonoCliente.setError(null);

                String nombre = etNombreCliente.getText().toString();
                String apellido = etApellidoCliente.getText().toString();
                String direccion = etDireccionCliente.getText().toString();
                String telefono = etTelefonoCliente.getText().toString();

                if ("".equals(nombre)) {
                    etNombreCliente.setError("Escribe el Nombre");
                    etNombreCliente.requestFocus();
                    return;
                }
                if ("".equals(apellido)) {
                    etApellidoCliente.setError("Escribe el Apellido");
                    etApellidoCliente.requestFocus();
                    return;
                }
                if ("".equals(direccion)) {
                    etDireccionCliente.setError("Escribe la Dirección");
                    etDireccionCliente.requestFocus();
                    return;
                }
                if ("".equals(telefono)) {
                    etTelefonoCliente.setError("Escribe el Teléfono");
                    etTelefonoCliente.requestFocus();
                    return;
                }

                Cliente nuevoCliente = new Cliente(nombre, apellido, direccion, telefono);
                long id_cliente = clienteController.nuevoCliente(nuevoCliente);
                if (id_cliente == -1) {
                    Toast.makeText(NuevoClienteActivity.this, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NuevoClienteActivity.this, "Registro guardado.", Toast.LENGTH_SHORT).show();
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