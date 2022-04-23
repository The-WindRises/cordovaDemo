package com.booway.sqlitehelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class SqliteHelper extends CordovaPlugin {

    private SQLiteDatabase writableDatabase;
    private final String DB_NAME = "cordovaDemo.db";
    private final String TABLE_NAME = "t_user";
    private final String C_ID = "id";
    private final String C_USERNAME = "username";
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
                insertInfo(args);
                break;
            case "delete":
                deleteInfo(args);
                break;
            case "update":
                updateInfo(args);
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
        final String createUserTable = "create table " + TABLE_NAME + " (" + C_ID + " integer primary key autoincrement, " + "" + C_USERNAME + " text," + C_BIRTHDAY + " text)";
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
        try {
            Cursor cursor = writableDatabase.rawQuery("select * from " + TABLE_NAME, null);
            JSONArray jsonArray = new JSONArray();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);  //获取字段值
                String username = cursor.getString(cursor.getColumnIndex(C_USERNAME));
                String birthday = cursor.getString(cursor.getColumnIndex(C_BIRTHDAY));
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(C_ID, id);  //第1参数为键名，第2参数为键值
                jsonObject.put(C_USERNAME, username);
                jsonObject.put(C_BIRTHDAY, birthday);
                jsonArray.put(jsonObject);
            }
            callback.success(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void insertInfo(JSONArray args) {  //插入记录
        if (writableDatabase == null) {
            callback.error("请初始化数据库");
            return;
        }
        try {
            JSONArray jsonArray = (JSONArray) args.get(0);
            String name = jsonArray.get(0).toString();
            String birthday = jsonArray.get(1).toString();
            ContentValues values = new ContentValues();
            values.put(C_USERNAME, name);
            values.put(C_BIRTHDAY, birthday);
            long rowid = writableDatabase.insert(TABLE_NAME, null, values);
            if (rowid == -1) Log.i("myDbDemo", "数据插入失败！");
            else Log.i("myDbDemo", "数据插入成功！" + rowid);
            callback.success("添加成功");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void deleteInfo(JSONArray args) {
        if (writableDatabase == null) {
            callback.error("请初始化数据库");
            return;
        }
        try {
            JSONArray jsonArray = (JSONArray) args.get(0);
            String where = C_ID + "=" + jsonArray.get(0).toString();
            int i = writableDatabase.delete(TABLE_NAME, where, null);
            if (i > 0) {
                callback.success("删除成功");
            } else {
                callback.error("删除失败");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void updateInfo(JSONArray args) {
        if (writableDatabase == null) {
            callback.error("请初始化数据库");
            return;
        }
        try {
            JSONArray jsonArray = (JSONArray) args.get(0);

            String id = jsonArray.get(0).toString();
            String name = jsonArray.get(1).toString();
            String birthday = jsonArray.get(2).toString();

            ContentValues values = new ContentValues();
            values.put(C_USERNAME, name);
            values.put(C_BIRTHDAY, birthday);
            String where = C_ID + "=" + id;
            int i = writableDatabase.update(TABLE_NAME, values, where, null);
            if (i > 0) {
                callback.success("修改成功");
            } else {
                callback.error("修改失败");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
