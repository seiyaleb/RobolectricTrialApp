package com.example.robolectrictrialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_length = findViewById(R.id.tv_length);
        EditText et_input = findViewById(R.id.et_input);

        //計算ボタンをタップ時
        findViewById(R.id.btn_calculation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //入力された文字数を表示
                tv_length.setText(et_input.getText().length() + "文字");
            }
        });

        //次へボタンをタップ時
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //WebViewActivity起動
                //URL情報を渡す
                Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
                intent.putExtra("url","https://qiita.com/leb397");
                startActivity(intent);
            }
        });
    }
}