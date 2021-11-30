package id.ac.ub.library;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "buku.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO Auto-generade method stub
        String sql = "create table buku (no_buku integer primary key, judul_buku text null, tgl_terbit text null, penulis text null, penerbit text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL (sql);
        sql = "INSERT INTO buku (no_buku, judul_buku, tgl_terbit, penulis, penerbit) VALUES ('01', 'BUKU EKONOMI', '1 November 2021', 'Ahmad', 'Gramedia');";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        //TODO Auto-generade method stub
    }
}
