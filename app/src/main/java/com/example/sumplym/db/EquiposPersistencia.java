package com.example.sumplym.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.sumplym.moden.Equiposdb;

import java.util.ArrayList;

public class EquiposPersistencia {

  private Context context;
  private EquiposSQLiteHelper esp;


    public EquiposPersistencia(Context context) {
        this.context = context;
        esp = new EquiposSQLiteHelper(context);

    }
    public SQLiteDatabase openReadable() {
        return esp.getReadableDatabase();
    }
    public SQLiteDatabase openWriteable() {
        return esp.getWritableDatabase();
    }
    public void close(SQLiteDatabase database) {
        database.close();
    }


    public Long insertar(Equiposdb e){

        //OBTENEMOS LA TRASCRIPCION DE LOS DATOS EN MODO ESCRITURA
        SQLiteDatabase database = openWriteable();
        //MARCAR EL INICIO DELA TRANSANCION
        database.beginTransaction();


        //volcar_todo la informacion  que queremos insertar
        ContentValues contactoValues = new ContentValues();

        contactoValues.put(EquiposContract.ContactoEntry.COLUMN_NAME,
                e.getNombreE());
        contactoValues.put(EquiposContract.ContactoEntry.COLUMN_MODELO,
                e.getModeloE());
        contactoValues.put(EquiposContract.ContactoEntry.COLUMN_TAMAÑO,
                e.getTamaño());
        contactoValues.put(EquiposContract.ContactoEntry.COLUMN_PROCESADOR,
                e.getProcesadorE());

        //de esta manera estamo haciendo un insert
        long id=  database.insert(EquiposContract.ContactoEntry.TABLE_NAME,
                null, contactoValues);


        if(id !=-1){
            database.setTransactionSuccessful();
        }
        database.endTransaction();
        close(database);

        //estod debemos tener para cualquier transancion
        return id;
    }
    public void actualizar(Equiposdb e){

        //OBTENEMOS LA TRASCRIPCION DE LOS DATOS EN MODO ESCRITURA
        SQLiteDatabase database = openWriteable();
        //MARCAR EL INICIO DELA TRANSANCION
        database.beginTransaction();

        //todo: Gestionar el update
        ContentValues contactoValues = new ContentValues();
        contactoValues.put(EquiposContract.ContactoEntry.COLUMN_NAME,
                e.getNombreE());
        contactoValues.put(EquiposContract.ContactoEntry.COLUMN_MODELO,
                e.getModeloE());
        contactoValues.put(EquiposContract.ContactoEntry.COLUMN_TAMAÑO,
                e.getTamaño());
        contactoValues.put(EquiposContract.ContactoEntry.COLUMN_PROCESADOR,
                e.getProcesadorE());

        database.update(EquiposContract.ContactoEntry.TABLE_NAME,
                contactoValues,String.format("%s=%d",
                        EquiposContract.ContactoEntry.COLUMN_ID
                        ,e.getId()),
                null);



        String [] whereAr = {String.valueOf(e.getId())};

        database.update(EquiposContract.ContactoEntry.TABLE_NAME,
                contactoValues,
                String.format(EquiposContract.ContactoEntry.COLUMN_ID + " = ?"),
                whereAr);


        //estod debemos tener para cualquier transancion
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }

    public void BorrarContacto(long idContacto){
        //OBTENEMOS LA TRASCRIPCION DE LOS DATOS EN MODO ESCRITURA
        SQLiteDatabase database = openWriteable();
        //MARCAR EL INICIO DELA TRANSANCION
        database.beginTransaction();

        //TODO: ELIMINAR CONTACTO

        //creamos un arry
        String [] whereArgs = {String.valueOf(idContacto)};
        database.delete(EquiposContract.ContactoEntry.TABLE_NAME,
                EquiposContract.ContactoEntry.COLUMN_ID+" = ?",
                whereArgs);



        //estod debemos tener para cualquier transancion
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }

