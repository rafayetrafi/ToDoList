package com.example.rafayet.todolist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.view.View.OnClickListener;

public class TaskActivity extends AppCompatActivity implements OnClickListener {

    Spinner spCategory;
    ArrayList<Category> categories;
    EditText edTaskName, edCategory, edStatus, edDescription;
    TextView tvDate;
    DataSource dataSource;
    private DatePickerDialog taskDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    int taskId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        dataSource = new DataSource(this);
        categories = dataSource.getAllCategory();

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvDate.setInputType(InputType.TYPE_NULL);
        edTaskName = (EditText) findViewById(R.id.edTaskName);
        edDescription = (EditText) findViewById(R.id.edDescription);

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        tvDate.setText(dateFormatter.format(tomorrow));
        spCategory = (Spinner) findViewById(R.id.spCategory);

        this.fillCategoryList();
        setDateTimeField();

        Intent intent = getIntent();
        taskId = intent.getIntExtra(Task.COL_ID, -1);

        if (taskId > 0) {
            Task task = dataSource.getTask(taskId);

            edTaskName.setText(task.getName());
            edDescription.setText(task.getDescription());
            spCategory.setSelection(this.getCategoryIndex(task.getCategoryId()));

            tvDate.setText(Utility.getDateFromInt(task.getDate()));
        }
    }

    private void fillCategoryList() {
        ArrayAdapter<Category> arrayAdapterCategory = new ArrayAdapter<Category>(TaskActivity.this, android.R.layout.simple_spinner_item, categories);
        arrayAdapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(arrayAdapterCategory);
    }

    private int getCategoryIndex(int categoryId) {
        int index = 0;

        if (categories != null && categories.size() > 0) {
            for (Category cate : categories) {
                if (categoryId == cate.getId()) {
                    break;
                }
                index++;
            }
        }
        return index;
    }

    private void setDateTimeField() {
        tvDate.setOnClickListener(TaskActivity.this);
        Calendar newCalendar = Calendar.getInstance();
        taskDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tvDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }

    public void saveTask(View view) {
        boolean flag = false;
        Task task = new Task();

        Intent intent = getIntent();
        taskId = intent.getIntExtra(Task.COL_ID, -1);

        if (taskId >= 0) {
            task = dataSource.getTask(taskId);
        }

        task.setName(edTaskName.getText().toString());
        task.setDescription(edDescription.getText().toString());
        task.setDate(Utility.dateToInt(tvDate.getText().toString()));
        Category category = (Category) spCategory.getSelectedItem();
        task.setCategoryId(category.getId());

        if (taskId >= 0) {
            flag = dataSource.updateTask(taskId, task);
        } else {
            flag = dataSource.addTask(task);
        }

        if (flag == true) {
            TaskActivity.this.finish();
        } else {

        }
    }

    @Override
    public void onClick(View view) {
        if (view == tvDate) {
            taskDatePickerDialog.show();
        }
    }

    public void buttonAddCategory(View view) {
        Intent taskActivity = new Intent(TaskActivity.this, CategoryActivity.class);
        startActivity(taskActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.fillCategoryList();
    }
}
