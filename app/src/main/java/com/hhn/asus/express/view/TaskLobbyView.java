package com.hhn.asus.express.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hhn.asus.express.R;

/**
 * Created by Light_Spirit3 on 2018/11/26.
 */

public class TaskLobbyView extends AppCompatActivity {

    private Activity tContext;
    private LayoutInflater tInflater;
    private View tCurrentView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(TaskLobbyView.this,"jkjkjkjkjkjkjkjk",Toast.LENGTH_SHORT).show();
        Button fab = (Button) findViewById(R.id.button2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TaskLobbyView.this,"jkjkjkjkjkjkjkjk",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public TaskLobbyView(Activity context){
        tContext=context;
        tInflater= LayoutInflater.from(tContext);
    }

    private void createView(){
        initView();
    }

    private void initView() {
        tCurrentView = tInflater.inflate(R.layout.activity_task_lobby,null);
    }

    public View getView(){
        if(tCurrentView == null){
            createView();
        }
        return tCurrentView;
    }

    public void showView(){
        if(tCurrentView == null){
            createView();
        }
        tCurrentView.setVisibility(View.VISIBLE);
    }
}
