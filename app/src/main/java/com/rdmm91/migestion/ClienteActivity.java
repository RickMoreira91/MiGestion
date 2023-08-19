package com.rdmm91.migestion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rdmm91.migestion.adapters.ClienteAdapter;
import com.rdmm91.migestion.adapters.ProveedorAdapter;
import com.rdmm91.migestion.controllers.ClienteController;
import com.rdmm91.migestion.models.Cliente;
import com.rdmm91.migestion.models.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class ClienteActivity extends AppCompatActivity {

    private List<Cliente> listaDeClientes;
    private RecyclerView recyclerView;
    private ClienteAdapter clienteAdapter;
    private ClienteController clienteController;
    private FloatingActionButton fabMenu, fabRegresar, fabAgregar;
    Animation fab_abierto, fab_cerrado;
    Boolean abierto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        clienteController = new ClienteController(ClienteActivity.this);

        recyclerView = findViewById(R.id.recyclerViewClientes);

        fabMenu = findViewById(R.id.fabMenu);
        fabRegresar = findViewById(R.id.fabRegresar);
        fabAgregar = findViewById(R.id.fabAgregar);

        fab_abierto = AnimationUtils.loadAnimation
                (this,R.anim.fab_abierto);
        fab_cerrado = AnimationUtils.loadAnimation
                (this,R.anim.fab_cerrado);

        listaDeClientes = new ArrayList<>();
        clienteAdapter = new ClienteAdapter(listaDeClientes);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(clienteAdapter);

        refrescarListaDeClientes();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Cliente clienteSeleccionado = listaDeClientes.get(position);
                Intent intent = new Intent(ClienteActivity.this, EditarClienteActivity.class);
                intent.putExtra("id_cliente", clienteSeleccionado.getId_cliente());
                intent.putExtra("nombre", clienteSeleccionado.getNombre());
                intent.putExtra("apellido", clienteSeleccionado.getApellido());
                intent.putExtra("direccion", clienteSeleccionado.getDireccion());
                intent.putExtra("telefono", clienteSeleccionado.getTelefono());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                final Cliente clienteAEliminar = listaDeClientes.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(ClienteActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                clienteController.eliminarCliente(clienteAEliminar);
                                refrescarListaDeClientes();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar al cliente " + clienteAEliminar.getNombre() + "?")
                        .create();
                dialog.show();
            }
        }));

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabAnimado();
            }
        });

        fabAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAnimado();
                Intent intent = new Intent(ClienteActivity.this, NuevoClienteActivity.class);
                startActivity(intent);
            }
        });

        fabRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAnimado();
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refrescarListaDeClientes();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void refrescarListaDeClientes() {

        if (clienteAdapter == null) return;
        listaDeClientes = clienteController.obtenerClientes();
        clienteAdapter.setListaDeClientes(listaDeClientes);
        clienteAdapter.notifyDataSetChanged();
    }

    private void fabAnimado(){
        if (abierto){
            fabAgregar.startAnimation(fab_cerrado);
            fabRegresar.startAnimation(fab_cerrado);
            fabAgregar.setClickable(false);
            fabRegresar.setClickable(false);
            abierto=false;
        }else {
            fabAgregar.startAnimation(fab_abierto);
            fabRegresar.startAnimation(fab_abierto);
            fabAgregar.setClickable(true);
            fabRegresar.setClickable(true);
            abierto=true;
        }
    }
}