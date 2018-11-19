package com.hhn.asus.express;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private TextView tv_task,tv_community,tv_message,tv_me,tv_main_title;
    private ImageView iv_task,iv_community,iv_message,iv_me;

    private View mTackBtn;   //底部的每个图标和文字的组合
    private View mCommunityBtn;
    private View mMessageBtn;
    private View mMeBtn;
    public LinearLayout mBottomLayout;
    private LinearLayout ll__title_bar;
    private FrameLayout mBodyLayout;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Init();
        setListener();
        setInitStatus();
    }
//数据初始化
    private void Init() {
        tv_task=(TextView)findViewById(R.id.bottom_bar_text_task);
        tv_community=(TextView)findViewById(R.id.bottom_bar_text_community);
        tv_message=(TextView)findViewById(R.id.bottom_bar_text_message);
        tv_me=(TextView)findViewById(R.id.bottom_bar_text_me);
        iv_task=(ImageView)findViewById(R.id.bottom_bar_image_task);
        iv_community=(ImageView)findViewById(R.id.bottom_bar_image_community);
        iv_message=(ImageView)findViewById(R.id.bottom_bar_image_message);
        iv_me=(ImageView)findViewById(R.id.bottom_bar_image_me);
        mTackBtn=findViewById(R.id.bottom_bar_task__btn);
        mCommunityBtn=findViewById(R.id.bottom_bar_community_btn);
        mMessageBtn=findViewById(R.id.bottom_bar_message_btn);
        mMeBtn=findViewById(R.id.bottom_bar_me_btn);
        mBottomLayout=(LinearLayout)findViewById(R.id.main_bottom_bar);  //中间内容
        mBodyLayout=(FrameLayout)findViewById(R.id.main_body);
        ll__title_bar=(LinearLayout)findViewById(R.id.title_bar);         //顶部的框
        tv_main_title=(TextView)findViewById(R.id.tv_main_content);      //顶部文字


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bottom_bar_task__btn:
                clearBottomImageState();
                selectDisplayView(0);
                break;
            case R.id.bottom_bar_community_btn:
                clearBottomImageState();
                selectDisplayView(1);
                break;
            case R.id.bottom_bar_message_btn:
                clearBottomImageState();
                selectDisplayView(2);
                break;
            case R.id.bottom_bar_me_btn:
                clearBottomImageState();
                selectDisplayView(3);
                break;
            default: break;
        }
    }
//清除底部按钮的选中状态
    private void clearBottomImageState() {
        tv_task.setTextColor(Color.parseColor("#666666"));
        tv_community.setTextColor(Color.parseColor("#666666"));
        tv_me.setTextColor(Color.parseColor("#666666"));
        tv_message.setTextColor(Color.parseColor("#666666"));
        iv_task.setImageResource(R.drawable.home);
        iv_community.setImageResource(R.drawable.home);
        iv_message.setImageResource(R.drawable.home);
        iv_me.setImageResource(R.drawable.home);
        for (int i=0;i<mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setSelected(false);
        }


    }
    private  void  setListener(){
        for (int i=0;i<mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }
    }
    //移除不需要的视图
    private  void  removeAllView(){
        for (int i=0;i<mBodyLayout.getChildCount();i++){
            mBodyLayout.getChildAt(i).setVisibility(View.GONE);
        }
    }
    //设置界面的初始状态
    private void  setInitStatus(){
        clearBottomImageState();
        setSelectedStatus(0);
        creatView(0);
    }

    private void setSelectedStatus(int i) {
        switch (i){
            case 0:
                mTackBtn.setSelected(true);
                iv_task.setImageResource(R.drawable.home);
                tv_task.setTextColor(Color.parseColor("#0097F7"));
                ll__title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("任务大厅");
                break;
            case 1:
                mCommunityBtn.setSelected(true);
                iv_community.setImageResource(R.drawable.community);
                tv_community.setTextColor(Color.parseColor("#0097F7"));
                ll__title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("社区");
                break;
            case 2:
              mMessageBtn.setSelected(true);
                iv_message.setImageResource(R.drawable.community);
                tv_message.setTextColor(Color.parseColor("#0097F7"));
                ll__title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("消息");
                break;
            case 3:
                mMeBtn.setSelected(true);
                iv_me.setImageResource(R.drawable.community);
                tv_me.setTextColor(Color.parseColor("#0097F7"));
                ll__title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("个人中心");
                break;
        }

    }

    //设置对应界面
    private void selectDisplayView(int i) {
        removeAllView();
        creatView(i);
        setSelectedStatus(i);
    }
    private void creatView(int i) {
        switch (i){
            case 0://大厅界面；
                break;
            case 1://社区界面
                break;
            case 2://消息界面
                break;
            case 3://个人中心界面
                break;

        }
}}
