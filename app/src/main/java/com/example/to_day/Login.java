package com.example.to_day;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText userName, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameText = userName.getText().toString();
                String passwordText = password.getText().toString();
                if (userNameText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    //Fire off Query
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(userNameText, passwordText);
                            if (userEntity == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),"Invalid credentials",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                String name = userEntity.userName;
                                startActivity(new Intent(
                                        Login.this, ToDayHome.class)
                                        .putExtra("name", name));
                            }
                        }
                    }).start();
                }
            }
        });
    }
}