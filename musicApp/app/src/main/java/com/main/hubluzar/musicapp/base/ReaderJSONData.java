package com.main.hubluzar.musicapp.base;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Агент on 17.04.2016.
 */
public interface ReaderJSONData {
    public void extentionListItemsMusicGroup(List<ItemMusicGroup> listItemsMusicGroup, int position);
    public Integer getSizeJSONArray();
    public void setJSONArray(JSONArray jSONArray);
}
