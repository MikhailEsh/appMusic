package com.main.hubluzar.musicapp.base;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by Агент on 17.04.2016.
 */
public interface LoaderData {
    public void extentionListItemsMusicGroup(List<ItemMusicGroup> listItemsMusicGroup, int position);
    public int getSizeJSONArray();
    public ImageLoader getImageLoader ();
    public void sendRequest(RequestQueue requestQueue);
}
