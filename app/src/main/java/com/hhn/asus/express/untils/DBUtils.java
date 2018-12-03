package com.hhn.asus.express.untils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.hhn.asus.express.Activity.GoodsInfosActivity;
import com.hhn.asus.express.bean.GoodsBean;
import com.hhn.asus.express.sqlite.SQLiteHelperZY;

import static android.content.ContentValues.TAG;


/**
 * Created by Administrator on 2018/10/19 0019.
 */

public class DBUtils {
    private static DBUtils instance = null;
    private static SQLiteHelperZY helper;
    private static SQLiteDatabase db;
    public DBUtils(Context context){
        helper=new SQLiteHelperZY(context);
        db=helper.getWritableDatabase();
    }
    public static DBUtils getInstance(Context context){
        if (instance==null){
            instance=new DBUtils(context);
        }
        return instance;
    }
    /**
     * 保存个人资料信息
     */
    public void saveGoodsInfo(GoodsBean bean){
        ContentValues cv=new ContentValues();
        cv.put("goodsId",bean.goodsId);
        cv.put("userName",bean.userName);
        cv.put("detailed_description",bean.detailed_description);
        cv.put("dTitle",bean.dtitle);
        cv.put("price",bean.price);
        db.insert(SQLiteHelperZY.U_GoodsInfo,null,cv);
    }
    /**
     * 获取个人资料信息
     */
    //getUserInfo 的数据库语句WHERE 搜寻goodsId对应的列(现为userName = skr '"+GoodsId+"'"
    public GoodsBean getGoodsInfo(String GoodsId){


        String sql="SELECT * FROM "+ SQLiteHelperZY.U_GoodsInfo + " WHERE goodsId=?";
        Cursor cursor=db.rawQuery(sql,new String[]{GoodsId});
        GoodsBean bean=null;
        int io= cursor.getCount();
        while(cursor.moveToNext()){
            bean=new GoodsBean();
            bean.userName = cursor.getString(cursor.getColumnIndex("userName"));
            bean.goodsId = cursor.getString(cursor.getColumnIndex("goodsId"));
            bean.detailed_description = cursor.getString(cursor.getColumnIndex("detailed_description"));
            bean.dtitle = cursor.getString(cursor.getColumnIndex("dTitle"));
            bean.price=cursor.getString(cursor.getColumnIndex("price"));

        }
        cursor.close();

        Log.d(TAG, "getGoodsInfo: sdasd");
        return bean;
    }
    /**
     * 修改个人资料
     */
    public void updateUserInfo(String key,String value,String GoodsId){
        ContentValues cv=new ContentValues();
        cv.put(key,value);
        db.update(SQLiteHelperZY.U_GoodsInfo,cv,"userName=?",new String[]{GoodsId});

    }
}
