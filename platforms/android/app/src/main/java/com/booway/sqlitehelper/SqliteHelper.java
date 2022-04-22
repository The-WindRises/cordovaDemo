package com.booway.sqlitehelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;
import com.google.gson.Gson;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class echoes a string called from JavaScript.
 */
public class SqliteHelper extends CordovaPlugin {

    private SQLiteDatabase writableDatabase;
    private final String DB_NAME = "cordovaDemo.db";
    private final String TABLE_NAME = "t_user";
    private final String C_ID = "id";
    private final String C_USERNAME = "username";
    private final String C_AGE = "age";
    private final String C_BIRTHDAY = "birthday";

    private CallbackContext callback;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        callback = callbackContext;
        switch (action) {
            case "initDB":
                initDB(args);
                break;
            case "createTable":
                createTable(args);
                break;
            case "insert":
                break;
            case "delete":
                break;
            case "update":
                break;
            case "query":
                queryAll();
                break;
        }
        return true;
    }


    private void initDB(JSONArray args) {
        if (writableDatabase == null) {
            BoowaySQLiteHelper boowaySQLiteHelper = new BoowaySQLiteHelper(cordova.getContext(), DB_NAME, null, 1);
            writableDatabase = boowaySQLiteHelper.getWritableDatabase();
            callback.success("初始化成功");
        } else {
            callback.error("数据库已初始化");
        }
    }

    private void createTable(JSONArray args) {
        final String createUserTable = "create table " + TABLE_NAME + " (" + C_ID + " integer primary key autoincrement, " + "" + C_USERNAME + " text," + C_AGE + " integer," + C_BIRTHDAY + " text)";
        if (writableDatabase != null) {
            writableDatabase.execSQL(createUserTable);
            callback.success("创建用户表成功");
        } else {
            callback.error("请初始化数据库");
        }

    }

    public void queryAll() {
        if (writableDatabase == null) {
            callback.error("请初始化数据库");
            return;
        }
        Cursor cursor = writableDatabase.rawQuery("select * from " + TABLE_NAME, null);
        ArrayList<Object> objects = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);  //获取字段值
            String username = cursor.getString(cursor.getColumnIndex(C_USERNAME));
            int age = cursor.getInt(cursor.getColumnIndex(C_AGE));
            String birthday = cursor.getString(cursor.getColumnIndex(C_BIRTHDAY));
            Map listItem = new HashMap<String, Object>(); //必须在循环体里新建
            listItem.put(C_ID, id);  //第1参数为键名，第2参数为键值
            listItem.put(C_USERNAME, username);
            listItem.put(C_AGE, age);
            listItem.put(C_BIRTHDAY, birthday);
            objects.add(listItem);   //添加一条记录
        }
        Gson gson = new Gson();
        callback.success(gson.toJson(objects));
    }

    public int getRecordsNumber() {  //返回数据表记录数
        Cursor cursor = writableDatabase.rawQuery("select * from " + TABLE_NAME, null);
        return cursor.getCount();
    }

    public void insertInfo(String name, int age, String birthday) {  //插入记录
        ContentValues values = new ContentValues();
        values.put(C_USERNAME, name);
        values.put(C_AGE, age);
        values.put(C_BIRTHDAY, birthday);
        long rowid = writableDatabase.insert(TABLE_NAME, null, values);
        if (rowid == -1) Log.i("myDbDemo", "数据插入失败！");
        else Log.i("myDbDemo", "数据插入成功！" + rowid);
    }

    public void deleteInfo(String selId) {  //删除记录
        String where = "id=" + selId;
        int i = writableDatabase.delete(TABLE_NAME, where, null);
        if (i > 0) Log.i("myDbDemo", "数据删除成功！");
        else Log.i("myDbDemo", "数据未删除！");
    }

    public void updateInfo(String name, int age, String selId) {  //修改记录
        //方法中的第三参数用于修改选定的记录
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        String where = "_id=" + selId;
        int i = writableDatabase.update(TABLE_NAME, values, where, null);

        //上面几行代码的功能可以用下面的一行代码实现
        //myDb.execSQL("update friends set name = ? ,age = ? where _id = ?",new Object[]{name,age,selId});

        if (i > 0) Log.i("myDbDemo", "数据更新成功！");
        else Log.i("myDbDemo", "数据未更新！");
    }

}
