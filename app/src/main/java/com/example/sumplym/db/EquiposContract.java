package com.example.sumplym.db;

import android.provider.BaseColumns;

public class EquiposContract {

    public static abstract class ContactoEntry implements BaseColumns {
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "NOMBRE";
        public static final String COLUMN_MODELO = "EMAIL";
        public static final String COLUMN_TAMAÑO = "TAMAÑO";
        public static final String COLUMN_PROCESADOR = "PROCESADOR";
        public static final String TABLE_NAME = "EQUIPOS11";


    }
}

