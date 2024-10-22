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

public class SignUpActivity extends AppCompatActivity {
    DatabaseHelper db= new DatabaseHelper(this);

    private Button btnSignUp;
    private EditText etEmailSignUp, etParolaSignUp1,etParolaSignUp2, etSignUpNume ;
    private TextView tvConectare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp=findViewById(R.id.btnSignUpInscriere);
        etSignUpNume=findViewById(R.id.tvSignUpNume);
        etEmailSignUp=findViewById(R.id.tvSignUpEmail);
        etParolaSignUp1=findViewById(R.id.tvSignUpParola1);
        etParolaSignUp2=findViewById(R.id.tvSignUpParola2);
        tvConectare=findViewById(R.id.tvSignUpConectare);

        tvConectare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LogInActivity.class);
                startActivity(intent);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =etEmailSignUp.getText().toString();
                String nume =etSignUpNume.getText().toString();
                String parola1 =etParolaSignUp1.getText().toString();
                String parola2 =etParolaSignUp2.getText().toString();

                Cursor c = db.getUser(email);
                if (c.moveToFirst()) {
                    Toast.makeText(getApplicationContext(), "Acest cont exista deja!", Toast.LENGTH_LONG).show();
                    c.close();
                }else {

                if (email.isEmpty() || parola1.isEmpty()|| parola2.isEmpty()|| nume.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Datele sunt incorecte!", Toast.LENGTH_LONG).show();
                }else if (!(parola1.equals(parola2))){
                    Toast.makeText(getApplicationContext(), "Parolele difera!", Toast.LENGTH_LONG).show();
                }else {

                    User user= new User(nume, email, parola1);
                    db.insertUser(user);

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }

            }}
        });
    }
}
