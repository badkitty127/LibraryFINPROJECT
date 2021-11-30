package id.ac.ub.library;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] daftar;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static MainActivity ma;

    @Override
    protected void onCreate (Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent inte = new Intent (MainActivity.this, CatatBuku.class);
                Log.d("11", "seharusnya tidak error");
                startActivity(new Intent (MainActivity.this, CatatBuku.class));
            }
        });

        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList ();

    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM buku", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }

        ListView01 = (ListView) findViewById(R.id.listView);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar [arg2];
                final CharSequence[] dialogitem = {"Lihat Data Buku", "Update Data Buku", "Hapus Buku"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), LihatBuku.class);
                                i.putExtra ("judul_buku", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), UpdateBuku.class);
                                in.putExtra("judul_buku", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from buku where judul_buku = '" + selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();

            }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();

    }

    @Override
    public  boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is parent.
        getMenuInflater().inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.actions) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}