package com.rdmm91.migestion.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rdmm91.migestion.R;
import com.rdmm91.migestion.models.Producto;
import com.rdmm91.migestion.models.Proveedor;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.MyViewHolder> {

    private List<Producto> listaDeProductos;

    public void setListaDeProductos(List<Producto> listaDeProductos) {
        this.listaDeProductos = listaDeProductos;
    }

    public ProductoAdapter(List<Producto> productos){
        this.listaDeProductos = productos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item_lista_productos = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_item_productos, viewGroup, false);
        return new MyViewHolder(item_lista_productos);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Producto producto = listaDeProductos.get(i);

        String nombreProducto = producto.getNombre();
        Double precioProducto = producto.getPrecio_venta();
        Integer stockProducto = producto.getStock_inicial();

        myViewHolder.viewNombre.setText(nombreProducto);
        myViewHolder.viewPrecio.setText(String.valueOf(precioProducto));
        myViewHolder.viewStock.setText(String.valueOf(stockProducto));
    }

    @Override
    public int getItemCount() {
        return listaDeProductos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewPrecio, viewStock;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.viewNombre = itemView.findViewById(R.id.viewNombre);
            this.viewPrecio = itemView.findViewById(R.id.viewPrecio);
            this.viewStock = itemView.findViewById(R.id.viewStock);
        }
    }
}
