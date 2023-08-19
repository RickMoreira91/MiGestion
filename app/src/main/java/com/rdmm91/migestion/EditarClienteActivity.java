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

public class EditarClienteActivity extends AppCompatActivity {

    private EditText etEditarNombreCliente, etEditarApellidoCliente, etEditarDireccionCliente, etEditarTelefonoCliente;
    private FloatingActionButton fabMenu, fabCancelar, fabGuardar;
    private Cliente cliente;
    private ClienteController clienteController;
    Animation fab_abierto, fab_cerrado;
    Boolean abierto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);

        fab_abierto = AnimationUtils.loadAnimation
                (this,R.anim.fab_abierto);
        fab_cerrado = AnimationUtils.loadAnimation
                (this,R.anim.fab_cerrado);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }

        clienteController = new ClienteController(EditarClienteActivity.this);

        long idCliente = extras.getLong("id_cliente");
        String nombreCliente = extras.getString("nombre");
        String apellidoCliente = extras.getString("apellido");
        String direccionCliente = extras.getString("direccion");
        String telefonoCliente = extras.getString("telefono");
        cliente = new Cliente(idCliente, nombreCliente, apellidoCliente, direccionCliente, telefonoCliente);

        etEditarNombreCliente = findViewById(R.id.etEditarNombreCliente);
        etEditarApellidoCliente = findViewById(R.id.etEditarApellidoCliente);
        etEditarDireccionCliente = findViewById(R.id.etEditarDireccionCliente);
        etEditarTelefonoCliente = findViewById(R.id.etEditarTelefonoCliente);
        fabMenu = findViewById(R.id.fabMenu);
        fabGuardar = findViewById(R.id.fabGuardar);
        fabCancelar = findViewById(R.id.fabCancelar);

        etEditarNombreCliente.setText(cliente.getNombre());
        etEditarApellidoCliente.setText(cliente.getApellido());
        etEditarDireccionCliente.setText(cliente.getDireccion());
        etEditarTelefonoCliente.setText(cliente.getTelefono());

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

                etEditarNombreCliente.setError(null);
                etEditarApellidoCliente.setError(null);
                etEditarDireccionCliente.setError(null);
                etEditarTelefonoCliente.setError(null);

                String nuevoNombreCliente = etEditarNombreCliente.getText().toString();
                String nuevoApellidoCliente = etEditarApellidoCliente.getText().toString();
                String nuevaDireccionCliente = etEditarDireccionCliente.getText().toString();
                String nuevoTelefonoCliente = etEditarTelefonoCliente.getText().toString();

                if (nuevoNombreCliente.isEmpty()){
                    etEditarNombreCliente.setError("Escribe el Nombre");
                    etEditarNombreCliente.requestFocus();
                    return;
                }
                if (nuevoApellidoCliente.isEmpty()){
                    etEditarApellidoCliente.setError("Escribe el Apellido");
                    etEditarApellidoCliente.requestFocus();
                    return;
                }
                if (nuevaDireccionCliente.isEmpty()){
                    etEditarDireccionCliente.setError("Escribe la Dirección");
                    etEditarDireccionCliente.requestFocus();
                    return;
                }
                if (nuevoTelefonoCliente.isEmpty()){
                    etEditarTelefonoCliente.setError("Escribe el Teléfono");
                    etEditarTelefonoCliente.requestFocus();
                    return;
                }

                Cliente clienteConCambios = new Cliente(cliente.getId_cliente(), nuevoNombreCliente, nuevoApellidoCliente, nuevaDireccionCliente, nuevoTelefonoCliente);
                int filasModificadas = clienteController.editarCliente(clienteConCambios);
                if (filasModificadas != 1) {
                    Toast.makeText(EditarClienteActivity.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditarClienteActivity.this, "Registro editado.", Toast.LENGTH_SHORT).show();
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