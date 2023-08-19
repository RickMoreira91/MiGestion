package com.rdmm91.migestion.models;

import androidx.annotation.NonNull;

public class Cliente {
    private long id_cliente;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;

    public Cliente(String nombre, String apellido, String direccion, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Cliente(long id_cliente, String nombre, String apellido, String direccion, String telefono) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @NonNull
    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                "apellido='" + apellido + '\'' +
                "direccion='" + direccion + '\'' +
                "telefono='" + telefono +
                '}';
    }
}
