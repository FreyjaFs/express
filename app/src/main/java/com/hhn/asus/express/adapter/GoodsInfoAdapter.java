package com.hhn.asus.express.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhn.asus.express.bean.GoodsBean;

import java.util.List;
import com.hhn.asus.express.R;

/**
 * Created by Administrator on 2018/11/24 0024.
 */

public class GoodsInfoAdapter extends BaseAdapter{
    private Context mContext;
    private List<GoodsBean> gbl;

    public void setData(List<GoodsBean> gbl) {
        this.gbl = gbl;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh ;
        if(convertView == null){
            vh = new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(
                    R.layout.goods_detail_info,null);
            vh.detailed_description=(TextView)convertView.findViewById(R.id.tv_detail_description);
            vh.userName=(TextView)convertView.findViewById(R.id.tv_user);
            vh.price=(TextView) convertView.findViewById(R.id.tv_price);
//            vh.pictureShare=(ImageView)convertView.findViewById(R.id.tv_)
            convertView.setTag(vh);
        }else {
            vh=(ViewHolder)convertView.getTag();
        }
        final GoodsBean bean = (GoodsBean) getItem(position);
        if(bean!=null){

            vh.detailed_description.setText(position +1+ "");
            vh.userName.setText(bean.dtitle);
            vh.price.setText(bean.detailed_description);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bean==null){
                    return;
                    //跳转到习题详情页面
                }
//                Intent intent = new Intent(mContext, ExercisesDetailActivity.class);
//                intent.putExtra("id",bean.id);
//                intent.putExtra("title",bean.title);
//                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        public TextView userName,price;
        public TextView detailed_description;
        public ImageView pictureShare;
    }
}
