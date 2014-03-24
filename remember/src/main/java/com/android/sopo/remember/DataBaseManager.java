package com.android.sopo.remember;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {

    static final String TABLE_NAME = "registro";
    static final String _id = "_id";
    static final String _nombre = "_nombre";
    static final String _tipo = "_tipo";
    static final String _dia = "_dia";
    static final String _hora = "_hora";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + " ("
            + _id + " integer primary key autoincrement,"
            + _nombre + " text not null,"
            + _tipo + " text,"
            + _dia + " text,"
            + _hora + " String);";

    private DBHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context)
    { //contructor
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues generarContentValues(String nombre, String tipo, String dia, String hora)
    {
        ContentValues valores = new ContentValues();
        valores.put(_nombre,nombre);
        valores.put(_tipo,tipo);
        valores.put(_dia,dia);
        valores.put(_hora,hora);

        return valores;
    }

    public void insertar(String nombre, String tipo, String dia, String hora)
    {
        db.insert(TABLE_NAME, null, generarContentValues(nombre,tipo,dia,hora));
    }

    public void eliminar(String nombre)
    {
        db.delete(TABLE_NAME, _nombre+"=?", new String[]{nombre});
    }

    public Cursor cargarCursorRegistro()
    {
        String[] campos = new String[]{_id,_nombre,_tipo};
        return db.query(TABLE_NAME, campos, null, null, null, null, null);
    }
}
