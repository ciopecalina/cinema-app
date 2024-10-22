package com.example.bob.crawlertutorialapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProgramCinemaActivity extends AppCompatActivity {
TextView tvIntroducere, tvContinut, tvSf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_cinema);

        tvIntroducere=findViewById(R.id.tvAnuntIntroducere);
        tvContinut=findViewById(R.id.tvAnuntContinut);
        tvSf=findViewById(R.id.tvAnuntSf);

        StringBuilder strIntroducere= new StringBuilder();
        strIntroducere.append("            ").append(getString(R.string.anunt_introducere));
        tvIntroducere.setText(strIntroducere);

        StringBuilder strContinut= new StringBuilder();
        strContinut.append("         ").append(getString(R.string.anunt_continut));
        tvContinut.setText(strContinut);

        StringBuilder strSf= new StringBuilder();
        strSf.append("            ").append("Va urma ...");
        tvSf.setText(strSf);
    }
}
