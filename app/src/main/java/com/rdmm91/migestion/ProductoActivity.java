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
import com.rdmm91.migestion.adapters.ProductoAdapter;
import com.rdmm91.migestion.controllers.ProductoController;
import com.rdmm91.migestion.models.Producto;
import com.rdmm91.migestion.models.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class ProductoActivity extends AppCompatActivity {

    private List<Producto> listaDeProductos;
    private RecyclerView recyclerView;
    private ProductoAdapter productoAdapter;
    private ProductoController productoController;
    private FloatingActionButton fabMenu, fabRegresar, fabAgregar;
    Animation fab_abierto, fab_cerrado;
    Boolean abierto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        productoController = new ProductoController(ProductoActivity.this);

        recyclerView = findViewById(R.id.recyclerViewProductos);

        fabMenu = findViewById(R.id.fabMenu);
        fabRegresar = findViewById(R.id.fabRegresar);
        fabAgregar = findViewById(R.id.fabAgregar);

        fab_abierto = AnimationUtils.loadAnimation
                (this,R.anim.fab_abierto);
        fab_cerrado = AnimationUtils.loadAnimation
                (this,R.anim.fab_cerrado);

        listaDeProductos = new ArrayList<>();
        productoAdapter = new ProductoAdapter(listaDeProductos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productoAdapter);

        refrescarListaDeProductos();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Producto productoSeleccionado = listaDeProductos.get(position);
                Intent intent = new Intent(ProductoActivity.this, EditarProductoActivity.class);
                intent.putExtra("id_producto", productoSeleccionado.getId_producto());
                intent.putExtra("nombre", productoSeleccionado.getNombre());
                intent.putExtra("precio_venta", productoSeleccionado.getPrecio_venta());
                intent.putExtra("stock_inicial", productoSeleccionado.getStock_inicial());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                final Producto productoAEliminar = listaDeProductos.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(ProductoActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                productoController.eliminarProducto(productoAEliminar);
                                refrescarListaDeProductos();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar el producto " + productoAEliminar.getNombre() + "?")
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
                Intent intent = new Intent(ProductoActivity.this, NuevoProductoActivity.class);
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
        refrescarListaDeProductos();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void refrescarListaDeProductos() {

        if (productoAdapter == null) return;
        listaDeProductos = productoController.obtenerProductos();
        productoAdapter.setListaDeProductos(listaDeProductos);
        productoAdapter.notifyDataSetChanged();
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