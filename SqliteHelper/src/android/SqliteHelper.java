package com.booway.sqlitehelper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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
    private final String C_AGE = "age";
    private final String C_BIRTHDAY = "birthday";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        String message = args.getString(0);
        if ("initDB".equals(action)) {
            initDB(message, callbackContext);
            return true;
        }
        if ("createTable".equals(action)) {
            createTable(message, callbackContext);
            return true;
        }

        if (writableDatabase == null) {
            callbackContext.error("请创建数据库");
            return true;
        } else {
            String sql;
            switch (action) {
                case "insert":
                    sql = "INSERT INTO " + TABLE_NAME + " (" + C_ID + ", " + C_USERNAME + "," + C_AGE + "," + C_BIRTHDAY + ") \n"
                            + "VALUES (1, zhangsan, 17,20201010);";
                    writableDatabase.execSQL(sql);
                    break;
                case "delete":
                    break;
                case "update":
                    break;
                case "select":
                    break;
            }
        }
        return true;
    }


    private void initDB(String message, CallbackContext callbackContext) {
        if (writableDatabase == null) {
            BoowaySQLiteHelper boowaySQLiteHelper = new BoowaySQLiteHelper(cordova.getContext(), DB_NAME, null, 1);
            writableDatabase = boowaySQLiteHelper.getWritableDatabase();
            callbackContext.success("初始化成功");
        } else {
            callbackContext.error("数据库已创建");
        }
    }

    private void createTable(String message, CallbackContext callbackContext) {
        final String createUserTable = "create table " + TABLE_NAME + " (" + C_ID + " integer primary key autoincrement, " + "" + C_USERNAME + " text," + C_AGE + " integer," + C_BIRTHDAY + " text)";
        if (writableDatabase != null) {
            writableDatabase.execSQL(createUserTable);
            callbackContext.success("创建用户表成功");
        } else {
            callbackContext.error("请创建数据库");
        }

    }

}
