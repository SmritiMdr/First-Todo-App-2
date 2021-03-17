package com.example.firsttodoapp2;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public static final String TODO_INDEX = "com.example.android.firsttodoapp.extra.todo_index";
    private static final String TAG=MainActivity.class.getSimpleName();

    private String[] todos;
    private int todoIndex;

    private TextView todoTextView;
    private Button prevButton;
    private Button detailsButton;
    private Button nextButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate()");

        if (savedInstanceState != null) {
            todoIndex = savedInstanceState.getInt(TODO_INDEX, 0);
        }

        Resources res = getResources();
        todos = res.getStringArray(R.array.todos);
        Log.d(TAG, "Length of the array" + todos.length);

        todoTextView = findViewById(R.id.todo_tv);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.previous_button);
        todoTextView.setText(todos[todoIndex]);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoIndex = ++todoIndex % todos.length;
                Log.d(TAG, "todoIndex:" + todoIndex);
                todoTextView.setText(todos[todoIndex]);

                //Log.d(TAG,"todoIndex:"+todoIndex);

            }

        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoIndex = (todoIndex - 1) % todos.length;
                Log.d(TAG, "todoIndex:" + todoIndex);
                todoTextView.setText(todos[todoIndex]);

                //Log.d(TAG,"todoIndex:"+todoIndex);
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(TODO_INDEX,todoIndex);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        todoIndex = savedInstanceState.getInt(TODO_INDEX);
        todoTextView.setText(todos[todoIndex]);
    }

    public void onClickDetail(View view) {
        Intent intent = new Intent(this, DetailActivitys.class) ;
        intent.putExtra(TODO_INDEX, todoIndex);
        startActivity(intent);
    }

}