package com.rdmm91.migestion.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rdmm91.migestion.bd.DbHelper;
import com.rdmm91.migestion.models.Proveedor;

import java.util.ArrayList;

public class ProveedorController {
    private DbHelper dbHelper;
    private String NOMBRE_TABLA = "t_proveedores";

    public ProveedorController(Context contexto){
        dbHelper = new DbHelper(contexto);
    }

    public long nuevoProveedor(Proveedor proveedor){
        SQLiteDatabase baseDeDatos = dbHelper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("razon_social", proveedor.getRazon_social());
        valoresParaInsertar.put("direccion", proveedor.getDireccion());
        valoresParaInsertar.put("telefono", proveedor.getTelefono());
        valoresParaInsertar.put("url", proveedor.getUrl());
        valoresParaInsertar.put("correo_electronico", proveedor.getCorreo_electronico());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int editarProveedor(Proveedor proveedorEditado){
        SQLiteDatabase baseDeDatos = dbHelper.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("razon_social", proveedorEditado.getRazon_social());
        valoresParaActualizar.put("direccion", proveedorEditado.getDireccion());
        valoresParaActualizar.put("telefono", proveedorEditado.getTelefono());
        valoresParaActualizar.put("url", proveedorEditado.getUrl());
        valoresParaActualizar.put("correo_electronico", proveedorEditado.getCorreo_electronico());
        String campoParaActualizar = "id_proveedor = ?";
        String[] argumentosParaActualizar = {String.valueOf(proveedorEditado.getId_proveedor())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int eliminarProveedor(Proveedor proveedor) {
        SQLiteDatabase baseDeDatos = dbHelper.getWritableDatabase();
        String[] argumentos = {String.valueOf(proveedor.getId_proveedor())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id_proveedor = ?", argumentos);
    }

    public ArrayList<Proveedor> obtenerProveedores() {
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        SQLiteDatabase baseDeDatos = dbHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id_proveedor", "razon_social", "direccion", "telefono", "url", "correo_electronico"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null){
            return proveedores;
        }

        if (!cursor.moveToFirst()) return proveedores;
        do{
            long idProveedor = cursor.getLong(0);
            String razonsocialDeDb = cursor.getString(1);
            String direccionDeDb = cursor.getString(2);
            String telefonoDeDb = cursor.getString(3);
            String urlDeDb = cursor.getString(4);
            String correoelectronicoDeDb = cursor.getString(5);
            Proveedor proveedorDeDb = new Proveedor(idProveedor, razonsocialDeDb, direccionDeDb, telefonoDeDb, urlDeDb, correoelectronicoDeDb);
            proveedores.add(proveedorDeDb);
        } while (cursor.moveToNext());

        cursor.close();
        return proveedores;
    }
}