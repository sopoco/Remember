package com.android.sopo.remember;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class lista_Activity extends Activity {

    DataBaseManager manager;
    Cursor cursor;
    ListView lvRegistro;
    SimpleCursorAdapter cursorAdapter;
    ContextMenu menu;
    private View v;
    private ContextMenu.ContextMenuInfo menuInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_);

        manager = new DataBaseManager(this);
        lvRegistro = (ListView) findViewById(R.id.lvRegistro);

        String[] from = new String[]{manager._nombre,manager._tipo};
        int[] to = new int[]{android.R.id.text1,android.R.id.text2};

        cursor = manager.cargarCursorRegistro();
        cursorAdapter = new SimpleCursorAdapter(getBaseContext(),android.R.layout.two_line_list_item, cursor,from,to,0);
        lvRegistro.setAdapter(cursorAdapter);

        registerForContextMenu(lvRegistro);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Acciones");
        menu.add(Menu.NONE, 0, 0, "Eliminar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        String nombre = cursor.getString(1); //0 es id, 1 es nombre, 2 es tipo

        try
        {
            if (item.getTitle() == "Eliminar")
            {
                manager.eliminar(nombre);
                String[] from = new String[]{manager._nombre,manager._tipo};
                int[] to = new int[]{android.R.id.text1,android.R.id.text2};

                cursor = manager.cargarCursorRegistro();
                cursorAdapter = new SimpleCursorAdapter(getBaseContext(),android.R.layout.two_line_list_item, cursor,from,to,0);
                lvRegistro.setAdapter(cursorAdapter);


                Toast.makeText(getApplicationContext()," Eliminada", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Opción no valida", Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }
        catch (Exception e)
        {
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lista_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
