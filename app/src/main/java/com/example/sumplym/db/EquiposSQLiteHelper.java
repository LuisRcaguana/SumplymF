package com.example.sumplym.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EquiposSQLiteHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME= "EQUIPOSDB";
    static final int VERSION_BD=4;

    static final String CREATE_TABLE_EQUIPOS =
            "CREATE TABLE "+ EquiposContract.ContactoEntry.TABLE_NAME+ "( "+
                    EquiposContract.ContactoEntry.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                    EquiposContract.ContactoEntry.COLUMN_NAME+" TEXT NOT NULL," +
                    EquiposContract.ContactoEntry.COLUMN_MODELO+" TEXT NOT NULL," +
                    EquiposContract.ContactoEntry.COLUMN_TAMAÑO+" TEXT NOT NULL," +
                    EquiposContract.ContactoEntry.COLUMN_PROCESADOR+" TEXT NOT NULL);";




    public EquiposSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_BD);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EQUIPOS);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EquiposContract.ContactoEntry.TABLE_NAME);
        onCreate(db);



    }
}
