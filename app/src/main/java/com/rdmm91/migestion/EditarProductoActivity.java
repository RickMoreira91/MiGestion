package com.rdmm91.migestion;

import static com.rdmm91.migestion.MainActivity.stock_inicial;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rdmm91.migestion.controllers.ProductoController;
import com.rdmm91.migestion.models.Producto;

public class EditarProductoActivity extends AppCompatActivity {

    private EditText etEditarNombreProducto, etEditarPrecioProducto, etEditarStockInicialProducto;
    private FloatingActionButton fabMenu, fabCancelar, fabGuardar;
    private Producto producto;
    private ProductoController productoController;
    Animation fab_abierto, fab_cerrado;
    Boolean abierto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);

        fab_abierto = AnimationUtils.loadAnimation
                (this,R.anim.fab_abierto);
        fab_cerrado = AnimationUtils.loadAnimation
                (this,R.anim.fab_cerrado);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }

        productoController = new ProductoController(EditarProductoActivity.this);

        long idProducto = extras.getLong("id_producto");
        String nombreProducto = extras.getString("nombre");
        double precio_ventaProducto = extras.getDouble("precio_venta");
        Integer stock_inicialProducto = extras.getInt("stock_inicial");
        producto = new Producto(idProducto, nombreProducto, precio_ventaProducto, stock_inicialProducto);

        etEditarNombreProducto = findViewById(R.id.etEditarNombreProducto);
        etEditarPrecioProducto = findViewById(R.id.etEditarPrecioProducto);
        etEditarStockInicialProducto = findViewById(R.id.etEditarStockInicialProducto);

        etEditarStockInicialProducto.setInputType(InputType.TYPE_NULL);

        fabMenu = findViewById(R.id.fabMenu);
        fabGuardar = findViewById(R.id.fabGuardar);
        fabCancelar = findViewById(R.id.fabCancelar);

        etEditarNombreProducto.setText(producto.getNombre());
        etEditarPrecioProducto.setText((int) producto.getPrecio_venta());
        etEditarStockInicialProducto.setText(stock_inicial);

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

                etEditarNombreProducto.setError(null);
                etEditarPrecioProducto.setError(null);

                String nuevoNombreProducto = etEditarNombreProducto.getText().toString();
                double nuevoPrecioProducto = Double.parseDouble(etEditarPrecioProducto.getText().toString());
                Integer nuevoStockInicialProducto = Integer.parseInt(etEditarStockInicialProducto.getText().toString());

                if (nuevoNombreProducto.isEmpty()){
                    etEditarNombreProducto.setError("Escribe el Nombre");
                    etEditarNombreProducto.requestFocus();
                    return;
                }
                if ("".equals(nuevoPrecioProducto)) {
                    etEditarPrecioProducto.setError("Escribe el Precio");
                    etEditarPrecioProducto.requestFocus();
                    return;
                }

                Producto productoConCambios = new Producto(producto.getId_producto(), nuevoNombreProducto, nuevoPrecioProducto, nuevoStockInicialProducto);
                int filasModificadas = productoController.editarProducto(productoConCambios);
                if (filasModificadas != 1) {
                    Toast.makeText(EditarProductoActivity.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditarProductoActivity.this, "Registro editado.", Toast.LENGTH_SHORT).show();
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