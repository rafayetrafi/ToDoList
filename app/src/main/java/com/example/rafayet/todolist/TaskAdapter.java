package com.example.rafayet.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {

    ArrayList<Task> taskList;
    Context context;
    DataSource dataSource;

    //TextView tvName, tvMobileNo, tvPosition, tvEmail;

    ImageView imageView;

    TaskAdapter(Context context, ArrayList<Task> taskList) {
        super(context, R.layout.row_view, taskList);
        this.taskList = taskList;
        this.context = context;
        dataSource = new DataSource(context);
    }

    static class ViewHolder {
        private TextView tvName;
        private TextView tvDescription;
        private TextView tvCategory;
        private TextView tvDate;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;


        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_view, null);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvTaskName);
            viewHolder.tvCategory = (TextView) convertView.findViewById(R.id.tvCategory);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(taskList.get(position).getName());

        Category category = dataSource.getCategory(taskList.get(position).getCategoryId());

        if (category != null) {
            //viewHolder.tvCategory.setText(category.getName().toString());

            viewHolder.tvCategory.setText(category.getName());
        }

        viewHolder.tvDescription.setText(taskList.get(position).getDescription());
        viewHolder.tvDate.setText(Utility.getDateFromInt(taskList.get(position).getDate()));


        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.parseColor("#F5D0A9"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#CEF6E3"));
        }


        return convertView;
    }
}

