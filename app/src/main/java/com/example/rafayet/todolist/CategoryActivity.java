package com.example.rafayet.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CategoryActivity extends AppCompatActivity {

    EditText edCategory;
    DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        dataSource = new DataSource(this);

        edCategory = (EditText) findViewById(R.id.editTextCategory);
    }

    public void saveCategory(View view) {
        Category category = new Category();

        category.setName(edCategory.getText().toString());

        boolean flag = dataSource.addCategory(category);

        Toast.makeText(this, " Catagory save successfully", Toast.LENGTH_SHORT).show();

        if (flag == true) {
            CategoryActivity.this.finish();
        } else {

        }
    }
}
