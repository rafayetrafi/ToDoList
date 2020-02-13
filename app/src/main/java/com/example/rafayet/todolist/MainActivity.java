package com.example.rafayet.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listViewTask;
    DataSource dataSource;
    ArrayList<Task> tasks;
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataSource = new DataSource(this);

        listViewTask = (ListView) findViewById(R.id.listViewTask);

        registerForContextMenu(listViewTask);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        this.fillTaskListView();
    }

    private void fillTaskListView() {
        tasks = dataSource.getAllTask();

        taskAdapter = new TaskAdapter(this, tasks);
        listViewTask.setAdapter(taskAdapter);
    }

    private void fillTaskListView(int days) {
        tasks = dataSource.getAllTask(days);

        taskAdapter = new TaskAdapter(this, tasks);
        listViewTask.setAdapter(taskAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_add_task) {
            Intent taskActivity = new Intent(MainActivity.this, TaskActivity.class);
            startActivity(taskActivity);
            return true;
        }

        if (id == R.id.action_today) {
            this.fillTaskListView(0);
            return true;
        }

        if (id == R.id.action_next7days) {
            this.fillTaskListView(7);
            return true;
        }

        if (id == R.id.action_next30days) {
            this.fillTaskListView(30);
            return true;
        }

        if (id == R.id.action_all) {
            this.fillTaskListView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.fillTaskListView();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_floating_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.id_delete:
                Task task = tasks.get(menuInfo.position);
                String name = task.getName();

                dataSource.deleteTask(task.getId());
                tasks.remove(menuInfo.position);
                taskAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), name + " deleted successfully", Toast.LENGTH_LONG);
                return true;

            case R.id.id_edit:
                Task editTask = tasks.get(menuInfo.position);

                Intent taskActivity = new Intent(MainActivity.this, TaskActivity.class);
                taskActivity.putExtra(Task.COL_ID, editTask.getId());
                startActivity(taskActivity);
                return true;
            default:
                return super.onContextItemSelected(item);

        }
    }
}
