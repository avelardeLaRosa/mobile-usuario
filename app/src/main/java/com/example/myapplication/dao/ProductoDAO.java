package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myapplication.db.DbHelper;
import com.example.myapplication.model.Producto;
import com.example.myapplication.util.Constantes;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO extends DbHelper {

    Context context;
    List<Producto> productos = null;

    public ProductoDAO(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long ingresar(Producto p){

        long success = 0;

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Constantes.DESCRIPCION, p.getDescripcion());
            values.put(Constantes.FECHA, p.getFecha());
            values.put(Constantes.MONTO, p.getMonto());
            values.put(Constantes.INGRESOS, p.getIngresos());
            values.put(Constantes.EGRESOS, p.getEgresos());

            success = db.insert(Constantes.TABLE_PRODUCTOS, null, values);
            db.close();
        }catch (Exception e){
            e.toString();
        }
        return success;
    }


    public Producto consultarById(String id){


        Producto producto = null;
        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            producto = new Producto();
            String[] param = {id};
            Cursor cursor = db.rawQuery("SELECT * FROM "+Constantes.TABLE_PRODUCTOS+" WHERE "+Constantes.ID_PRODUCTO+" = ?", param);

            cursor.moveToFirst();
            producto.setId(cursor.getInt(0));
            producto.setDescripcion(cursor.getString(1));
            producto.setFecha(cursor.getString(2));
            producto.setIngresos(cursor.getString(3));
            producto.setMonto(cursor.getString(4));
            producto.setEgresos(cursor.getString(5));
            cursor.close();
            db.close();
        }catch (Exception e){
            e.toString();
        }

        return producto;

    }

    public long actualizar(Producto p){
        long success=0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String[] param = {String.valueOf(p.getId())};
            ContentValues values = new ContentValues();

            values.put(Constantes.ID_PRODUCTO,p.getId());
            values.put(Constantes.DESCRIPCION, p.getDescripcion());
            values.put(Constantes.FECHA, p.getFecha());
            values.put(Constantes.MONTO, p.getMonto());
            values.put(Constantes.INGRESOS, p.getIngresos());
            values.put(Constantes.EGRESOS, p.getEgresos());

            success = db.update(Constantes.TABLE_PRODUCTOS, values, Constantes.ID_PRODUCTO+" = ?", param);
            db.close();
        }catch (Exception e){
            e.toString();
        }
        return success;
    }

    public List<Producto> listar(){


        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            productos = new ArrayList<>();
            Producto p = null;
            Cursor cursor = db.rawQuery("SELECT * FROM "+Constantes.TABLE_PRODUCTOS, null);

            while (cursor.moveToNext()){
                p=new Producto();
                p.setId(cursor.getInt(0));
                p.setDescripcion(cursor.getString(1));
                p.setFecha(cursor.getString(2));
                p.setMonto(cursor.getString(3));
                p.setIngresos(cursor.getString(4));
                p.setEgresos(cursor.getString(5));

                productos.add(p);
            }

        }catch (Exception e){
            e.toString();
        }

        return productos;

    }


    public long eliminar(String id){
        long success = 0;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[]param = {id};

        success = db.delete(Constantes.TABLE_PRODUCTOS, Constantes.ID_PRODUCTO+"=?", param);


        db.close();
        return success;
    }

}
