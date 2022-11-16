package com.example.mywallet;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TABLICA_PRIHOD = "TABLICA_PRIHOD";
    public static final String TABLICA_TROSAK = "TABLICA_TROSAK";
    public static final String STUPAC_KATEGORIJA = "KATEGORIJA";
    public static final String STUPAC_VRIJEDNOST = "VRIJEDNOST";
    public static final String STUPAC_BILJESKA = "BILJESKA";
    public static final String STUPAC_ID = "ID";
    public static final String STUPAC_ID_KORISNIKA = "ID_KORISNIKA";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "zapis.db", null, 1);
    }

    String createTablePrihod = "CREATE TABLE " + TABLICA_PRIHOD + " (" + STUPAC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STUPAC_KATEGORIJA + " TEXT, " + STUPAC_VRIJEDNOST + " INT, " + STUPAC_BILJESKA + " TEXT, "+ STUPAC_ID_KORISNIKA +" TEXT)";
    String createTableTrosak = "CREATE TABLE " + TABLICA_TROSAK + " (" + STUPAC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STUPAC_KATEGORIJA + " TEXT, " + STUPAC_VRIJEDNOST + " INT, " + STUPAC_BILJESKA + " TEXT, "+ STUPAC_ID_KORISNIKA +" TEXT)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTablePrihod);
        db.execSQL(createTableTrosak);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLICA_TROSAK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLICA_PRIHOD);

        onCreate(db);
    }

    public boolean addOnePrihod (PrihodModel prihodModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(STUPAC_KATEGORIJA, prihodModel.getKategorija());
        cv.put(STUPAC_VRIJEDNOST, prihodModel.getVrijednost());
        cv.put(STUPAC_BILJESKA, prihodModel.getBiljeska());
        cv.put(STUPAC_ID_KORISNIKA, 1);

        long insert = db.insert(TABLICA_PRIHOD, null, cv);
        if(insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean addOneTrosak (TrosakModel trosakModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(STUPAC_KATEGORIJA, trosakModel.getKategorija());
        cv.put(STUPAC_VRIJEDNOST, trosakModel.getVrijednost());
        cv.put(STUPAC_BILJESKA, trosakModel.getBiljeska());
        cv.put(STUPAC_ID_KORISNIKA, 1);

        long insert = db.insert(TABLICA_TROSAK, null, cv);
        if(insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public List<PrihodModel> getEveryone() {
        List<PrihodModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLICA_PRIHOD;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {
                int prihodID = cursor.getInt(0);
                String prihodKategorija = cursor.getString(1);
                int prihodVrijednost = cursor.getInt(2);
                String prihodBiljeska = cursor.getString(3);

                PrihodModel noviPrihod = new PrihodModel(prihodID, prihodKategorija, prihodVrijednost, prihodBiljeska, 1);
                returnList.add(noviPrihod);

            } while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<DataEntry> ReadDataToListPrihod() {
        List<DataEntry> returnList = new ArrayList<>();

        String query= "SELECT SUM("+STUPAC_VRIJEDNOST+"), "+STUPAC_KATEGORIJA+" FROM "+TABLICA_PRIHOD+" GROUP BY "+STUPAC_KATEGORIJA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                int vrijednost = cursor.getInt(0);
                String kategorija = cursor.getString(1);

                returnList.add(new ValueDataEntry(kategorija, vrijednost ));
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return returnList;
    }

    public List<DataEntry> ReadDataToListTrosak() {
        List<DataEntry> returnList = new ArrayList<>();

        String query= "SELECT SUM("+STUPAC_VRIJEDNOST+"), "+STUPAC_KATEGORIJA+" FROM "+TABLICA_TROSAK+" GROUP BY "+STUPAC_KATEGORIJA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                int vrijednost = cursor.getInt(0);
                String kategorija = cursor.getString(1);

                returnList.add(new ValueDataEntry(kategorija, vrijednost ));
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return returnList;
    }

    public List<PieEntry> ReadDataToListP() {
        List<PieEntry> returnList = new ArrayList<>();

        String query= "SELECT SUM("+STUPAC_VRIJEDNOST+"), "+STUPAC_KATEGORIJA+" FROM "+TABLICA_PRIHOD+" GROUP BY "+STUPAC_KATEGORIJA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                int vrijednost = cursor.getInt(0);
                String kategorija = cursor.getString(1);

                returnList.add(new PieEntry(vrijednost, kategorija));
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return returnList;
    }

    public List<PieEntry> ReadDataToListT() {
        List<PieEntry> returnList = new ArrayList<>();

        String query= "SELECT SUM("+STUPAC_VRIJEDNOST+"), "+STUPAC_KATEGORIJA+" FROM "+TABLICA_TROSAK+" GROUP BY "+STUPAC_KATEGORIJA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                int vrijednost = cursor.getInt(0);
                String kategorija = cursor.getString(1);

                returnList.add(new PieEntry(vrijednost, kategorija));
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return returnList;
    }



    public List<TrosakModel> getEveryone1() {
        List<TrosakModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLICA_TROSAK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {
                int trosakID = cursor.getInt(0);
                String trosakKategorija = cursor.getString(1);
                int trosakVrijednost = cursor.getInt(2);
                String trosakBiljeska = cursor.getString(3);

                TrosakModel noviTrosak = new TrosakModel(trosakID, trosakKategorija, trosakVrijednost, trosakBiljeska, 1);
                returnList.add(noviTrosak);

            } while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public int ukupniPrihod(){
        int total = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM("+STUPAC_VRIJEDNOST+") FROM "+TABLICA_PRIHOD, null);
        if(c.moveToFirst()){
            total=c.getInt(0);
        }
        return total;
    }

    public int ukupniTrosak(){
        int total = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM("+STUPAC_VRIJEDNOST+") FROM "+TABLICA_TROSAK, null);
        if(c.moveToFirst()){
            total=c.getInt(0);
        }
        return total;
    }

    Cursor readAllDataPrihod() {
        String query = "SELECT "+ STUPAC_KATEGORIJA + ", " + STUPAC_VRIJEDNOST + ", " + STUPAC_BILJESKA +", "+ STUPAC_ID + " FROM "+ TABLICA_PRIHOD;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor=db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllDataTrosak() {
        String query = "SELECT "+ STUPAC_KATEGORIJA + ", " + STUPAC_VRIJEDNOST + ", " + STUPAC_BILJESKA +", "+ STUPAC_ID +" FROM "+ TABLICA_TROSAK;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor=db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateDataPrihod(String row_id, String kategorija, int vrijednost, String biljeska){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STUPAC_KATEGORIJA, kategorija);
        cv.put(STUPAC_VRIJEDNOST, vrijednost);
        cv.put(STUPAC_BILJESKA, biljeska);
        cv.put(STUPAC_ID_KORISNIKA, 1);

        db.update(TABLICA_PRIHOD, cv, "ID=?", new String[]{row_id});
    }

    void updateDataTrosak(String row_id, String kategorija, int vrijednost, String biljeska){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STUPAC_KATEGORIJA, kategorija);
        cv.put(STUPAC_VRIJEDNOST, vrijednost);
        cv.put(STUPAC_BILJESKA, biljeska);
        cv.put(STUPAC_ID_KORISNIKA, 1);

        db.update(TABLICA_TROSAK, cv, "ID=?", new String[]{row_id});
    }

    void deletePrihod(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLICA_PRIHOD, "ID=?", new String[]{row_id});
    }

    void deleteTrosak(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLICA_TROSAK, "ID=?", new String[]{row_id});
    }
}



