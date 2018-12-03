package com.hhn.asus.express.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhn.asus.express.R;
import com.hhn.asus.express.bean.GoodsBean;
import com.hhn.asus.express.untils.DBUtils;

public class GoodsInfosActivity extends AppCompatActivity {

    private TextView tv_user;
    private ImageView iv_user;
    private TextView tv_price;
    private TextView tv_detail_description;
    private TextView tv_GoodsID;
    private Activity mContext;
    private LayoutInflater mInflater;
    private TextView detail_title_bar;
    private String spGoodsId;
    private TextView tv_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail_info);
        Intent intent = getIntent();
        spGoodsId = intent.getStringExtra("newGoodsId");
        initView();
        initData();
    }

    private void initData() {
        GoodsBean bean = null;
        bean = DBUtils.getInstance(this).getGoodsInfo(spGoodsId);
//        bean = DBUtils.getInstance(this).getUserInfo(spUserName);

        if (bean == null) {
            //无法获取应该是服务器炸了
//            bean=new GoodsBean();
//            bean.userName = spGoodsId;
//            bean.dtitle = "";
//            bean.detailed_description = "";
//            bean.price="";
//            bean.goodsId= "";
//            DBUtils.getInstance(this).saveGoodsInfo(bean);
        } else setValue(bean);
    }

    private void initView() {
        tv_user = (TextView) findViewById(R.id.tv_user);
        iv_user = (ImageView) findViewById(R.id.iv_user);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_detail_description = (TextView) findViewById(R.id.tv_detail_description);
        tv_GoodsID = (TextView) findViewById(R.id.tv_GoodsID);
        detail_title_bar = (TextView)findViewById(R.id.tv_main_title);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsInfosActivity.this.finish();
            }
        });
    }

    public void setValue(GoodsBean bean) {
        tv_user.setText(bean.userName);
        tv_price.setText(bean.price);
        tv_detail_description.setText(bean.detailed_description);
        tv_GoodsID.setText(bean.goodsId);
        detail_title_bar.setText(bean.dtitle);
    }

    private boolean readLoginStatus() {
        SharedPreferences sp = mContext.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }

    private String new_info_content;
    private String new_info;
    private String new_info1;
    private String new_info2;
    private String new_info3;
    private String new_info4;
    private String new_info5;
    private String new_info6;
    private String new_info7;

    /**
     * @param requestCode requestCode和startActivityForResult中的requestCode相对应
     * @param resultCode  resultCode和Intent是由子Activity通过其setResult()方法返回
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_OK:
                if (data != null) {
                    new_info = data.getStringExtra("Detailed_description");
                    new_info1 = data.getStringExtra("Dtitle");
                    new_info2 = data.getStringExtra("price");
                    new_info3 = data.getStringExtra("GoodsID");
                    if (TextUtils.isEmpty(new_info)) {
                        return;
                    } else {
                        tv_detail_description.setText(new_info);
                        DBUtils.getInstance(GoodsInfosActivity.this).updateUserInfo("Detailed_description", new_info,
                                spGoodsId);
                    }
                    if (TextUtils.isEmpty(new_info1)) {
                        return;
                    } else {
                        tv_detail_description.setText(new_info1);
                        new_info_content = tv_detail_description.getText().toString();
                        DBUtils.getInstance(GoodsInfosActivity.this).updateUserInfo("Detailed_description", new_info1,
                                new_info_content);
                    }

                }


//            String userName=data.getStringExtra("userName");
//            String detailed_description=data.getStringExtra("detailed_description");
//            String price=data.getStringExtra("price");
//            String GoodsID=data.getStringExtra("goodsId");
//            String dtitle=data.getStringExtra("dtitle");
////            if(!TextUtils.isEmpty(userName)){
////                tv_user.setText(userName);
////                tv_detail_description
////                tv_GoodsID
////                et_user_name.setSelection(userName.length());
////            }
//            if(!TextUtils.isEmpty(GoodsID)&&!TextUtils.isEmpty(detailed_description)) {
//                tv_GoodsID.setText(GoodsID);
//                tv_detail_description.setText(detailed_description);
//            }
//            if(!TextUtils.isEmpty(price)) {
//                tv_price.setText(price);
//            }
//            if(!TextUtils.isEmpty(dtitle)) {
////                tv_title.setText(dtitle);
//            }
//            if(!TextUtils.isEmpty(userName)) {
//                tv_user.setText(userName);
//            }


//            if (TextUtils.isEmpty(new_info1)){
//                return;
//            }else {
//                tv_detail_description.setText(new_info1);
//                DBUtils.getInstance(GoodsInfosActivity.this).updateUserInfo("Detailed_description",new_info,
//                        spGoodsId);
//            }
//            if (TextUtils.isEmpty(new_info)){
//                return;
//            }else {
//                tv_detail_description.setText(new_info);
//                DBUtils.getInstance(GoodsInfosActivity.this).updateUserInfo("Detailed_description",new_info,
//                        spGoodsId);
//            }


        }
    }
}

