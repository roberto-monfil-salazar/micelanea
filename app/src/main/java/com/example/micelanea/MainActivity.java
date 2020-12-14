package com.example.micelanea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
 // en el EditText van a ir los id de tus contenedores de texto que pusiste en el el activity_main los pongo aqui? sii---- asiiiii le sigues siiiii yaaaaaa...ahora vas hacer lo siguiente

   // EditText etNombre, etAPaterno, etAMaterno, etSexo, etDireccion, etFacebook, etInstagram;
    EditText etProducto, etPrecio, etCantidad, etPrecioVenta, etFechaCompra;//haces lo mismo listooo.... yaaaaa....estas ahi perra?
    FloatingActionButton fabGuardar, fabListar;

    ProgressDialog progressDialog;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String updateId, updateProducto, updatePrecio, updateCantidad, updatePrecioVenta, updateFechaCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etProducto = findViewById(R.id.etProducto);
        etPrecio = findViewById(R.id.etPrecio);
        etCantidad = findViewById(R.id.etCantidad);
        etPrecioVenta = findViewById(R.id.etPrecioVenta);
        etFechaCompra = findViewById(R.id.etFechaCompra);

        fabGuardar = findViewById(R.id.fabGuardar);
        fabListar = findViewById(R.id.fabListar);

        progressDialog = new ProgressDialog(this);

        db = FirebaseFirestore.getInstance();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar registro");


        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            actionBar.setTitle("Actualizar Datos");

            updateId = bundle.getString("updateId");
            updateProducto = bundle.getString("updateProducto");
            updatePrecio = bundle.getString("updatePrecio");//asi te vas hast abajo
            updateCantidad = bundle.getString("updateCantidad");
            updatePrecioVenta = bundle.getString("updatePrecioVenta");
            updateFechaCompra = bundle.getString("updateFechaCompra");

            etProducto.setText(updateProducto);
            etPrecio.setText(updatePrecio);
            etCantidad.setText(updateCantidad);
            etPrecioVenta.setText(updatePrecioVenta);
            etFechaCompra.setText(updateFechaCompra);


        } else {
            actionBar.setTitle("Agregar");
        }


        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/// podras hacer esa es que voy al baño jejejej vavva
                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 != null) {
                    String id = updateId;
                    String producto = etProducto.getText().toString().trim();
                    String precio = etPrecio.getText().toString().trim();
                    String cantidad = etCantidad.getText().toString().trim();
                    String precioVenta = etPrecioVenta.getText().toString().trim();
                    String fechaCompra = etFechaCompra.getText().toString().trim();

                    actualizarDatos(id, producto, precio, cantidad, precioVenta, fechaCompra);

                } else {
                    String producto = etProducto.getText().toString().trim();
                    String precio = etPrecio.getText().toString().trim();
                    String cantidad = etCantidad.getText().toString().trim();
                    String precioVenta = etPrecioVenta.getText().toString().trim();
                    String fechaCompra = etFechaCompra.getText().toString().trim();

                    cargarDatos(producto, precio, cantidad, precioVenta, fechaCompra);
                }
            }
        });


        fabListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivityPerson.class));
                finish();
            }
        });

    }


    private void cargarDatos(String producto, String precio, String cantidad, String precioVenta, String fechaCompra) {
        progressDialog.setTitle("Agregar datos");
        progressDialog.show();
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("producto", producto);
        doc.put("precio", precio);
        doc.put("cantidad", cantidad);
        doc.put("precioVenta", precioVenta);
        doc.put("fechaCompra", fechaCompra);


        db.collection("Documents").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Datos almacenados con éxito...", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Ha ocurrido un error..." + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void actualizarDatos(String id, String producto, String precio, String cantidad, String precioVenta, String fechaCompra) {
        progressDialog.setTitle("Actualizando datos a Firebase");
        progressDialog.show();

        /*
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("nombre", nombre);
        doc.put("apaterno", apaterno);
        doc.put("amaterno", amaterno);
        doc.put("sexo", sexo);
        doc.put("direccion", direccion);
        doc.put("facebook", facebook);
        doc.put("instagram", instagram);

         */
        db.collection("Documents")
                .document(id).update(
                "producto", producto,
                "precio", precio,
                "cantidad", cantidad,
                "precioVenta", precioVenta,
                "fechaCompra", fechaCompra
        )
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Actualización exitosa...", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Ha ocurrido un error..." + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}