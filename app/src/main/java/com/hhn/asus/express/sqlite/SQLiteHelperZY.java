package com.hhn.asus.express.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2018/11/2 0002.
 */

public class SQLiteHelperZY extends SQLiteOpenHelper {
    private static final int DB_VERSION=3;
    public static String DB_NAME="express.db";
    public static final String U_GoodsInfo="express";
    private static SQLiteHelperZY dbHelper;
    public SQLiteHelperZY(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+U_GoodsInfo +"("
        +"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        +"userName VARCHAR,"  //用户名
        +"goodsId VARCHAR,"  //订单id
        +"detailed_description VARCHAR,"//描述TV"

        +"dTitle VARCHAR,"   //标题
        +"image VARCHAR,"   //图片
        +"comment VARCHAR," //评论区
        +"price VARCHAR,"   //价格
        +"suishou BOOLEAN," //随手
        +"kuaidi BOOLEAN,"  //快递

        +"userId VARCHAR"
        +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ U_GoodsInfo);
            onCreate(db);
        }
    public static SQLiteHelperZY getInstance(Context context) {

        if (dbHelper == null) { //单例模式
            dbHelper = new SQLiteHelperZY(context);
        }
        return dbHelper;
    }
}
