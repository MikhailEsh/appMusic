package com.main.hubluzar.musicapp.base;

/**
 * Created by Агент on 21.04.2016.
 */
public interface ItemMusicGroup {

    public String[] getGenres();
    public String getGenresString();
    public Integer getId();
    public Integer getTracks();
    public String getTracksString();
    public Integer getAlbums();
    public String getAlbumsString();
    public String getLinkBigImage();
    public String getLinkSmallImage();
    public String getLink();
    public String getDescription();
    public String getName();
}
