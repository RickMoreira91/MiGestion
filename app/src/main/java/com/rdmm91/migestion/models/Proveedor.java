package com.rdmm91.migestion.models;

import androidx.annotation.NonNull;

public class Proveedor {
    private long id_proveedor;
    private String razon_social;
    private String direccion;
    private String telefono;
    private String url;
    private String correo_electronico;

    public Proveedor(String razon_social, String direccion, String telefono, String url, String correo_electronico) {
        this.razon_social = razon_social;
        this.direccion = direccion;
        this.telefono = telefono;
        this.url = url;
        this.correo_electronico = correo_electronico;
    }

    public Proveedor(long id_proveedor, String razon_social, String direccion, String telefono, String url, String correo_electronico) {
        this.id_proveedor = id_proveedor;
        this.razon_social = razon_social;
        this.direccion = direccion;
        this.telefono = telefono;
        this.url = url;
        this.correo_electronico = correo_electronico;
    }

    public long getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(long id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    @NonNull
    @Override
    public String toString() {
        return "Proveedor{" +
                "razon_social='" + razon_social + '\'' +
                "direccion='" + direccion + '\'' +
                "telefono='" + telefono + '\'' +
                "url='" + url + '\'' +
                "correo_electronico='" + correo_electronico +
                '}';
    }
}
