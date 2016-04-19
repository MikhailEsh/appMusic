package com.main.hubluzar.musicapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.loader.LruBitmapCache;

import uk.co.senab.photoview.PhotoViewAttacher;

public class GroupMusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

        WebView descriptionWebView = (WebView) findViewById(R.id.group_webView_description);
        String htmlText = "<html><body style=\"text-align:justify\"> %s </body></html>";
        String descriptionString = intent.getStringExtra(getString(R.string.common_labelGroup_description));
        descriptionWebView.loadDataWithBaseURL(null, String.format(htmlText, descriptionString), "text/html", "UTF-8", null);
        descriptionWebView.setVerticalScrollBarEnabled(true);
        descriptionWebView.setHorizontalScrollBarEnabled(true);

        setTextView(R.id.group_textView_link, intent.getStringExtra(getString(R.string.common_labelGroup_link)));
        setTextView(R.id.group_textView_genres, intent.getStringExtra(getString(R.string.common_labelGroup_genresArray)));
        setTextView(R.id.group_textView_tracks, intent.getStringExtra(getString(R.string.common_labelGroup_tracks)));
        setTextView(R.id.group_textView_albums, intent.getStringExtra(getString(R.string.common_labelGroup_albums)));
    }

    private void setTextView( int resIdView, String textContent) {
        TextView nameGroup = (TextView) findViewById(resIdView);
        if ( textContent == null) {
            textContent = getString(R.string.defaultValueParametr);
        }
        nameGroup.setText(textContent);
    }

    private void setContentActivityImage(ImageLoader imageLoader, Intent intent)
    {
        NetworkImageView iconNetworkImageView = (NetworkImageView) findViewById(R.id.group_networkImageView_icon);
        String linkBigImage = intent.getStringExtra(getString(R.string.common_labelGroup_linkBigImage));
        iconNetworkImageView.setImageUrl(linkBigImage, imageLoader);
        PhotoViewAttacher mAttacher = new PhotoViewAttacher(iconNetworkImageView);
        mAttacher.setZoomable(true);
    }

    private ImageLoader createImageLoader()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        return new ImageLoader(requestQueue,
                new LruBitmapCache(this));
    }

}
