package com.example.myapplication.util;

public class Constantes {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db_productos";
    public static final String TABLE_PRODUCTOS = "t_productos";





    public static final String ID_PRODUCTO = "id";
    public static final String DESCRIPCION = "descripcion";
    public static final String FECHA = "fecha";
    public static final String MONTO = "monto";
    public static final String INGRESOS = "ingresos";
    public static final String EGRESOS = "egresos";



    public static final String CREAR_TABLA_PRODUCTOS = " CREATE TABLE " +TABLE_PRODUCTOS+ " ( " + ID_PRODUCTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +DESCRIPCION+ " TEXT, " +FECHA+ " TEXT, " + MONTO + " TEXT, " + EGRESOS + " TEXT, " +INGRESOS+ " TEXT )";



}