    public Equiposdb leercontacot(long idE){

        //enves de modo escritura es lectura
        Equiposdb edb = null;
        SQLiteDatabase database = openReadable();
        String query = "SELECT "+
                EquiposContract.ContactoEntry.COLUMN_ID+
                ", " + EquiposContract.ContactoEntry.COLUMN_NAME+
                ", " + EquiposContract.ContactoEntry.COLUMN_MODELO+
                ", " + EquiposContract.ContactoEntry.COLUMN_TAMAÑO+
                ", " + EquiposContract.ContactoEntry.COLUMN_PROCESADOR+
                " FROM " + EquiposContract.ContactoEntry.TABLE_NAME+
                " WHERE "+
                EquiposContract.ContactoEntry.COLUMN_ID + " =  ?" ;
        //en el casi si la sentencia tiene una interrogacion
        String [] whereArgs = {String.valueOf(idE)};

        Cursor cursor= database.rawQuery(query, whereArgs);
        long id;
        String nombre;
        String modelo;
        String tamaño;
        String procesador;

        if(cursor.moveToFirst()){
          id= cursor.getLong(cursor.getColumnIndex(EquiposContract.ContactoEntry.COLUMN_ID));
          nombre= cursor.getString(cursor.getColumnIndex(EquiposContract.ContactoEntry.COLUMN_NAME));
          modelo= cursor.getString(cursor.getColumnIndex(EquiposContract.ContactoEntry.COLUMN_MODELO));
          tamaño= cursor.getString(cursor.getColumnIndex(EquiposContract.ContactoEntry.COLUMN_TAMAÑO));
          procesador= cursor.getString(cursor.getColumnIndex(EquiposContract.ContactoEntry.COLUMN_PROCESADOR));


          edb = new Equiposdb(nombre,modelo,tamaño,procesador);
          edb.setId(id);


        }
        cursor.close();
        close(database);
        return edb;
    }
    public ArrayList<Equiposdb>leerContactos(){
        ArrayList<Equiposdb>listaContactos = new ArrayList<Equiposdb>();


        //OBTENEMOS LA TRASCRIPCION DE LOS DATOS EN MODO ESCRITURA
        SQLiteDatabase database = openReadable();

        String query = "SELECT "+
                EquiposContract.ContactoEntry.COLUMN_ID+
                ", " + EquiposContract.ContactoEntry.COLUMN_NAME+
                ", " + EquiposContract.ContactoEntry.COLUMN_MODELO+
                ", " + EquiposContract.ContactoEntry.COLUMN_TAMAÑO+
                ", " + EquiposContract.ContactoEntry.COLUMN_PROCESADOR+
                " FROM " + EquiposContract.ContactoEntry.TABLE_NAME;
        Cursor cursor= database.rawQuery(query, null);
        Equiposdb e = null;
        long id;
        String nombre;
        String email;
        String modelo;
        String tamaño;
        String procesador;
        if(cursor.moveToFirst()){
            do{
                id= cursor.getLong(cursor.getColumnIndex(EquiposContract.ContactoEntry.COLUMN_ID));
                nombre= cursor.getString(cursor.getColumnIndex(EquiposContract.ContactoEntry.TABLE_NAME));
                modelo= cursor.getString(cursor.getColumnIndex(EquiposContract.ContactoEntry.COLUMN_MODELO));
                tamaño= cursor.getString(cursor.getColumnIndex(EquiposContract.ContactoEntry.COLUMN_TAMAÑO));
                procesador= cursor.getString(cursor.getColumnIndex(EquiposContract.ContactoEntry.COLUMN_PROCESADOR));

                e = new Equiposdb(nombre,modelo,tamaño,procesador);
                e.setId(id);

                listaContactos.add(e);



            }while (cursor.moveToFirst());{

            }
        }

        close(database);
        return listaContactos;
    }

}
