package com.scu.taphelp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by abhishekbirjepatil on 1/23/16.
 */
public class CustomAdapter  extends BaseAdapter {
    String [] result;
    String [] resultDesc;
    Context context;
    int [] imageId;
    int [] imageRating;
    int [] imageCall;
    DashboardActivity dashboardActivityOpen;
    private static LayoutInflater inflater=null;
    public CustomAdapter(DashboardActivity dashboardActivity, String[] prgmNameList, int[] prgmImages,String[] prgmNameListDesc,int[] prgmImagesRatings,int[] prgmImagesCall) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        resultDesc=prgmNameListDesc;
        context=dashboardActivity;
        imageId=prgmImages;
        imageRating=prgmImagesRatings;
        imageCall=prgmImagesCall;
        dashboardActivityOpen=dashboardActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
        TextView tvdesc;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.program_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.img.setImageResource(imageId[position]);
        holder.tv.setText(result[position]);
        holder.tvdesc=(TextView) rowView.findViewById(R.id.textView5);
        holder.tvdesc.setText(resultDesc[position]);

        holder.img=(ImageView) rowView.findViewById(R.id.imageView4);
        holder.img.setImageResource(imageRating[position]);

        holder.img=(ImageView) rowView.findViewById(R.id.imageView2);
        holder.img.setImageResource(imageCall[position]);


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();

                dashboardActivityOpen.startActivity(new Intent(dashboardActivityOpen, ScheduleActivity.class));

            }
        });
        return rowView;
    }

}