package com.rdmm91.migestion.models;

import androidx.annotation.NonNull;

public class Producto {

    private long id_producto;
    private String nombre;
    private double precio_venta;
    private Integer stock_inicial;

    public Producto(String nombre, Double precio_venta, Integer stock_inicial) {
        this.nombre = nombre;
        this.precio_venta = precio_venta;
        this.stock_inicial = stock_inicial;
    }

    public Producto(long id_producto, String nombre, Double precio_venta, Integer stock_inicial) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio_venta = precio_venta;
        this.stock_inicial = stock_inicial;
    }

    public long getId_producto() {
        return id_producto;
    }

    public void setId_producto(long id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public Integer getStock_inicial() {
        return stock_inicial;
    }

    public void setStock_inicial(Integer stock_inicial) {
        this.stock_inicial = stock_inicial;
    }

    @NonNull
    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                "precio_venta='" + precio_venta + '\'' +
                "stock_inicial='" + stock_inicial +
                '}';
    }
}
