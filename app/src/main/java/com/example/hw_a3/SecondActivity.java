package com.example.hw_a3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
    private static final int SELECT_IMAGE = 234;
    private ImageView imageView;
    private EditText editText;
    private Button button;
    private String image="MyImage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
        onClick();
    }

    private void onClick() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override           //gallery
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("Address", editText.getText().toString());
                intent.putExtra("image",image);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override //photo
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            image= data.getDataString();
            Uri uri = data.getData();
            imageView.setImageURI(uri);
        }
    }

    private void init() {
        imageView = findViewById(R.id.imageView2);
        editText = findViewById(R.id.editTextTextPersonName);
        button = findViewById(R.id.button);
    }
}