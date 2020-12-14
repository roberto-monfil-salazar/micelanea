package com.example.micelanea;

public class PersonModel {


    //aqui vas a poner tus atrubutos de tus tablas no me acuerdo jejejje...checa si es asi siiiiii solo falto la de facebook tambien lo pusiste? no mejor quitala
    String id, producto, precio, cantidad, precioventa, preciocompra;

    public PersonModel(String id, String producto, String precio, String cantidad, String precioventa, String preciocompra) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.precioventa = precioventa;
        this.preciocompra = preciocompra;

    }

    public PersonModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(String precioventa) {
        this.precioventa = precioventa;
    }

    public String getPreciocompra() {
        return preciocompra;
    }

    public void setPreciocompra(String preciocompra) {
        this.preciocompra = preciocompra;
    }
}
//listo!!!