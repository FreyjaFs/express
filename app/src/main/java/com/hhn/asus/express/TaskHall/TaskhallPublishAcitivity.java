package com.hhn.asus.express.TaskHall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhn.asus.express.R;

public class TaskhallPublishAcitivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout title_bar;
    private EditText et_Dtitle;
    private View view;
    private EditText et_Detailed_description;
    private Button btn_suishou;
    private Button btn_kuaidi;
    private LinearLayout ll_1;
    private Button btn_subTask;
    private String content,title;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskhall_publish_acitivity);
        initView();
    }

    //布局初始化
    private void initView() {
        flag=getIntent().getIntExtra("flag",0);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        view = (View) findViewById(R.id.view);
        et_Dtitle = (EditText) findViewById(R.id.et_Dtitle);
        et_Detailed_description = (EditText) findViewById(R.id.et_Detailed_description);
        btn_suishou = (Button) findViewById(R.id.btn_suishou);
        btn_kuaidi = (Button) findViewById(R.id.btn_kuaidi);
        ll_1 = (LinearLayout) findViewById(R.id.ll_1);
        btn_subTask = (Button) findViewById(R.id.btn_subTask);
        title=getIntent().getStringExtra("title");
        content=getIntent().getStringExtra("content");
        if (!TextUtils.isEmpty(content)){
            et_Detailed_description.setText(content);
            et_Detailed_description.setSelection(content.length());
        }contentListener();

        btn_suishou.setOnClickListener(this);
        btn_kuaidi.setOnClickListener(this);
        btn_subTask.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_suishou:

                break;
            case R.id.btn_kuaidi:

                break;
            case R.id.btn_subTask:
                Intent data = new Intent();
                String etContent = et_Detailed_description.getText().toString();
                switch (flag){
                    case 1:
                    if (!TextUtils.isEmpty(etContent)){
                        data.putExtra("nickName",etContent);
                        setResult(RESULT_OK,data);
                        Toast.makeText(TaskhallPublishAcitivity.this,"保存成功",
                                Toast.LENGTH_SHORT).show();
                        TaskhallPublishAcitivity.this.finish();
                    }
                    break;
                    case 2:
                        if (!TextUtils.isEmpty(etContent)){
                            data.putExtra("signature",etContent);
                            setResult(RESULT_OK,data);
                            Toast.makeText(TaskhallPublishAcitivity.this,"保存成功",
                                    Toast.LENGTH_SHORT).show();
                            TaskhallPublishAcitivity.this.finish();
                        }else{
                            Toast.makeText(TaskhallPublishAcitivity.this,"签名不能为空",
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                break;
        }
    }
    //设置输入的文本长度
    private void contentListener() {
        et_Detailed_description.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable=et_Detailed_description.getText();
                int len=editable.length();//输入的文本的长度
                if (len > 0){
//                    iv_delete.setVisibility(View.VISIBLE);
                }else{
//                    iv_delete.setVisibility(View.GONE);
                }
                switch (flag){
                    case 1://标题
                        //标题限制最多8个文字，超过8个需要截取掉多余的文字
                        if (len>10){
                            int selEndIndex= Selection.getSelectionEnd(editable);
                            String str=editable.toString();
                            //截取新字符串
                            String newStr=str.substring(0,8);
                            et_Detailed_description.setText(newStr);
                            editable=et_Detailed_description.getText();
                            //新字符串的长度
                            int newLen = editable.length();
                            //旧光标位置超过新字符串的长度
                            if (selEndIndex>newLen){
                                selEndIndex=editable.length();
                            }
                            //设置新光标所在的位置
                            Selection.setSelection(editable,selEndIndex);
                        }
                        break;
                    case 2://任务描述
                        //任务描述最多是100个字符，超过100个字符需要截取掉多余的文字
                        if (len>16){
                            int selEndIndex= Selection.getSelectionEnd(editable);
                            String str=editable.toString();
                            //截取新字符串
                            String newStr=str.substring(0,16);
                            et_Detailed_description.setText(newStr);
                            editable=et_Detailed_description.getText();
                            //新字符串的长度
                            int newLen = editable.length();
                            //旧光标位置超过新字符串的长度
                            if (selEndIndex>newLen){
                                selEndIndex=editable.length();
                            }
                            //设置新光标所在的位置
                            Selection.setSelection(editable,selEndIndex);
//                            Toast.makeText(TaskhallPublishAcitivity.this,"输入不能大于100字符",
//                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        } );
    }


            private void submit() {
                // validate
//        String Dtitle = et_Dtitle.getText().toString().trim();
//        if (TextUtils.isEmpty(Dtitle)) {
//            Toast.makeText(this, "任务标题 输入侠客喜爱的标题", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        String description = et_Detailed_description.getText().toString().trim();
//        if (TextUtils.isEmpty(description)) {
//            Toast.makeText(this, "描述一下任务内容，入手渠道和须知", Toast.LENGTH_SHORT).show();
//            return;
//        }

                // TODO validate success, do something


            }

}