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
import com.rdmm91.migestion.adapters.ProveedorAdapter;
import com.rdmm91.migestion.controllers.ProveedorController;
import com.rdmm91.migestion.models.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class ProveedorActivity extends AppCompatActivity {

    private List<Proveedor> listaDeProveedores;
    private RecyclerView recyclerView;
    private ProveedorAdapter proveedorAdapter;
    private ProveedorController proveedorController;
    private FloatingActionButton fabMenu, fabRegresar, fabAgregar;
    Animation fab_abierto, fab_cerrado;
    Boolean abierto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor);

        proveedorController = new ProveedorController(ProveedorActivity.this);

        recyclerView = findViewById(R.id.recyclerViewProveedores);

        fabMenu = findViewById(R.id.fabMenu);
        fabRegresar = findViewById(R.id.fabRegresar);
        fabAgregar = findViewById(R.id.fabAgregar);

        fab_abierto = AnimationUtils.loadAnimation
                (this,R.anim.fab_abierto);
        fab_cerrado = AnimationUtils.loadAnimation
                (this,R.anim.fab_cerrado);

        listaDeProveedores = new ArrayList<>();
        proveedorAdapter = new ProveedorAdapter(listaDeProveedores);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(proveedorAdapter);

        refrescarListaDeProveedores();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Proveedor proveedorSeleccionado = listaDeProveedores.get(position);
                Intent intent = new Intent(ProveedorActivity.this, EditarProveedorActivity.class);
                intent.putExtra("id_proveedor", proveedorSeleccionado.getId_proveedor());
                intent.putExtra("razon_social", proveedorSeleccionado.getRazon_social());
                intent.putExtra("direccion", proveedorSeleccionado.getDireccion());
                intent.putExtra("telefono", proveedorSeleccionado.getTelefono());
                intent.putExtra("url", proveedorSeleccionado.getUrl());
                intent.putExtra("correo_electronico", proveedorSeleccionado.getCorreo_electronico());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                final Proveedor proveedorAEliminar = listaDeProveedores.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(ProveedorActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                proveedorController.eliminarProveedor(proveedorAEliminar);
                                refrescarListaDeProveedores();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar al proveedor " + proveedorAEliminar.getRazon_social() + "?")
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
                Intent intent = new Intent(ProveedorActivity.this, NuevoProveedorActivity.class);
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
        refrescarListaDeProveedores();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void refrescarListaDeProveedores() {

        if (proveedorAdapter == null) return;
        listaDeProveedores = proveedorController.obtenerProveedores();
        proveedorAdapter.setListaDeProveedores(listaDeProveedores);
        proveedorAdapter.notifyDataSetChanged();
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