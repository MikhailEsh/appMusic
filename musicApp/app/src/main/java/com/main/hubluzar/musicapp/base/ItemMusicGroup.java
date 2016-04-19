package com.main.hubluzar.musicapp.base;

import android.content.Context;

import com.main.hubluzar.musicapp.R;

/**
 * Created by sbt-eshtokin-ml on 27.03.2016.
 */
public class ItemMusicGroup {
    private Context context;
    private String name, description, link, linkSmallImage, linkBigImage;
    private Integer  albums, tracks, id;
    private String genres[];
    final private String space = " ";

    public ItemMusicGroup(String name, String description, String link, String linkSmallImage, String linkBigImage, Integer albums, Integer tracks, Integer id, String[] genres, Context context) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.linkSmallImage = linkSmallImage;
        this.linkBigImage = linkBigImage;
        this.albums = albums;
        this.tracks = tracks;
        this.id = id;
        this.genres = genres;
        this.context = context;
    }

    public String[] getGenres() {
        return genres;
    }

    //Функция возвращает склеенный набор "стилей pop rnb"
    public String getGenresString() {
        if (this.genres == null) return null;
        StringBuilder  genresBuilder = new StringBuilder();
        for( int i = 0 ; i < this.genres.length; i++)
            genresBuilder.append(this.genres[i]).append(this.space);
        return genresBuilder.toString();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTracks() {
        return tracks;
    }

    //Возвращает склеенное поле количества треков
    public String getTracksString() {
        if (this.tracks == null ) return null;
        return this.tracks.toString() + space + context.getString(R.string.list_item_textView_countSing);
    }


    public Integer getAlbums() {
        return albums;
    }

    //Возвращает склеенное поле количества альбомов
    public String getAlbumsString() {
        if (this.albums == null ) return null;
        return this.albums.toString() + space + context.getString(R.string.list_item_textView_countAlbum);
    }


    public String getLinkBigImage() {
        return linkBigImage;
    }


    public String getLinkSmallImage() {
        return linkSmallImage;
    }


    public String getLink() {
        return link;
    }




    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
