package com.test.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.transform.Templates;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context mContext;
    MainActivity mView;
    public static List<articles> mList= new ArrayList<>();
    articles item;
    public Adapter(Context mainActivity, ArrayList<articles> modelRecyclerArrayList, MainActivity mainActivity2) {
        this.mContext = mainActivity;
        this.mList = modelRecyclerArrayList;
        this.mView = mainActivity2;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout,viewGroup,false);
        return new Adapter.MyViewHolder(view);    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.txt_author.setText(mList.get(i).getAuthor());
        myViewHolder.txt_title.setText(mList.get(i).getTitle());
        Log.d("images",mList.get(i).getUrltoImage());
        Picasso.get()
                .load(mList.get(i).getUrltoImage())
                .into(myViewHolder.img_pic);
        String[] separated = mList.get(i).getPublishedAt().split("T");
        String trip_date=separated[0];
        String pastTripDate = null;
        @SuppressLint("SimpleDateFormat") DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") DateFormat outFormat = new SimpleDateFormat("dd MMMM yyyy");
        Date past_trip_date = null;
        try {
            past_trip_date = inFormat.parse(trip_date);
            assert past_trip_date != null;
            pastTripDate = outFormat.format(past_trip_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        myViewHolder.txt_date.setText(pastTripDate);

        myViewHolder.layout.setTag(i);
        myViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                Log.d("sadhsad",String.valueOf(pos));
                mContext.startActivity(new Intent(mContext, DetailActivity.class)
                        .putExtra("title",mList.get(pos).getTitle())
                        .putExtra("author",mList.get(pos).getAuthor())
                        .putExtra("description",mList.get(pos).getDescription())
                        .putExtra("url",mList.get(pos).getUrl())
                        .putExtra("urlToImage",mList.get(pos).getUrltoImage())
                        .putExtra("publishedAt",mList.get(pos).getPublishedAt())
                        .putExtra("content",mList.get(pos).getContent()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_author,txt_title,txt_date;
        ImageView img_pic;
        LinearLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title=(TextView)itemView.findViewById(R.id.txt_title);
            img_pic=(ImageView)itemView.findViewById(R.id.img_pic);
            txt_author=(TextView)itemView.findViewById(R.id.txt_author);
            txt_date=(TextView)itemView.findViewById(R.id.txt_date);
            layout=(LinearLayout)itemView.findViewById(R.id.layout);
        }
    }
}
