package com.sict.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity implements View.OnClickListener{
    private EditText etname;
    private EditText etpassword;
    private EditText etpassword1;
    private Button register;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();//创建打开数据库
        register.setOnClickListener(this);
        db=openOrCreateDatabase("musicplayer.db",MODE_PRIVATE,null);
        db.execSQL("create table if not exists users(name varchar(50),pswd varchar(50),primary key(name))");
    }

    private void initView() {
        etname = (EditText) findViewById(R.id.editTextTextPersonName2);
        etpassword = (EditText) findViewById(R.id.editTextTextPassword2);
        etpassword1 = (EditText) findViewById(R.id.editTextTextPassword3);
        register = (Button) findViewById(R.id.button3);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name=etname.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        String pswd1=etpassword.getText().toString().trim();
        if(TextUtils.isEmpty(pswd1)){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        String pswd2=etpassword1.getText().toString().trim();
        if(TextUtils.isEmpty(pswd2)){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!pswd1.equals(pswd2)){
            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
        }else {
            try {
                //将用户名密码插入到数据库中
                db.execSQL("insert into users(name,pswd)values(?,?)",new String[]{name,pswd1});
                //使用Intent对象保存输入的用户名密码
                Intent date=getIntent();
                date.putExtra("name",name);
                date.putExtra("pswd",pswd1);
                setResult(RESULT_OK,date);
                finish();
            }catch (Exception e){
                Toast.makeText(this, "用户注册失败", Toast.LENGTH_SHORT).show();

            }

        }
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}