package com.rdmm91.migestion;

import static com.rdmm91.migestion.MainActivity.stock_inicial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rdmm91.migestion.controllers.ProductoController;
import com.rdmm91.migestion.controllers.ProveedorController;
import com.rdmm91.migestion.models.Producto;
import com.rdmm91.migestion.models.Proveedor;

public class NuevoProductoActivity extends AppCompatActivity {

    private EditText etNombreProducto, etPrecioProducto, etStockInicialProducto;
    private FloatingActionButton fabMenu, fabGuardar, fabCancelar;
    private ProductoController productoController;
    Animation fab_abierto, fab_cerrado;
    Boolean abierto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);

        fab_abierto = AnimationUtils.loadAnimation
                (this,R.anim.fab_abierto);
        fab_cerrado = AnimationUtils.loadAnimation
                (this,R.anim.fab_cerrado);

        etNombreProducto = findViewById(R.id.etNombreProducto);
        etPrecioProducto = findViewById(R.id.etPrecioProducto);
        etStockInicialProducto = findViewById(R.id.etStockInicialProducto);

        etStockInicialProducto.setInputType(InputType.TYPE_NULL);

        etStockInicialProducto.setText(stock_inicial);

        fabMenu = findViewById(R.id.fabMenu);
        fabGuardar = findViewById(R.id.fabGuardar);
        fabCancelar = findViewById(R.id.fabCancelar);

        productoController = new ProductoController(NuevoProductoActivity.this);

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

                etNombreProducto.setError(null);
                etPrecioProducto.setError(null);

                String nombre = etNombreProducto.getText().toString();
                double precio_venta = Double.parseDouble(etPrecioProducto.getText().toString());
                Integer stock_inicial = Integer.parseInt(etStockInicialProducto.getText().toString());

                if ("".equals(nombre)) {
                    etNombreProducto.setError("Escribe el Nombre");
                    etNombreProducto.requestFocus();
                    return;
                }

                if ("".equals(precio_venta)) {
                    etPrecioProducto.setError("Escribe el Precio");
                    etPrecioProducto.requestFocus();
                    return;
                }

                try {
                    precio_venta = Double.parseDouble(etPrecioProducto.getText().toString());
                } catch (NumberFormatException e) {
                    etPrecioProducto.setError("Escribe el Precio");
                    etPrecioProducto.requestFocus();
                    return;
                }

                Producto nuevoProducto = new Producto(nombre, precio_venta, stock_inicial);
                long id_producto = productoController.nuevoProducto(nuevoProducto);
                if (id_producto == -1) {
                    Toast.makeText(NuevoProductoActivity.this, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NuevoProductoActivity.this, "Registro guardado.", Toast.LENGTH_SHORT).show();
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