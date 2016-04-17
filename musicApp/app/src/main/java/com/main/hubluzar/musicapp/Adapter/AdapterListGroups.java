package com.main.hubluzar.musicapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;
import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.base.LoaderData;

import java.util.List;

/**
 * Created by sbt-eshtokin-ml on 27.03.2016.
 */
public class AdapterListGroups extends BaseAdapter {

    private List<ItemMusicGroup> listItemsMusicGroup;
    private LayoutInflater layoutInflater;
    private LoaderData loaderData;

    final private String space = " ";
    final private String emptyString = "";
    final private int delta;

    public AdapterListGroups(Context context, List<ItemMusicGroup> listItemsMusicGroup, LoaderData loaderData) {
        this.listItemsMusicGroup = listItemsMusicGroup;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.loaderData = loaderData;
        this.delta = context.getResources().getInteger(R.integer.sizeOfListView) / 2;
    }

    @Override
    public int getCount() {
        if ( this.listItemsMusicGroup.isEmpty() ) loaderData.extentionListItemsMusicGroup(listItemsMusicGroup, 0);
        return this.listItemsMusicGroup.size();
    }

    @Override
    public Object getItem(int position) {
        if (position >= listItemsMusicGroup.size() - delta && position < loaderData.getSizeJSONArray())
        {
            loaderData.extentionListItemsMusicGroup(listItemsMusicGroup, position);
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

    private void setImageContentItem(View view, ItemMusicGroup currentItemMusicGroup)
    {
        if ( currentItemMusicGroup.getLinkSmallImage() != null) {
            NetworkImageView networkImageView = (NetworkImageView) view.findViewById(R.id.item_networkImageView_icon);
            networkImageView.setImageUrl(currentItemMusicGroup.getLinkSmallImage(), loaderData.getImageLoader());
        }
    }
    private void setTextContentItem(View view, ItemMusicGroup currentItemMusicGroup)
    {
        try {

            setTextView(currentItemMusicGroup, view, R.id.item_textView_nameGroup, currentItemMusicGroup.getName(), emptyString);

            String textSuffixCountAlbum = space + getStringResource(R.string.list_item_textView_countAlbum, view);
            setTextView(currentItemMusicGroup, view, R.id.list_item_textView_countAlbum, String.valueOf(currentItemMusicGroup.getAlbums()), textSuffixCountAlbum);

            String textSuffixCountSing = space + getStringResource(R.string.list_item_textView_countSing, view);
            setTextView(currentItemMusicGroup, view, R.id.list_item_textView_countSing, String.valueOf(currentItemMusicGroup.getTracks()), textSuffixCountSing);

            setTextGenre(currentItemMusicGroup, view);

        } catch (Exception e) {
            Log.d(getStringResource(R.string.log_tag_error, view), this.getClass().getSimpleName() + view.toString());
        }

    }

    private void setTextGenre(ItemMusicGroup currentItemMusicGroup, View view)
    {
        TextView genrer = (TextView) view.findViewById(R.id.list_item_TextView_styleMusic);
        String genresString = emptyString;
        String[] genresMas = currentItemMusicGroup.getGenres();
        for( int i = 0 ; i < genresMas.length; i++ )
        {
            genresString = genresString + genresMas[i] + space;
        }
        genrer.setText(genresString);
    }

    private void setTextView(ItemMusicGroup currentItemMusicGroup, View view, int resIdVew, String textContent, String textSuffix) {
        TextView nameGroup = (TextView) view.findViewById(resIdVew);
        if ( textContent == null) {
            textContent = getStringResource(R.string.defaultValueParametr, view);
        }
        nameGroup.setText(textContent + textSuffix);
    }

    private String getStringResource(int resourseId, View view)
    {
        return view.getContext().getResources().getString(resourseId);
    }

    public ItemMusicGroup getItemMusicGroup(int position) {
        return (ItemMusicGroup) getItem(position);
    }
}
