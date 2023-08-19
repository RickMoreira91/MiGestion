package com.rdmm91.migestion.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rdmm91.migestion.R;
import com.rdmm91.migestion.models.Cliente;
import com.rdmm91.migestion.models.Proveedor;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.MyViewHolder> {

    private List<Cliente> listaDeClientes;

    public void setListaDeClientes(List<Cliente> listaDeClientes) {
        this.listaDeClientes = listaDeClientes;
    }

    public ClienteAdapter(List<Cliente> clientes){
        this.listaDeClientes = clientes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item_lista_clientes = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_item_clientes, viewGroup, false);
        return new MyViewHolder(item_lista_clientes);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Cliente cliente = listaDeClientes.get(i);

        String nombreCliente = cliente.getNombre();
        String apellidoCliente = cliente.getApellido();
        String direccionCliente = cliente.getDireccion();
        String telefonoCliente = cliente.getTelefono();

        myViewHolder.viewNombre.setText(nombreCliente);
        myViewHolder.viewApellido.setText(apellidoCliente);
        myViewHolder.viewDireccion.setText(direccionCliente);
        myViewHolder.viewTelefono.setText(telefonoCliente);
    }

    @Override
    public int getItemCount() {
        return listaDeClientes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewApellido, viewDireccion, viewTelefono;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.viewNombre = itemView.findViewById(R.id.viewNombre);
            this.viewApellido = itemView.findViewById(R.id.viewApellido);
            this.viewDireccion = itemView.findViewById(R.id.viewDireccion);
            this.viewTelefono = itemView.findViewById(R.id.viewTelefono);
        }
    }
}
