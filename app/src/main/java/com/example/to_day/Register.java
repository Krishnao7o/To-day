package com.example.to_day;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText userName, password, confirmPassword;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        confirmPassword = findViewById(R.id.confirmpassword);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating user entity
                UserEntity userEntity = new UserEntity();
                userEntity.setUserName(userName.getText().toString());
                userEntity.setPassword(password.getText().toString());
                userEntity.setConfirmPassword(confirmPassword.getText().toString());


//                if (!confirmPassword.equals(password)) {
//                    Toast.makeText(getApplicationContext(),"Passwords do not match!", Toast.LENGTH_SHORT).show();
//                }
                if (validateInput(userEntity)) {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //Register User
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                } else {
                    Toast.makeText(getApplicationContext(),"Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean validateInput(UserEntity userEntity) {
        if (userEntity.getUserName().isEmpty() ||
                userEntity.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }
}