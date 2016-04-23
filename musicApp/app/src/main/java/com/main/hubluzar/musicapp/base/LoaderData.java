package com.main.hubluzar.musicapp.base;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by Агент on 17.04.2016.
 */
public interface LoaderData {
    public void extentionListItemsMusicGroup(List<ItemMusicGroup> listItemsMusicGroup, int position);
    public Integer getSizeJSONArray();
    public void sendRequest();
    public void setImageUrl(NetworkImageView networkImageView, String url);
}
