package com.example.boilerplateattempt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;


import org.json.JSONObject;

public class AddToTodo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_todo);

        //Setting up variables - RadioGroups
        final RadioGroup radioGroupP = findViewById(R.id.rbPriority);
        final RadioGroup radioGroupC = findViewById(R.id.rbCompleted);

        //Setting up buttons and then the TextViews to be used
        Button todoSubmit = (Button) findViewById(R.id.btnTodoSubmit);
        Button switchToBoiler = (Button) findViewById(R.id.btnChangeToBoiler);

        final TextView description = findViewById(R.id.tvDescription);
        final TextView responsible = findViewById(R.id.tvResponsible);


        switchToBoiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent boilerActivity = new Intent(AddToTodo.this, MainActivity.class);
                AddToTodo.this.startActivity(boilerActivity);


            }
        });

        todoSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String URL = "https://largeproject-mern-app.herokuapp.com/todos/add";

                //Find the values from the text views
                String descr = description.getText().toString();
                String respo = responsible.getText().toString();

                //Get value from Priority Button
                int radioIDP = radioGroupP.getCheckedRadioButtonId();
                RadioButton rbp = findViewById(radioIDP);
                String priority = rbp.getText().toString();

                //Get value from Completed Button
                int radioIDC = radioGroupC.getCheckedRadioButtonId();
                RadioButton rbc = findViewById(radioIDC);
                String tf = rbc.getText().toString();
                boolean trFa = tf.equals("True") ? true : false;

                //Create JSON object to send to L's code
                JSONObject obj =  new JSONObject();
                try {
                    obj.put("todo_description", descr);
                    obj.put("todo_responsible", respo);
                    obj.put("todo_priority",priority);
                    obj.put("todo_completed",trFa);
                }
                catch(Exception e){

                }
                final String message = obj.toString();

                //Send object to L's code
                final JSONObject ret = com.example.boilerplateattempt.JsonIo.doJsonIo(URL, message);

                //Show return value
                if (ret.toString()!= null)
                {
                    TextView tv = findViewById(R.id.textView2);
                    tv.setText(ret.toString());
                }
                else
                {
                    TextView tv = findViewById(R.id.textView2);
                    tv.setText("Ret was null");
                }
            }
        });
    }


}

