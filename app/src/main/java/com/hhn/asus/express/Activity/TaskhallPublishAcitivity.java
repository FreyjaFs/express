package com.hhn.asus.express.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhn.asus.express.R;
import com.hhn.asus.express.bean.GoodsBean;
import com.hhn.asus.express.untils.AnalysisUtils;
import com.hhn.asus.express.untils.DBUtils;
import com.hhn.asus.express.untils.GetId;

public class TaskhallPublishAcitivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_user;
    private ImageView iv_user;
    //连接GoodsInfo
    private TextView tv_price;
    private TextView tv_goods_title;
    private TextView tv_detail_description;
    private TextView tv_GoodsID;

    private RelativeLayout title_bar;
    private EditText et_Dtitle;
    private EditText et_Detailed_description;
    private EditText et_price;
    private Button btn_suishou, btn_subTask;
    private Button btn_kuaidi;
    private LinearLayout ll_1;
    private String content, title;
    private int flag;
    private int suishou = 0, kuaidi = 0;
    private String price;
    private String Dtitle, Detailed_description;
    private String GoodsID;
    private Activity mContext;
    private LayoutInflater mInflater;
    private String spGoodsId;
    private String spUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity context = this;
        mContext = context;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskhall_publish_acitivity);

        //为之后将Layout转化为view时用
        mInflater = LayoutInflater.from(mContext);
        initView();
    }

    //布局初始化
    private void initView() {
        flag = getIntent().getIntExtra("flag", 0);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        et_Dtitle = (EditText) findViewById(R.id.et_Dtitle);
        et_Detailed_description = (EditText) findViewById(R.id.et_Detailed_description);
        btn_suishou = (Button) findViewById(R.id.btn_suishou);
        btn_kuaidi = (Button) findViewById(R.id.btn_kuaidi);
        ll_1 = (LinearLayout) findViewById(R.id.ll_1);
        btn_subTask = (Button) findViewById(R.id.btn_subTask);
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        et_price = (EditText) findViewById(R.id.et_price);
        tv_GoodsID = (TextView) findViewById(R.id.tv_GoodsID);
        tv_goods_title = (TextView) findViewById(R.id.tv_main_title);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_detail_description = (TextView) findViewById(R.id.tv_detail_description);
        title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(content)) {
            et_Detailed_description.setText(content);
            et_Detailed_description.setSelection(content.length());
        }
        contentListener();

        /**
         *
         */
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskhallPublishAcitivity.this.finish();
            }
        });
        btn_suishou.setOnClickListener(this);
        btn_kuaidi.setOnClickListener(this);
        //提交发布按钮
        btn_subTask.setOnClickListener(new View.OnClickListener() {
            @Override
            //保存
            public void onClick(View v) {
                getEditString();
                Intent data = new Intent();
                if (!TextUtils.isEmpty(Detailed_description) && !TextUtils.isEmpty(Dtitle)) {

                    data.putExtra("Detailed_description", Detailed_description);
                    data.putExtra("Dtitle", Dtitle);
                    data.putExtra("price", price);
                    data.putExtra("GoodsID", GoodsID);

                    Toast.makeText(TaskhallPublishAcitivity.this, "发布成功",
                            Toast.LENGTH_SHORT).show();
                    Toast.makeText(TaskhallPublishAcitivity.this, GoodsID, Toast.LENGTH_SHORT).show();

                    saveGoodsInfo(Detailed_description, Dtitle, price, GoodsID);
                    data.putExtra("newGoodsId", GoodsID);
                    data.setClass(TaskhallPublishAcitivity.this, GoodsInfosActivity.class);
                    TaskhallPublishAcitivity.this.finish();
                    startActivity(data);

                    //发布成功转到任务界面，关闭发布界面

                } else if (TextUtils.isEmpty(Detailed_description)) {
                    Toast.makeText(TaskhallPublishAcitivity.this, "标题和订单描述不能为空",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(Dtitle)) {
                    Toast.makeText(TaskhallPublishAcitivity.this, "标题和订单描述不能为空",
                            Toast.LENGTH_SHORT).show();

                }
//                switch (flag){
//                    case 1:
//                        if (!TextUtils.isEmpty(DdContent)){
//                            data.putExtra("nickName",DdContent);
//                            setResult(RESULT_OK,data);
//                            Toast.makeText(TaskhallPublishAcitivity.this,"发布成功",Toast.LENGTH_SHORT).show();
//                            TaskhallPublishAcitivity.this.finish();
//                        }
//                        break;
//                    case 2:
//                        if (!TextUtils.isEmpty(DdContent)){
//                            data.putExtra("快递描述",DdContent);
//                            setResult(RESULT_OK,data);
//                            Toast.makeText(TaskhallPublishAcitivity.this,"保存成功",Toast.LENGTH_SHORT).show();
//                            TaskhallPublishAcitivity.this.finish();
//                        }else{
//                            Toast.makeText(TaskhallPublishAcitivity.this,"签名不能为空",Toast.LENGTH_SHORT).show();
//                        }
//                        break;w
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_suishou:
                Toast.makeText(TaskhallPublishAcitivity.this, "点击了随手",
                        Toast.LENGTH_SHORT).show();
                suishou = 1;

                break;
            case R.id.btn_kuaidi:
                Toast.makeText(TaskhallPublishAcitivity.this, "点击了快递",
                        Toast.LENGTH_SHORT).show();
                kuaidi = 1;
                break;
            case R.id.et_Detailed_description:

                //测试点击发布
//            case R.id.btn_subTask:
//                Toast.makeText(TaskhallPublishAcitivity.this,"点击了发布",
//                        Toast.LENGTH_SHORT).show();
//                Intent data = new Intent();
//                String etContent = et_Detailed_description.getText().toString();
//                switch (flag){
//                    case 1:
//                    if (!TextUtils.isEmpty(etContent)){
//                            data.putExtra("nickName",etContent);
//                        setResult(RESULT_OK,data);
//                        Toast.makeText(TaskhallPublishAcitivity.this,"保存成功",
//                                Toast.LENGTH_SHORT).show();
//                        TaskhallPublishAcitivity.this.finish();
//                    }
//                    break;
//                    case 2:
//                        if (!TextUtils.isEmpty(etContent)){
//                            data.putExtra("signature",etContent);
//                            setResult(RESULT_OK,data);
//                            Toast.makeText(TaskhallPublishAcitivity.this,"保存成功",
//                                    Toast.LENGTH_SHORT).show();
//                            TaskhallPublishAcitivity.this.finish();
//                        }else{
//                            Toast.makeText(TaskhallPublishAcitivity.this,"签名不能为空",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                        break;
//                }
//                break;
        }
    }
    //设置输入的文本长度

    private void contentListener() {
        //任务描述字数限制
        et_Detailed_description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = et_Detailed_description.getText();
                int len = editable.length();//输入的文本的长度
                if (len > 0) {
//                    iv_delete.setVisibility(View.VISIBLE);
                } else {
//                    iv_delete.setVisibility(View.GONE);
                }
                if (len > 100) {
                    int selEndIndex = Selection.getSelectionEnd(editable);
                    String str = editable.toString();
                    //截取新字符串
                    String newStr = str.substring(0, 100);
                    et_Detailed_description.setText(newStr);
                    editable = et_Detailed_description.getText();
                    //新字符串的长度
                    int newLen = editable.length();
                    //旧光标位置超过新字符串的长度
                    if (selEndIndex > newLen) {
                        selEndIndex = editable.length();
                    }
                    //设置新光标所在的位置
                    Selection.setSelection(editable, selEndIndex);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //标题字数限制
        et_Dtitle.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = et_Dtitle.getText();
                int len = editable.length();//输入的文本的长度
                if (len > 0) {
//                    iv_delete.setVisibility(View.VISIBLE);
                } else {
//                    iv_delete.setVisibility(View.GONE);
                }
                if (len > 20) {
                    int selEndIndex = Selection.getSelectionEnd(editable);
                    String str = editable.toString();
                    //截取新字符串
                    String newStr = str.substring(0, 20);
                    et_Dtitle.setText(newStr);
                    editable = et_Dtitle.getText();
                    //新字符串的长度
                    int newLen = editable.length();
                    //旧光标位置超过新字符串的长度
                    if (selEndIndex > newLen) {
                        selEndIndex = editable.length();
                    }
                    //设置新光标所在的位置
                    Selection.setSelection(editable, selEndIndex);
                    Toast.makeText(TaskhallPublishAcitivity.this, "输入标题不能大于20字节", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //获取控件的数据
    private void getEditString() {
        Detailed_description = et_Detailed_description.getText().toString().trim();
        Dtitle = et_Dtitle.getText().toString().trim();
        price = et_price.getText().toString().trim();
        //生成商品id
        GoodsID = GetId.generateShortUuid();
    }

    //保存控件内容
    private void saveGoodsInfo(String Detailed_description, String Dtitle,
                               String price, String GoodsID) {
        //GoodInfo第一个参数是指存放的（文件名.xml）,方法的第二个参数指定文件的操作模式

//        SharedPreferences sp = getSharedPreferences("GoodsInfo", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString(Detailed_description, Detailed_description);
//        editor.putString(Dtitle, Dtitle);
//        editor.putString(price, price);
//        editor.putString(GoodsID, GoodsID);
//        editor.commit();
        //保存到数据库
        spUserName = AnalysisUtils.readLoginUserName(this);
        GoodsBean bean = null;
        if (bean == null) {
            bean = new GoodsBean();
            bean.userName = spUserName;
            bean.dtitle = Dtitle;
            bean.detailed_description = Detailed_description;
            bean.price = price;
            bean.goodsId = GoodsID;
            DBUtils.getInstance(this).saveGoodsInfo(bean);
        }

    }
}