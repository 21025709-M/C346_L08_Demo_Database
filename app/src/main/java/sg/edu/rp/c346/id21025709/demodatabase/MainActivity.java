package sg.edu.rp.c346.id21025709.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;
    ArrayAdapter<String> aa;
    ArrayList<Task> al;
    EditText etTask, etDate;
    boolean asc = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DBHelper db = new DBHelper(MainActivity.this);
//        db.insertTask("Submit RJ", "25 Apr 2021");

        btnInsert = findViewById(R.id.buttonInsert);
        btnGetTasks = findViewById(R.id.buttonGetTask);
        tvResults = findViewById(R.id.textViewReults);
        lv = findViewById(R.id.listView);
        etTask = findViewById(R.id.editTextTask);
        etDate = findViewById(R.id.editTextDate);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(etTask.getText().toString(), etDate.getText().toString());
                db.close();

            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                //listview
                DBHelper db2 = new DBHelper(MainActivity.this);
                al = db2.getTasks(asc);
                db2.close();
                asc = !asc;
                aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, al);
                lv.setAdapter(aa);
            }
        });

    }

}