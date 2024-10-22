package com.example.bob.crawlertutorialapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    DatabaseHelper db= new DatabaseHelper(this);

private Button btnLogIn, gif;
private EditText etEmailLogIn, etParolaLogIn;
private TextView tvInscriere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        gif=findViewById(R.id.gif);

        btnLogIn=findViewById(R.id.btnLogIn);
        etEmailLogIn=findViewById(R.id.tvLogInEmail);
        etParolaLogIn=findViewById(R.id.tvLogInParola);
        tvInscriere=findViewById(R.id.tvInscriere);

        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GifActivity.class);
                startActivity(intent);
            }
        });

        tvInscriere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =etEmailLogIn.getText().toString();
                String parola =etParolaLogIn.getText().toString();
                  if (email.isEmpty() || parola.isEmpty()) {
                      Toast.makeText(getApplicationContext(), "Completati toate campurile!", Toast.LENGTH_LONG).show();
                  }else {

                      Cursor c = db.getUser(email);
                      if (c.moveToFirst()){
                          c.moveToFirst();
                          String parolaBD=c.getString(0);
                          c.close();

                          if (parola.equals(parolaBD)){
                              Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                              intent.putExtra("email",email);
                              startActivity(intent);
                          }else {

                              Toast.makeText(getApplicationContext(), "Parola incorecta!", Toast.LENGTH_LONG).show();
                          }
                      }else {
                          Toast.makeText(getApplicationContext(), "Nu exista cont pentru acest email!", Toast.LENGTH_LONG).show();
                      }

                  }

            }
        });
    }
}
