package com.azpe.cou.ejemplosdia6;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PreferenceActivity extends Activity {

    private SharedPreferences prfs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        prfs = getSharedPreferences("com.azpe.cou.ejemplosdia6.appreferences", MODE_PRIVATE);

        String fuente = prfs.getString("fontName", "");

        EditText txtFontName = (EditText)findViewById(R.id.txtFontName);
        txtFontName.setText(fuente);

        Button btnSaveFont = (Button) findViewById(R.id.btnSaveFont);
        btnSaveFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtFontName = (EditText)findViewById(R.id.txtFontName);
                SharedPreferences.Editor editor = prfs.edit();

                editor.putString("fontName", txtFontName.getText().toString());
                editor.commit();
            }
        });
    }
}
