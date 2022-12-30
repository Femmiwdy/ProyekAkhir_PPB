package com.example.musicplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    SharedPreferences shp;
    SharedPreferences.Editor shpEditor;
    EditText edtUserId, edtPassword;
    Button btnLogin;
    TextView txtInfo;
    TextView loginDisini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        edtUserId = findViewById(R.id.edtUserid);
        edtPassword = findViewById(R.id.edtPassword);

        edtUserId.setText("");
        edtPassword.setText("");
        btnLogin = findViewById(R.id.btnLogin);
        loginDisini = findViewById(R.id.textView8);
        txtInfo = findViewById(R.id.txtInfo);

        shp = getSharedPreferences("myPreferences", MODE_PRIVATE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUserId.getText().toString().equals("") || edtPassword.getText().toString().equals(""))
                    txtInfo.setText("Please insert userid and password");
                else
                    DoLogin(edtUserId.getText().toString(), edtPassword.getText().toString());
            }
        });

        loginDisini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void DoLogin(String userid, String password) {
        try {
            if (password.equals("Android3am")) {
                if (shp == null)
                    shp = getSharedPreferences("myPreferences", MODE_PRIVATE);

                shpEditor = shp.edit();
                shpEditor.putString("name", userid);
                shpEditor.commit();

                Intent i = new Intent(Register.this, MainActivity.class);
                startActivity(i);
                finish();
            } else
                txtInfo.setText("Invalid Credentails");
        } catch (Exception ex) {
            txtInfo.setText(ex.getMessage().toString());
        }
    }



}
