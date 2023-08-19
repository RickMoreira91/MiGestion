package com.rdmm91.migestion.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rdmm91.migestion.R;
import com.rdmm91.migestion.models.Proveedor;

import java.util.List;

public class ProveedorAdapter extends RecyclerView.Adapter<ProveedorAdapter.MyViewHolder> {

    private List<Proveedor> listaDeProveedores;

    public void setListaDeProveedores(List<Proveedor> listaDeProveedores) {
        this.listaDeProveedores = listaDeProveedores;
    }

    public ProveedorAdapter(List<Proveedor> proveedores){
        this.listaDeProveedores = proveedores;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item_lista_proveedores = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_item_proveedores, viewGroup, false);
        return new MyViewHolder(item_lista_proveedores);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Proveedor proveedor = listaDeProveedores.get(i);

        String razonsocialProveedor = proveedor.getRazon_social();
        String direccionProveedor = proveedor.getDireccion();
        String telefonoProveedor = proveedor.getTelefono();
        String urlProveedor = proveedor.getUrl();
        String correoelectronicoProveedor = proveedor.getCorreo_electronico();

        myViewHolder.viewRazonSocial.setText(razonsocialProveedor);
        myViewHolder.viewDireccion.setText(direccionProveedor);
        myViewHolder.viewTelefono.setText(telefonoProveedor);
        myViewHolder.viewUrl.setText(urlProveedor);
        myViewHolder.viewCorreoElectronico.setText(correoelectronicoProveedor);

        if(myViewHolder.viewUrl.getText().toString().isEmpty()){
            myViewHolder.viewUrl.setVisibility(View.GONE);
        }else{
            myViewHolder.viewUrl.setVisibility(View.VISIBLE);
        }

        if(myViewHolder.viewCorreoElectronico.getText().toString().isEmpty()){
            myViewHolder.viewCorreoElectronico.setVisibility(View.GONE);
        }else{
            myViewHolder.viewCorreoElectronico.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listaDeProveedores.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView viewRazonSocial, viewDireccion, viewTelefono, viewUrl, viewCorreoElectronico;

        MyViewHolder(View itemView) {
            super(itemView);

            this.viewRazonSocial = itemView.findViewById(R.id.viewRazonSocial);
            this.viewDireccion = itemView.findViewById(R.id.viewDireccion);
            this.viewTelefono = itemView.findViewById(R.id.viewTelefono);
            this.viewUrl = itemView.findViewById(R.id.viewUrl);
            this.viewCorreoElectronico = itemView.findViewById(R.id.viewCorreoElectronico);
        }
    }
}
