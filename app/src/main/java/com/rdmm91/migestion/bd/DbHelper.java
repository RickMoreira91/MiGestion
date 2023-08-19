package com.rdmm91.migestion.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String BASE_DE_DATOS = "dbapp.db",
            TABLA_PROVEEDORES = "t_proveedores",
            TABLA_CLIENTES = "t_clientes",
            TABLA_PRODUCTOS = "t_productos";
    private static final int VERSION_BASE_DE_DATOS = 1;

    public DbHelper(Context context) {
        super(context, BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_PROVEEDORES + "(" +
                "id_proveedor INTEGER PRIMARY KEY AUTOINCREMENT," +
                "razon_social TEXT NOT NULL," +
                "direccion TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "url TEXT," +
                "correo_electronico TEXT)");

        db.execSQL("CREATE TABLE " + TABLA_CLIENTES + "(" +
                "id_cliente INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "direccion TEXT NOT NULL," +
                "telefono TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLA_PRODUCTOS + "(" +
                "id_producto INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "precio_venta REAL NOT NULL," +
                "stock_inicial INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLA_PROVEEDORES);
        db.execSQL("DROP TABLE " + TABLA_CLIENTES);
        db.execSQL("DROP TABLE " + TABLA_PRODUCTOS);
        onCreate(db);
    }
}
