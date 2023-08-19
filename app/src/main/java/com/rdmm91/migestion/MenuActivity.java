package com.rdmm91.migestion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    CardView cvProveedor, cvCliente, cvProducto, cvCategoria, cvEntrada, cvSalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cvProveedor = findViewById(R.id.cvProveedor);
        cvCliente = findViewById(R.id.cvCliente);
        cvProducto = findViewById(R.id.cvProducto);
        cvEntrada = findViewById(R.id.cvEntrada);
        cvSalida = findViewById(R.id.cvSalida);

        cvProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ProveedorActivity.class);
                startActivity(intent);
            }
        });

        cvCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ClienteActivity.class);
                startActivity(intent);
            }
        });

        cvProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ProductoActivity.class);
                startActivity(intent);
            }
        });

        cvEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, EntradaActivity.class);
                startActivity(intent);
            }
        });
    }
}