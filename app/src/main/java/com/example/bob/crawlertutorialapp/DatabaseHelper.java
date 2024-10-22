package com.example.bob.crawlertutorialapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="CinemaApp.db";

    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE FILM( TITLU TEXT, ORA TEXT, DATA TEXT, POST TEXT,PRIMARY KEY ( TITLU, ORA, DATA) )");
        db.execSQL("CREATE TABLE CINEMATOGRAF( DENUMIRE TEXT, ADRESA TEXT, LINK TEXT, LATITUDINE TEXT, LONGITUDINE TEXT,PRIMARY KEY ( DENUMIRE) )");
        db.execSQL("CREATE TABLE USER( EMAIL TEXT PRIMARY KEY NOT NULL," +
                "NUME TEXT, PAROLA TEXT)");

        db.execSQL("CREATE TABLE WATCHLIST( ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "EMAIL_USER TEXT, TITLU_FILM TEXT, ORA_FILM TEXT, DATA_FILM TEXT)");

//        String sql =
//                "INSERT or replace INTO USER (EMAIL,NUME,PAROLA) VALUES('ciopecalina@gmail.com','Ciopec Alina-Mihaela','parola123')" ;
//        db.execSQL(sql);

        String sqlCinema =
                "INSERT or replace INTO CINEMATOGRAF (DENUMIRE, ADRESA, LINK, LATITUDINE, LONGITUDINE) VALUES" +
                        "('CINEMA CITY COTROCENI','Adresă: Bulevardul General Vasile Milea 4, București 061344','https://www.zilesinopti.ro/locuri/587/cinema-city-cotroceni','44.430602','26.052030')," +
                        "('CINEMA CITY COTROCENI (SALA VIP)','Adresă: Bulevardul General Vasile Milea 4, București 061344','https://www.zilesinopti.ro/locuri/588/cinema-city-cotroceni-sala-vip','44.430602','26.052030')," +
                        "('CINEMA CITY SUN PLAZA','Adresă: Calea Văcărești 391, București 040069','https://www.zilesinopti.ro/locuri/589/cinema-city-sun-plaza','44.396175064705815','26.122441291809086')," +
                        "('CINEMA CITY PARK LAKE','Adresă: Park Lake Shopping center, Strada Liviu Rebreanu 4, București','https://www.zilesinopti.ro/locuri/9519/cinema-city-park-lake','44.420741','26.149408')," +
                        "('CINEMA CITY PARK LAKE VIP','Adresă: Park Lake Shopping center, Strada Liviu Rebreanu 4, București','https://www.zilesinopti.ro/locuri/10885/cinema-city-park-lake-vip','44.420741','26.149408')," +
                        "('CINEMA CITY MEGA MALL','Adresă: Bulevardul Pierre de Coubertin 3-5, București 021901','https://www.zilesinopti.ro/locuri/7843/cinema-city-mega-mall','44.440750970508674','26.152149438858032')," +
                        "('CINEMA CITY MEGA MALL 4DX','Adresă: Bulevardul Pierre de Coubertin 3-5, București 021901','https://www.zilesinopti.ro/locuri/8004/cinema-city-mega-mall-4dx','44.440750970508674','26.152149438858032')," +
                        "('IMAX','Adresă: Bulevardul General Vasile Milea 4, București 061344','https://www.zilesinopti.ro/locuri/2/imax','44.430602','26.052030')," +

                        "('GRAND CINEMA & MORE','Adresă: Șoseaua București-Ploiești 42D, București 013696','https://www.zilesinopti.ro/locuri/781/grand-cinema-and-more','44.506496','26.090260')," +
                        "('GRAND CINEMA & MORE - SALA GRAND ULTRA','Adresă: Șoseaua București-Ploiești 42D, București 013696','https://www.zilesinopti.ro/locuri/784/grand-cinema-and-more-sala-grand-ultra','44.506496','26.090260')," +
                        "('GRAND CINEMA & MORE - SALA GRAND EPIKA','Adresă: Șoseaua București-Ploiești 42D, București 013696','https://www.zilesinopti.ro/locuri/3849/grand-cinema-and-more-sala-grand-epika','44.506496','26.090260')," +
                        "('GRAND CINEMA & MORE - GRAND STUDIOS','Adresă: Șoseaua București-Ploiești 42D, București 013696','https://www.zilesinopti.ro/locuri/3619/grand-cinema-and-more-grand-studios','44.506496','26.090260')," +
                        "('GRAND CINEMA & MORE - GRAND STUDIOS 2','Adresă: Șoseaua București-Ploiești 42D, București 013696','https://www.zilesinopti.ro/locuri/3817/grand-cinema-and-more-grand-studios-2','44.506496','26.090260')," +

                        "('MOVIEPLEX CINEMA','Adresă: Bulevardul Timișoara 26 Plaza Romania, etaj 2','https://www.zilesinopti.ro/locuri/3/movieplex-cinema','44.4280571','26.0343096')," +
                        "('HOLLYWOOD MULTIPLEX','Adresă: București Mall, Etaj 2, Calea Vitan 55-59, București 031281','https://www.zilesinopti.ro/locuri/1/hollywood-multiplex','44.4198737','26.1258525')," +
                        "('HAPPY CINEMA','Adresă: Liberty Center, Etaj 2, Strada Progresului 151-171, București 050696','https://www.zilesinopti.ro/locuri/10775/happy-cinema','44.415736','26.0798699')," +
                        "('CINEPLEXX TITAN','Adresă: Iris Shopping Center, Etaj 1, București 032455','https://www.zilesinopti.ro/locuri/12172/cineplexx-titan','44.41931402530711','26.178703308105472');";

                        db.execSQL(sqlCinema);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS FILM");
        db.execSQL("DROP TABLE IF EXISTS USER");
        db.execSQL("DROP TABLE IF EXISTS WATCHLIST");
        db.execSQL("DROP TABLE IF EXISTS CINEMATOGRAF");

        onCreate(db);
    }

    public boolean insertUser(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        long result=0;

        ContentValues values= new ContentValues();
        values.put("EMAIL", user.getEmail());
        values.put("NUME", user.getNume());
        values.put("PAROLA",user.getParola());

        result=db.insert("USER", null, values);
        if (result==1){
            return false ;
        }
        else {return true;
        }
    }

    public boolean insertFilm(Film film){
        SQLiteDatabase db=this.getWritableDatabase();
        long result=0;

        ContentValues values= new ContentValues();
        values.put("TITLU", film.getTitlu());
        values.put("ORA", film.getOra());
        values.put("DATA",film.getData());
        values.put("POST", film.getPostTV());
        result=db.insert("FILM", null, values);
        if (result==1){
            return false ;
        }
        else {return true;
        }
    }

    public boolean insertWatchlist(Watchlist w){
        SQLiteDatabase db=this.getWritableDatabase();
        long result=0;

        ContentValues values= new ContentValues();
        values.put("EMAIL_USER", w.getEmail_user());
        values.put("TITLU_FILM", w.getTitlu_film());
        values.put("ORA_FILM", w.getOra_film());
        values.put("DATA_FILM", w.getData_film());
        result=db.insert("WATCHLIST", null, values);
        if (result==1){
            return false ;
        }
        else {return true;
        }
    }

    public Cursor selectAllFilm(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM FILM", null);
        return c;
    }
    public Cursor selectAllWatchlist(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM WATCHLIST", null);
        return c;
    }

    public Cursor selectAllUser(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM USER", null);
        return c;
    }

    public Cursor selectAllCinema(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM CINEMATOGRAF", null);
        return c;
    }

    public Cursor getWatchlist(String email){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT TITLU_FILM,ORA_FILM,DATA_FILM FROM WATCHLIST WHERE EMAIL_USER=?",new String[]{email});
        return c;
    }

    public Cursor getUser(String email){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT PAROLA FROM USER WHERE EMAIL=?",new String[]{email});
        return c;
    }
    public Cursor getNumeUser(String email){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT NUME FROM USER WHERE EMAIL=?",new String[]{email});
        return c;
    }

    public Cursor getPostTv(String titlu,String ora,String data){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT POST FROM FILM WHERE TITLU=? AND DATA=? AND ORA=?",new String[]{titlu, data, ora});
        return c;
    }

    public void deleteAllFilm()
    {   SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("delete from "+"FILM" );
    }

    public void deleteAllUser()
    {   SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("delete from "+"USER" );
    }

    public void deleteAllWatchlist()
    {   SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("delete from "+"WATCHLIST" );
    }
    public void deleteWatchlist(String titlu)
    {   SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("delete from WATCHLIST WHERE TITLU_FILM=?",
                new String[]{titlu});
    }
    public void deleteFilm(String titlu)
    {   SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("delete from FILM WHERE TITLU=?",
                new String[]{titlu});
    }

    public void deleteAllCinema()
    {   SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("delete from "+"CINEMATOGRAF" );
    }

}
