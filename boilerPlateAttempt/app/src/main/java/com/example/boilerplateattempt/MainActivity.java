package com.example.boilerplateattempt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up buttons to be used
        Button btnSumbit = (Button) findViewById(R.id.btnBoilerSubmit);
        Button btnChange = (Button) findViewById(R.id.btnChangeToTodo);

        final TextView idText = findViewById(R.id.tvID);
        final TextView messageText = findViewById(R.id.tvMessage);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent todoIntent = new Intent(MainActivity.this, AddToTodo.class);
                MainActivity.this.startActivity(todoIntent);

            }
        });

        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String URL1 = "https://mern-boilerplate-app2.herokuapp.com/api/putData";

                int idT = Integer.parseInt(idText.getText().toString());
                String mess = messageText.getText().toString();

                JSONObject obj =  new JSONObject();
                try {
                    obj.put("id", idT);
                    obj.put("message", mess);
                }
                catch(Exception e){

                }
                final String message = obj.toString();

                final JSONObject ret = com.example.boilerplateattempt.JsonIo.doJsonIo(URL1, message);

                //Show return value
                if (ret.toString()!= null)
                {
                    TextView tv = findViewById(R.id.tvBoilerSubmit);
                    tv.setText(ret.toString());
                }
                else
                {
                    TextView tv = findViewById(R.id.tvBoilerSubmit);
                    tv.setText("Ret was null");
                }


            }
        });


    }
}
