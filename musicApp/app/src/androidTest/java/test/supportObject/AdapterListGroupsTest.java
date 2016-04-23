package test.supportObject;

import android.view.View;
import android.view.ViewGroup;

import com.main.hubluzar.musicapp.base.AdapterListGroups;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;

/**
 * Created by Агент on 23.04.2016.
 */
public class AdapterListGroupsTest implements AdapterListGroups {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public ItemMusicGroup getItemMusicGroup(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public void notifyDataSetChanged() {

    }
}
