package com.main.hubluzar.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class GroupMusicActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        setContentActivityText(intent);

        ImageLoader imageLoader = createImageLoader();
        setContentActivityImage(imageLoader, intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setContentActivityText(Intent intent)
    {
        getSupportActionBar().setTitle(intent.getStringExtra(getString(R.string.common_labelGroup_name)));

        TextView descriptionGroup = (TextView) findViewById(R.id.group_textView_Description);
        descriptionGroup.setMovementMethod(new ScrollingMovementMethod());
        descriptionGroup.setText(intent.getStringExtra(getString(R.string.common_labelGroup_description)));
    }
    private void setContentActivityImage(ImageLoader imageLoader, Intent intent)
    {
        NetworkImageView iconNetworkImageView = (NetworkImageView) findViewById(R.id.group_networkImageView_icon);
        String linkBigImage = intent.getStringExtra(getString(R.string.common_labelGroup_linkBigImage));
        iconNetworkImageView.setImageUrl(linkBigImage, imageLoader);
    }

    private ImageLoader createImageLoader()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        return new ImageLoader(requestQueue,
                new LruBitmapCache(this));
    }

}
