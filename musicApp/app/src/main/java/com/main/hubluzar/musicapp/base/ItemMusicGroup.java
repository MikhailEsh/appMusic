package com.main.hubluzar.musicapp.base;

/**
 * Created by sbt-eshtokin-ml on 27.03.2016.
 */
public class ItemMusicGroup {
    private String name, description, link, linkSmallImage, linkBigImage;
    private Integer  albums, tracks, id;
    private String genres[];

    public ItemMusicGroup(String name, String description, String link, String linkSmallImage, String linkBigImage, Integer albums, Integer tracks, Integer id, String[] genres) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.linkSmallImage = linkSmallImage;
        this.linkBigImage = linkBigImage;
        this.albums = albums;
        this.tracks = tracks;
        this.id = id;
        this.genres = genres;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
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

    public void setTracks(Integer tracks) {
        this.tracks = tracks;
    }

    public Integer getAlbums() {
        return albums;
    }

    public void setAlbums(Integer albums) {
        this.albums = albums;
    }

    public String getLinkBigImage() {
        return linkBigImage;
    }

    public void setLinkBigImage(String linkBigImage) {
        this.linkBigImage = linkBigImage;
    }

    public String getLinkSmallImage() {
        return linkSmallImage;
    }

    public void setLinkSmallImage(String linkSmallImage) {
        this.linkSmallImage = linkSmallImage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }



    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
