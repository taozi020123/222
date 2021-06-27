package com.sict.sqlite.Fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.sict.sqlite.Adapter.fragment1adapter;
import com.sict.sqlite.R;
import com.sict.sqlite.info.fragment1info;

import java.util.ArrayList;

public class Fragment1 extends Fragment implements View.OnClickListener{
    private EditText jobname;
    private CheckBox checkBox3;
    private EditText date;
    private EditText hour;
    private EditText minute;
    private RecyclerView recyclerView;
    private Button ADD;
    private Button UPDATE;
    private Button DELETE;
   // MySQLiteHelper helper;
    //private SQLiteDatabase db;
    MyHelper myHelper;
    private ArrayList<fragment1info>mList;
    private fragment1adapter adapter;
    private RecyclerView fragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1, container, false);
        jobname = (EditText)view.findViewById(R.id.jobname);
        checkBox3 = (CheckBox)view.findViewById(R.id.checkBox3);
        date = (EditText) view.findViewById(R.id.date);
        hour = (EditText) view.findViewById(R.id.hour);
        minute = (EditText) view.findViewById(R.id.minute);
        recyclerView = (RecyclerView)view. findViewById(R.id.recyclerView);
        ADD = (Button) view.findViewById(R.id.ADD);
        UPDATE = (Button) view.findViewById(R.id.UPDATE);
        DELETE = (Button) view.findViewById(R.id.DELETE);
        ADD.setOnClickListener(this);
        UPDATE.setOnClickListener(this);
        DELETE.setOnClickListener(this);
        myHelper=new MyHelper(getActivity());
        return view;

    }

   @Override
    public void onClick(View v) {

        String jobname1,date1,hour1,minute1,hourstype;
        SQLiteDatabase db;
       ContentValues values ;
        switch (v.getId()){
            case R.id.ADD:
                jobname1=jobname.getText().toString();
                db=myHelper.getWritableDatabase();
                values=new ContentValues();
                values.put("jobname", jobname.getText().toString());
                values.put("date", date.getText().toString());
                values.put("hour", hour.getText().toString());
                values.put("minute", minute.getText().toString());
                db.insert("diary", null, values);
               Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_LONG);

           Cursor cursor= db.query("diary",null,null,null,null,null,null);
            if(cursor.moveToFirst()){
               // do{}
                while(cursor.moveToNext());
                fragment1info fragment2=new fragment1info();
                fragment2.setJobname(cursor.getString(cursor.getColumnIndex("jobname")));
                fragment2.setDate(cursor.getInt(cursor.getColumnIndex("date")));
                fragment2.setHour(cursor.getInt(cursor.getColumnIndex("date")));
                fragment2.setMinute(cursor.getInt(cursor.getColumnIndex("date")));
                mList.add(fragment2);
            }
            adapter=new fragment1adapter(this.getActivity(),mList);
            fragment.setAdapter(adapter);
           adapter.notifyDataSetChanged();
                recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
           cursor.close();
                break;
            case R.id.UPDATE:
                db=myHelper.getWritableDatabase();
                values=new ContentValues();
                values.put("date", date.getText().toString());
                values.put("hour", hour.getText().toString());
                values.put("minute", minute.getText().toString());
                db.update("diary",values,"jobname1=?",new String[]{jobname.getText().toString()});
                Toast.makeText(getActivity(), "更新成功", Toast.LENGTH_SHORT).show();
                db.close();
                break;
            case R.id.DELETE:
                db=myHelper.getWritableDatabase();
                db.delete("diary",null,null);
                Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                db.close();
                break;
        }

    }


    private class MyHelper extends SQLiteOpenHelper{
        public MyHelper(Context context){
            super(context,"admin.db",null,1);}


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table diary (id integer PRIMARY KEY AUTOINCREMENT,"
                    +"jobname VARCHAR(20),date VARCHAR(20),hour VARCHAR(20),minute VARCHAR(20))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        jobname = getActivity().findViewById(R.id.jobname2);
//        date = getActivity().findViewById(R.id.date2);
//        fragment=getActivity().findViewById(R.id.recyclerView);
//        helper = new MySQLiteHelper(getActivity(), "diary", null, 1);
//        ADD = (Button) getActivity().findViewById(R.id.ADD);
//        ADD.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                db = helper.getWritableDatabase();
//                Insert();
//                Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
//                jobname.setText("");
//                date.setText("");
//                hour.setText("");
//                minute.setText("");
//                db.close();
//            }
//        });
//        UPDATE = (Button) getActivity().findViewById(R.id.UPDATE);
//        UPDATE.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                db = helper.getWritableDatabase();
//                //查询数据
//                Query();
//
//            }
//        });
//    DELETE=getActivity().findViewById(R.id.DELETE);
//    DELETE.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            db=helper.getWritableDatabase();
//               db.delete("information",null,null);
//               Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
//               db.close();
//
//        }
//    });
//    }
//        public void Query(){
//             db=helper.getWritableDatabase();
//            Cursor cursor= db.query("diary",null,null,null,null,null,null);
//            if(cursor.moveToFirst()){
//               // do{}
//                while(cursor.moveToNext());
//                fragment1info fragment2=new fragment1info();
//                fragment2.setJobname(cursor.getString(cursor.getColumnIndex("jobname")));
//                fragment2.setDate(cursor.getInt(cursor.getColumnIndex("date")));
//                fragment2.setHour(cursor.getInt(cursor.getColumnIndex("date")));
//                fragment2.setMinute(cursor.getInt(cursor.getColumnIndex("date")));
//                mList.add(fragment2);
//            }
//            adapter=new fragment1adapter(this.getActivity(),mList);
//            fragment.setAdapter(adapter);
//            adapter.notifyDataSetChanged();
//            cursor.close();
//        }
//        public void Insert(){
//            ContentValues values = new ContentValues();
//            values.put("jobname", jobname.getText().toString());
//            values.put("date", date.getText().toString());
//            values.put("hour", hour.getText().toString());
//            values.put("minute", minute.getText().toString());
//            db.insert("diary", null, values);
//        }
//
 // }


