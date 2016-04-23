package com.main.hubluzar.musicapp.base;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Агент on 21.04.2016.
 */
public interface AdapterListGroups {
    public int getCount();
    public ItemMusicGroup getItemMusicGroup(int position);
    public View getView(int position, View convertView, ViewGroup parent);
    public long getItemId(int position);
    public Object getItem(int position);
    public void notifyDataSetChanged();

}
