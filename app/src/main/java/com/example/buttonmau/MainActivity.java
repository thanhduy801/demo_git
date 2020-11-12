package com.example.buttonmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import static android.R.color.black;

public class MainActivity extends AppCompatActivity{

    Button btncolor,btnsave ;
    LinearLayout bgr;
    Integer a;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ
        bgr = (LinearLayout) findViewById(R.id.bgr_color);
        btncolor = (Button) findViewById(R.id.btn_color);
        btnsave = (Button) findViewById(R.id.btn_save);


        initPreferences();  //gọi initPreferences

        //gọi hàm lấy background đã lưu trước đó
        maubgr();

        //Bắt sự kiện khi click nút ĐỔI MÀU
        btncolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = Integer.valueOf(randomColor());
                bgr.setBackgroundColor(a);
            }
        });


        //Bắt sự kiện khi click nút SAVE
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("DATA",a);
                editor.commit();
            }
        });
    }


    //hàm lấy màu background đã save, để hiển thị lên background khi bật app trở lại
    public void maubgr(){
        if(sharedPreferences.getInt("DATA", 0)==0){

        }else {
            int a = sharedPreferences.getInt("DATA", 0);
            bgr.setBackgroundColor(a);
        }
}

    //Hàm random tạo màu background
    public int randomColor()
    {
        Random random= new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256),
                random.nextInt(256));
    }
}