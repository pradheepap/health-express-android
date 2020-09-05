package com.sg.healthexpress.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import com.google.android.material.textfield.TextInputLayout;
import com.sg.healthexpress.services.HelloService;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.sg.healthexpress.welcome.MESSAGE";
    String msg = "Android : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(msg, "The onCreate() event");
    }

    public void getInfo(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        TextInputLayout editText =  findViewById(R.id.textField);
        String message = editText.getEditText().getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}