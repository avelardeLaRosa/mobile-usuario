package com.example.myapplication.model;

public class Producto {

    private int id;
    private String descripcion;
    private String fecha;
    private String monto;
    private String ingresos;
    private String egresos;

    public Producto() {
    }

    public Producto(String descripcion, String fecha, String monto, String ingresos, String egresos) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.monto = monto;
        this.ingresos = ingresos;
        this.egresos = egresos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getIngresos() {
        return ingresos;
    }

    public void setIngresos(String ingresos) {
        this.ingresos = ingresos;
    }

    public String getEgresos() {
        return egresos;
    }

    public void setEgresos(String egresos) {
        this.egresos = egresos;
    }
}
