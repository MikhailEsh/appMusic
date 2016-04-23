package com.main.hubluzar.musicapp.display;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.main.hubluzar.musicapp.base.AdapterListGroups;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;
import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.base.LoaderData;

import java.util.List;

/**
 * Created by sbt-eshtokin-ml on 27.03.2016.
 */
public class AdapterListGroupsImpl extends BaseAdapter implements AdapterListGroups {

    private List<ItemMusicGroup> listItemsMusicGroup;
    private LayoutInflater layoutInflater;
    private LoaderData loaderData;
    final private int heightForLoad;

    public AdapterListGroupsImpl(Context context, List<ItemMusicGroup> listItemsMusicGroup, LoaderData loaderData) {
        this.listItemsMusicGroup = listItemsMusicGroup;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.loaderData = loaderData;
        this.heightForLoad = context.getResources().getInteger(R.integer.sizeOfListView) / 2;//Параметр который определяет когда нужно подгружать данные в список
    }

    @Override
    public int getCount() {
        if ( this.listItemsMusicGroup.isEmpty() ) loaderData.extentionListItemsMusicGroup(listItemsMusicGroup, 0);//Если список пустой, подгружаем первые данне
        return this.listItemsMusicGroup.size();
    }

    @Override
    public Object getItem(int position) {
        //При прокрутке смотрим нужно ли увечичвать список
        if (checkNeedExtention(position)) {
            loaderData.extentionListItemsMusicGroup(listItemsMusicGroup, position);
            notifyDataSetChanged();
        }
        return this.listItemsMusicGroup.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if ( view == null ) {
            view = layoutInflater.inflate(R.layout.list_item, parent, false);
        }
        ItemMusicGroup currentItemMusicGroup = getItemMusicGroup(position);
        setTextContentItem(view, currentItemMusicGroup);
        setImageContentItem(view, currentItemMusicGroup);
        return view;
    }

    //Заполняем миниатюры для картинок
    private void setImageContentItem(View view, ItemMusicGroup currentItemMusicGroup)
    {
        if ( currentItemMusicGroup.getLinkSmallImage() != null) {
            NetworkImageView networkImageView = (NetworkImageView) view.findViewById(R.id.item_networkImageView_icon);
            loaderData.setImageUrl(networkImageView, currentItemMusicGroup.getLinkSmallImage());
        }
    }
    //Заполняем данные в TextView
    private void setTextContentItem(View view, ItemMusicGroup currentItemMusicGroup)
    {
        try {

            setTextForTextView(view, R.id.item_textView_nameGroup, currentItemMusicGroup.getName());
            setTextForTextView(view, R.id.list_item_textView_countAlbum, currentItemMusicGroup.getAlbumsString());
            setTextForTextView(view, R.id.list_item_textView_countSing, currentItemMusicGroup.getTracksString());
            setTextForTextView(view, R.id.list_item_TextView_genres, currentItemMusicGroup.getGenresString());

        } catch (Exception e) {
            Log.d(getStringResource(R.string.log_tag_error, view), this.getClass().getSimpleName() + view.toString());
        }

    }

    private void setTextForTextView( View view, int resIdView, String textContent) {
        TextView nameGroup = (TextView) view.findViewById(resIdView);
        if ( textContent == null) {
            textContent = getStringResource(R.string.defaultValueParametr, view);
        }
        nameGroup.setText(textContent);
    }

    private String getStringResource(int resourseId, View view)
    {
        return view.getContext().getResources().getString(resourseId);
    }

    public ItemMusicGroup getItemMusicGroup(int position) {
        return (ItemMusicGroup) getItem(position);
    }

    private boolean checkNeedExtention(int position)
    {
        if (position >= listItemsMusicGroup.size() - heightForLoad && position < loaderData.getSizeJSONArray())
        {
            return true;
        } else return false;

    }
}
