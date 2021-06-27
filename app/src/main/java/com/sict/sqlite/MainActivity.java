package com.sict.sqlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etname;
    private EditText etpassword;
    private CheckBox checkBox;
    private Button register;
    private Button denglu;
    private static final int REGISER_CODE=1;
    int requestCode=-1;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        db=openOrCreateDatabase("musicplayer.db",MODE_PRIVATE,null);
        db.execSQL("create table if not exists users(name varchar(50),pswd varchar(50),primary key(name))");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(requestCode!=REGISER_CODE)
        readShardPreferences();
    }



    private void initView() {
        etname = (EditText) findViewById(R.id.editTextTextPersonName);
        etpassword = (EditText) findViewById(R.id.editTextTextPassword);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        register = (Button) findViewById(R.id.button);
        denglu = (Button) findViewById(R.id.button2);
        register.setOnClickListener(this);
        denglu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Intent intent=new Intent(MainActivity.this,register.class);
                //startActivity(intent_register);
                startActivityForResult(intent,REGISER_CODE);
                break;
            case R.id.button2:
                submit();
                break;
        }
    }


    private void submit() {
        String name=etname.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        String pswd=etpassword.getText().toString().trim();
        if(TextUtils.isEmpty(pswd)){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
        return;
        }
        try {//核验用户输入的信息是否可以与数据库中的用户名密码核对成功，根据用户名获取数据库中的用户名密码
            Cursor cursor=db.rawQuery("select pswd from users where name=?",new String[]{name});
            String pswd_check=null;//取出的结果放在这个字符串中
            //判断取出的数据有几行（1或0行两种可能）
            if(cursor.getCount()==1){//跳转到第一行,取出第一行第一列的数据
                cursor.moveToFirst();
                pswd_check=cursor.getString(0);
                if(pswd_check.equals(pswd)){
                    //记住密码，写入Shardpreferences文件
                    writeShardPreferences(name,pswd);
                    Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "用户名、密码不一致", Toast.LENGTH_SHORT).show();
                }


            }else{
                Toast.makeText(this, "用户不存在", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "登陆失败", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.requestCode=-1;
        if(resultCode == Activity.RESULT_OK){
            //获取传回来的数据，即data
            if(data!=null) {
                //填充两个输入框为注册页面传回来的用户名、密码
                etname.setText(data.getStringExtra("name"));
                etpassword.setText(data.getStringExtra("pswd"));
                this.requestCode=requestCode;
    }

        }

    }
    //记住密码
    private void writeShardPreferences(String name, String pswd) {
        SharedPreferences preferences=getSharedPreferences("MusicUserRecord",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        if(checkBox.isChecked()==true){
            editor.putString("name",name);
            editor.putString("pswd",pswd);
            editor.putBoolean("keep",true);
        }else{
            editor.putBoolean("keep",false);
        }//保存编辑器中的内容
        editor.commit();
    }
    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
    private void readShardPreferences() {
        SharedPreferences preferences=getSharedPreferences("MusicUserRecord",MODE_PRIVATE);
        if(preferences.getBoolean("keep",false)){
            String name=preferences.getString("name","");
            String pswd=preferences.getString("pswd","");
            etname.setText(name);
            etpassword.setText(pswd);
            checkBox.setChecked(true );
        }else{
            etname.setText("");
            etpassword.setText("");
            checkBox.setChecked(false);
        }
    }
}