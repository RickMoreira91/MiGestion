package com.rdmm91.migestion.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rdmm91.migestion.bd.DbHelper;
import com.rdmm91.migestion.models.Cliente;
import com.rdmm91.migestion.models.Proveedor;

import java.util.ArrayList;

public class ClienteController {
    private DbHelper dbHelper;
    private String NOMBRE_TABLA = "t_clientes";

    public ClienteController(Context contexto){
        dbHelper = new DbHelper(contexto);
    }

    public long nuevoCliente(Cliente cliente){
        SQLiteDatabase baseDeDatos = dbHelper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", cliente.getNombre());
        valoresParaInsertar.put("apellido", cliente.getApellido());
        valoresParaInsertar.put("direccion", cliente.getDireccion());
        valoresParaInsertar.put("telefono", cliente.getTelefono());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int editarCliente(Cliente clienteEditado){
        SQLiteDatabase baseDeDatos = dbHelper.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", clienteEditado.getNombre());
        valoresParaActualizar.put("apellido", clienteEditado.getApellido());
        valoresParaActualizar.put("direccion", clienteEditado.getDireccion());
        valoresParaActualizar.put("telefono", clienteEditado.getTelefono());
        String campoParaActualizar = "id_cliente = ?";
        String[] argumentosParaActualizar = {String.valueOf(clienteEditado.getId_cliente())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int eliminarCliente(Cliente cliente) {
        SQLiteDatabase baseDeDatos = dbHelper.getWritableDatabase();
        String[] argumentos = {String.valueOf(cliente.getId_cliente())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id_cliente = ?", argumentos);
    }

    public ArrayList<Cliente> obtenerClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        SQLiteDatabase baseDeDatos = dbHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id_cliente", "nombre", "apellido", "direccion", "telefono"};
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
            return clientes;
        }

        if (!cursor.moveToFirst()) return clientes;
        do {
            long idCliente = cursor.getLong(0);
            String nombreDeDb = cursor.getString(1);
            String apellidoDeDb = cursor.getString(2);
            String direccionDeDb = cursor.getString(3);
            String telefonoDeDb = cursor.getString(4);
            Cliente clienteDeDb = new Cliente(idCliente, nombreDeDb, apellidoDeDb, direccionDeDb, telefonoDeDb);
            clientes.add(clienteDeDb);
        } while (cursor.moveToNext());

        cursor.close();
        return clientes;
    }
}