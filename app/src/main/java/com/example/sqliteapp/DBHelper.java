package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Medicamentos(nome TEXT primary key, frequencia TEXT, dosagem TEXT, horario TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Medicamentos");
    }

    public Boolean insertData(String nome, String frequencia, String dosagem, String horario) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("frequencia", frequencia);
        contentValues.put("dosagem", dosagem);
        contentValues.put("horario", horario);
        long result = DB.insert("Medicamentos", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateData(String nome, String frequencia, String dosagem, String horario) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("frequencia", frequencia);
        contentValues.put("dosagem", dosagem);
        contentValues.put("horario", horario);

        Cursor cursor = DB.rawQuery("Select * from Medicamentos where nome = ?", new String[] {nome});
        if(cursor.getCount() > 0) {
            long result = DB.update("Medicamentos", contentValues, "nome=?", new String[] {nome});
            return result != -1;
        } else {
            return false;
        }

    }

    public Boolean deleteData(String nome) {

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Medicamentos where nome = ?", new String[] {nome});
        if(cursor.getCount() > 0) {
            long result = DB.delete("Medicamentos", "name = ?", new String[] {nome});
            return result != -1;
        } else {
            return false;
        }

    }

    public Cursor getData() {

        SQLiteDatabase DB = this.getWritableDatabase();

        return DB.rawQuery("Select * from Medicamentos", null);
    }
}
