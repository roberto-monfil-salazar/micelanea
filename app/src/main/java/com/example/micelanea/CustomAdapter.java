package com.example.micelanea;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.IccOpenLogicalChannelResponse;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {

    ListActivityPerson listActivityPerson;
    List<PersonModel> mPersonModelList;

    public CustomAdapter(ListActivityPerson listActivity, List<PersonModel> personModelList) {
        this.listActivityPerson = listActivity;
        this.mPersonModelList = personModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.model, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String producto = mPersonModelList.get(position).getProducto();
                String precio = mPersonModelList.get(position).getPrecio();
                String cantidad = mPersonModelList.get(position).getCantidad();
                Toast.makeText(listActivityPerson, producto + " " + precio + " " + cantidad, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(listActivityPerson);
                String[] options = {"Actualizar datos", "Eliminar"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            String id = mPersonModelList.get(position).getId();
                            String producto = mPersonModelList.get(position).getProducto();
                            String precio = mPersonModelList.get(position).getPrecio();
                            String cantidad = mPersonModelList.get(position).getCantidad();
                            String precioventa = mPersonModelList.get(position).getPrecioventa();
                            String preciocompra = mPersonModelList.get(position).getPreciocompra();

                            Intent actualizarDatos = new Intent(listActivityPerson, MainActivity.class);
                            actualizarDatos.putExtra("updateId", id);
                            actualizarDatos.putExtra("updateProducto", producto);
                            actualizarDatos.putExtra("updatePrecio", precio);
                            actualizarDatos.putExtra("updateCantidad", cantidad);
                            actualizarDatos.putExtra("updatePrecioVenta", precioventa);
                            actualizarDatos.putExtra("updateFechaCompra", preciocompra);
                            listActivityPerson.startActivity(actualizarDatos);
                            // String id, String nombre, String apaterno, String amaterno, String sexo, String direccion, String facebook, String instagram
                        }

                        if (which == 1) {
                            listActivityPerson.eliminarRegistro(position);
                        }
                    }
                }).create().show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvProducto.setText(
                mPersonModelList.get(i).getProducto()
                        + " " + mPersonModelList.get(i).getPrecio()
                        + " " + mPersonModelList.get(i).getCantidad()
        );
    }

    @Override
    public int getItemCount() {
        return mPersonModelList.size();
    }
}
//eso es todo
//solo cargalo a tu fon y yaa....estas ahi?!!!perdon we me llamaron ahhhhhh
//cargalo a tu fon y checa va we
// ya los de sql me ayudas mañana...si cargo...no cargo nada jeje...desconecta y conecta tu fon vavvvavav le doy? no sirve mi cableentoces?
