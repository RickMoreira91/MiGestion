package com.rdmm91.migestion.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rdmm91.migestion.bd.DbHelper;
import com.rdmm91.migestion.models.Cliente;
import com.rdmm91.migestion.models.Producto;

import java.util.ArrayList;

public class ProductoController {
    private DbHelper dbHelper;
    private String NOMBRE_TABLA = "t_productos";

    public ProductoController(Context contexto){
        dbHelper = new DbHelper(contexto);
    }

    public long nuevoProducto(Producto producto){
        SQLiteDatabase baseDeDatos = dbHelper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", producto.getNombre());
        valoresParaInsertar.put("precio_venta", producto.getPrecio_venta());
        valoresParaInsertar.put("stock_inicial", producto.getStock_inicial());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int editarProducto(Producto productoEditado){
        SQLiteDatabase baseDeDatos = dbHelper.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", productoEditado.getNombre());
        valoresParaActualizar.put("precio_venta", productoEditado.getPrecio_venta());
        valoresParaActualizar.put("stock_inicial", productoEditado.getStock_inicial());
        String campoParaActualizar = "id_producto = ?";
        String[] argumentosParaActualizar = {String.valueOf(productoEditado.getId_producto())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int eliminarProducto(Producto producto) {
        SQLiteDatabase baseDeDatos = dbHelper.getWritableDatabase();
        String[] argumentos = {String.valueOf(producto.getId_producto())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id_producto = ?", argumentos);
    }

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        SQLiteDatabase baseDeDatos = dbHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id_producto", "nombre", "precio_venta", "stock_inicial"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {
            return productos;
        }

        if (!cursor.moveToFirst()) return productos;
        do {
            long idProducto = cursor.getLong(0);
            String nombreDeDb = cursor.getString(1);
            Double precioDeDb = cursor.getDouble(2);
            Integer stockDeDb = cursor.getInt(3);
            Producto productoDeDb = new Producto(idProducto, nombreDeDb, precioDeDb, stockDeDb);
            productos.add(productoDeDb);
        } while (cursor.moveToNext());

        cursor.close();
        return productos;
    }
}
