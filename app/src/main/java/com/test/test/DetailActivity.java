package com.test.test;

import android.annotation.SuppressLint;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {
    TextView txt_title,txt_author,txt_content,txt_desc,txt_date,txt_url;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txt_title=(TextView)findViewById(R.id.txt_title);
        txt_author=(TextView)findViewById(R.id.txt_author);
        txt_content=(TextView)findViewById(R.id.txt_dcontent);
        txt_desc=(TextView)findViewById(R.id.txt_desc);
        txt_date=(TextView)findViewById(R.id.txt_date);
        txt_url=(TextView)findViewById(R.id.txt_url);

        imageView=(ImageView)findViewById(R.id.image_photo);

        if (getIntent().getStringExtra("title") != null && getIntent().getStringExtra("title").length() > 0) {
           txt_title.setText(getIntent().getStringExtra("title"));
        }

        if (getIntent().getStringExtra("author") != null && getIntent().getStringExtra("author").length() > 0) {
            txt_author.setText(getIntent().getStringExtra("author"));
        }

        if (getIntent().getStringExtra("description") != null && getIntent().getStringExtra("description").length() > 0) {
            txt_desc.setText(getIntent().getStringExtra("description"));
        }

        if (getIntent().getStringExtra("content") != null && getIntent().getStringExtra("content").length() > 0) {
            txt_content.setText(getIntent().getStringExtra("content"));
        }

        if (getIntent().getStringExtra("publishedAt") != null && getIntent().getStringExtra("publishedAt").length() > 0) {

            String[] separated = getIntent().getStringExtra("publishedAt").split("T");
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
            txt_date.setText(pastTripDate);
        }

        if (getIntent().getStringExtra("urlToImage") != null && getIntent().getStringExtra("urlToImage").length() > 0) {
            Picasso.get()
                    .load(getIntent().getStringExtra("urlToImage"))
                    .into(imageView);
        }

        if (getIntent().getStringExtra("url") != null && getIntent().getStringExtra("url").length() > 0) {

            txt_url.setText(Html.fromHtml(getIntent().getStringExtra("url")));
            txt_url.setMovementMethod(LinkMovementMethod.getInstance());

        }
    }
}
