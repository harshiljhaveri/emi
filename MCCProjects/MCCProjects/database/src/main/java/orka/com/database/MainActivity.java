package orka.com.database;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.Random;

import androidx.room.Room;

public class MainActivity extends AppCompatActivity {


    private AppDatabase mainDB;

    private RecyclerView rv;
    private TodoAdapter adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                new MaterialDialog.Builder(MainActivity.this)
                        .title("Add")
                        .content("Add a New Todo")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input("", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, final CharSequence input) {

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mainDB.todoDao().insertAll(new Todo(new Random().nextInt(),input.toString()));
                                    }
                                }).run();

                                Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                updateDB();
                            }
                        }).show();



            }
        });


        mainDB = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();


        adap = new TodoAdapter();
        adap.mData = new ArrayList<Todo>();

        adap.ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i = (int) v.getTag();

                mainDB.todoDao().delete(adap.mData.get(i));
                updateDB();

            }
        };


        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv.setAdapter(adap);



        updateDB();




    }

    private void updateDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                adap.mData = mainDB.todoDao().getAll();
                if(adap.mData == null) {
                    adap.mData = new ArrayList<Todo>();
                }
                Log.d("MSG BItCH", "run: "+ adap.mData.size());
                adap.notifyDataSetChanged();
            }
        }).run();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
